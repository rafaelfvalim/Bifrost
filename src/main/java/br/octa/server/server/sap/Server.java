package br.octa.server.server.sap;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sap.conn.jco.AbapClassException;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerFunctionHandlerFactory;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.octa.enums.EasterEgg;
import br.octa.intefaces.mappers.ConfNFeDestTORFC;
import br.octa.intefaces.mappers.EnvConfRecbtoTORFC;
import br.octa.intefaces.mappers.RFCTOConsNFeDest;
import br.octa.intefaces.mappers.RFCTOEnvConfRecbto;
import br.octa.model.NFeConsultaWSData;
import br.octa.model.NFeEnvEventoWSData;
import br.octa.service.nfe.ws.ConsNFeDestServiceWS;
import br.octa.service.nfe.ws.EnvConfRecebtoServiceWS;
import br.octa.utils.ConfigUtils;
import br.octa.view.BifrostView;

public class Server {
	private static Server INSTANCIA;
	final static Logger logger = Logger.getLogger(Server.class);
	JCoServer server;

	static class MyFunctionHandlerFactory implements JCoServerFunctionHandlerFactory {
		class SessionContext {
			Hashtable<String, Object> cachedSessionData = new Hashtable<String, Object>();
		}

		private Map<String, SessionContext> statefulSessions = new Hashtable<String, SessionContext>();
		private StfcConnectionFunctionHandles stfcConnectionFunctionHandler = new StfcConnectionFunctionHandles();
		private KTTSendConsNfeDestWsFunctionHandler kTTSendConsNfeDestWsFunctionHandler = new KTTSendConsNfeDestWsFunctionHandler();
		private KTTSendEnvConfRecebtoWSFunctionHandler kTTSendEnvConfRecebtoWSFunctionHandler = new KTTSendEnvConfRecebtoWSFunctionHandler();

		public JCoServerFunctionHandler getCallHandler(JCoServerContext serverCtx, String functionName) {
			JCoServerFunctionHandler handler = null;

			System.out.println(functionName);

			if ("STFC_CONNECTION".equals(functionName))
				handler = stfcConnectionFunctionHandler;
			else if ("/KTT/SENDCONSNFEDESTWS".equals(functionName))
				handler = kTTSendConsNfeDestWsFunctionHandler;
			else if ("/KTT/SENDENVCONFRECEBTOWS".equals(functionName))
				handler = kTTSendEnvConfRecebtoWSFunctionHandler;

			if (handler instanceof StatefulFunctionModule) {
				SessionContext cachedSession;
				if (!serverCtx.isStatefulSession()) {
					serverCtx.setStateful(true);
					cachedSession = new SessionContext();
					statefulSessions.put(serverCtx.getSessionID(), cachedSession);
				} else {
					cachedSession = statefulSessions.get(serverCtx.getSessionID());
					if (cachedSession == null)
						throw new RuntimeException(
								"Unable to find the session context for session id " + serverCtx.getSessionID());
				}
				((StatefulFunctionModule) handler).setSessionData(cachedSession.cachedSessionData);
				return handler;
			}

			// null leads to a system failure on the ABAP side
			return null;
		}

		public void sessionClosed(JCoServerContext serverCtx, String message, boolean error) {
			System.out.println(
					"Session " + serverCtx.getSessionID() + " was closed " + (error ? message : "by SAP system"));
			statefulSessions.remove(serverCtx.getSessionID());
		}
	}

	static abstract class StatefulFunctionModule implements JCoServerFunctionHandler {
		Hashtable<String, Object> sessionData;

		public void setSessionData(Hashtable<String, Object> sessionData) {
			this.sessionData = sessionData;
		}
	}

	/// KTT/SENDCONSNFEDESTWS
	static class KTTSendConsNfeDestWsFunctionHandler extends StatefulFunctionModule {

		@Override
		public void handleRequest(JCoServerContext context, JCoFunction function)
				throws AbapException, AbapClassException {
			ConsultaNFeDestinatario(function);
		}

	}

	/// KTT/SENDENVCONFRECEBTOWS
	static class KTTSendEnvConfRecebtoWSFunctionHandler extends StatefulFunctionModule {
		final static Logger logger = Logger.getLogger(KTTSendEnvConfRecebtoWSFunctionHandler.class);

		@Override
		public void handleRequest(JCoServerContext context, JCoFunction function)
				throws AbapException, AbapClassException {
			EnvioEvento(function);
		}
	}

	static class StfcConnectionFunctionHandles extends StatefulFunctionModule {
		public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
			function.getExportParameterList().setValue("ECHOTEXT",
					function.getImportParameterList().getString("REQUTEXT"));
			function.getExportParameterList().setValue("RESPTEXT", "BIFROST SAP INTEGRATOR");
			new EasterEgg().showLogo(function.getImportParameterList().getString("REQUTEXT"));

		}

	}

	public static void main(String[] args) {
		new Server().start();
	}

	public void stop() {
		server.stop();
		info("Server Finalizado");
	}

	public void start() {
		if (ConfigUtils.checkRequirements()) {
			// Monitor.createTrayIcon();
			String serverName = "SERVER";
			try {
				server = JCoServerFactory.getServer(serverName);
				info("Server Online");
			} catch (JCoException ex) {
				throw new RuntimeException(
						"Unable to create the server " + serverName + ", because of " + ex.getMessage(), ex);
			}
			server.setCallHandlerFactory(new MyFunctionHandlerFactory());
			server.start();
		}
	}

	public static synchronized Server getInstance() {
		if (INSTANCIA == null) {
			INSTANCIA = new Server();
		}
		return INSTANCIA;
	}

	public static boolean checkRequirements() {
		File certificado = new File(ConfigUtils.getArquivoCertificado());
		File caCert = new File(ConfigUtils.getNFeCacerts());
		File ABAP_AS_WITH_POOL = new File("ABAP_AS_WITH_POOL.jcoDestination");
		File ABAP_AS_WITHOUT_POOL = new File("ABAP_AS_WITHOUT_POOL.jcoDestination");
		File SERVER = new File("SERVER.jcoServer");
		if (certificado.exists() && caCert.exists() && SERVER.exists() && ABAP_AS_WITH_POOL.exists() && ABAP_AS_WITHOUT_POOL.exists() ) {
			return true;
		} else {
			String msg = "Unable to create the server check if A1 certificate is present and install keystore ";
			info(msg);
			throw new RuntimeException(msg);
		}

	}

	public static void EnvioEvento(JCoFunction function) {
		final Logger logger = Logger.getLogger(KTTSendEnvConfRecebtoWSFunctionHandler.class);
		info("#################Inicio do envio de evento de NFes##########################");
		BifrostView.infoView("#################Inicio do envio de evento de NFes##########################");
		info("Recebendo solicitação da RFC: " + function.getName());

		RFCTOEnvConfRecbto rfctoEnvConfRecbto = new RFCTOEnvConfRecbto(function);
		TEnvEvento evento = rfctoEnvConfRecbto.getRfcData();
		NFeEnvEventoWSData nFeEnvEventoWSData = rfctoEnvConfRecbto.getHeaderWS();

		info("Enviando dasdos ao SEFAZ URL: " + nFeEnvEventoWSData.getUrl());
		EnvConfRecbtoTORFC.setRFCData(function,
				new EnvConfRecebtoServiceWS(evento, nFeEnvEventoWSData).sendEnvEvento());
		info("#################Fim do envio de evento de NFes#############################");
		BifrostView.infoView("#################Fim do envio de evento de NFes#############################");

	}

	public static void ConsultaNFeDestinatario(JCoFunction function) {
		final Logger logger = Logger.getLogger(KTTSendConsNfeDestWsFunctionHandler.class);
		info("#################Inicio da consulta de NFes################################");
		info("Recebendo solicitação da RFC: " + function.getName());

		RFCTOConsNFeDest reConsNFeDest = new RFCTOConsNFeDest(function);
		TConsNFeDest consulta = reConsNFeDest.getRFCData();
		NFeConsultaWSData nFeConsultaWSData = reConsNFeDest.getHeaderWS();

		info("Enviando dasdos ao SEFAZ URL: " + nFeConsultaWSData.getUrl());
		ConfNFeDestTORFC.setRFCData(function, new ConsNFeDestServiceWS(consulta, nFeConsultaWSData).sendConsNfeDest());
		info("#################Fim da consulta de NFes###################################");
	}

	public static void info(String msg) {
		Logger.getLogger(Server.class).info(msg);
		BifrostView.infoView(msg);
	}

}