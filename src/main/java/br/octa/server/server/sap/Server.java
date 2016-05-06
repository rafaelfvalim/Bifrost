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
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.octa.enums.EasterEgg;
import br.octa.enums.VersaoDadosEnum;
import br.octa.mappers.ConfNFeDestTORFC;
import br.octa.mappers.RFCTOConsNFeDest;
import br.octa.service.nfe.ws.ConsNFeDestServiceWS;
import br.octa.thread.services.ConsultaNFeDestinadasThread;
import br.octa.utils.ConfigUtils;
import br.octa.utils.ConsultaNFeUtils;

public class Server {

	final static Logger logger = Logger.getLogger(Server.class);

	static class MyFunctionHandlerFactory implements JCoServerFunctionHandlerFactory {
		class SessionContext {
			Hashtable<String, Object> cachedSessionData = new Hashtable<String, Object>();
		}

		private Map<String, SessionContext> statefulSessions = new Hashtable<String, SessionContext>();
		private ZGetCounterFunctionHandler zGetCounterFunctionHandler = new ZGetCounterFunctionHandler();
		private ZIncrementCounterFunctionHandler zIncrementCounterFunctionHandler = new ZIncrementCounterFunctionHandler();
		private StfcConnectionFunctionHandles stfcConnectionFunctionHandler = new StfcConnectionFunctionHandles();
		private KTTSendConsNfeDestWsFunctionHandler kTTSendConsNfeDestWsFunctionHandler = new KTTSendConsNfeDestWsFunctionHandler();
		private KTTSendEnvConfRecebtoWSFunctionHandler kTTSendEnvConfRecebtoWSFunctionHandler = new KTTSendEnvConfRecebtoWSFunctionHandler();

		public JCoServerFunctionHandler getCallHandler(JCoServerContext serverCtx, String functionName) {
			JCoServerFunctionHandler handler = null;

			System.out.println(functionName);

			if ("STFC_CONNECTION".equals(functionName))
				handler = stfcConnectionFunctionHandler;
			else if ("Z_GET_COUNTER".equals(functionName))
				handler = zGetCounterFunctionHandler;
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
		public void handleRequest(JCoServerContext context, JCoFunction function) throws AbapException, AbapClassException {
			System.out.println("RECEIVE FROM SAP");
			ConsNFeDestServiceWS service = new ConsNFeDestServiceWS();
			System.out.println("Inicio da thread de ConsultaNfeDestinadas");
			RFCTOConsNFeDest consNFeDest = new RFCTOConsNFeDest(function);
			TConsNFeDest consulta = consNFeDest.getRFCData();
			TRetConsNFeDest trRetConsNFeDest = service.sendConsNfeDest(ConsultaNFeUtils.getUrl(consulta.getTpAmb()),consulta, VersaoDadosEnum.V1_01,ConsultaNFeUtils.getUFConsulta());
			ConfNFeDestTORFC.setRFCData(function, trRetConsNFeDest);
			System.out.println("Fim da thread de ConsultaNfeDestinadas");
		}

	}

	/// KTT/SENDENVCONFRECEBTOWS
	static class KTTSendEnvConfRecebtoWSFunctionHandler extends StatefulFunctionModule {
		final static Logger logger = Logger.getLogger(KTTSendEnvConfRecebtoWSFunctionHandler.class);

		@Override
		public void handleRequest(JCoServerContext context, JCoFunction function) throws AbapException, AbapClassException {
			System.out.println("RECEIVE FROM SAP");
		
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

	static class ZGetCounterFunctionHandler extends StatefulFunctionModule {
		public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
			System.out.println("ZGetCounterFunctionHandler: return counter");
			Integer counter = (Integer) sessionData.get("COUNTER");
			if (counter == null)
				function.getExportParameterList().setValue("GET_VALUE", 0);
			else
				function.getExportParameterList().setValue("GET_VALUE", counter.intValue());
		}

	}

	static class ZIncrementCounterFunctionHandler extends StatefulFunctionModule {
		public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
			System.out.println("ZIncrementCounterFunctionHandler: increase counter");
			Integer counter = (Integer) sessionData.get("COUNTER");
			if (counter == null)
				sessionData.put("COUNTER", new Integer(1));
			else
				sessionData.put("COUNTER", new Integer(counter.intValue() + 1));
		}
	}

	public static void main(String[] args) {
		if (checkRequirements()) {
			// Monitor.createTrayIcon();

			String serverName = "SERVER";
			JCoServer server;
			try {
				server = JCoServerFactory.getServer(serverName);
				System.out.println(">>>>>>>>>>>>>>>>>>>SERVER ONLINE<<<<<<<<<<<<<<<<<<<<");
			} catch (JCoException ex) {
				throw new RuntimeException(
						"Unable to create the server " + serverName + ", because of " + ex.getMessage(), ex);
			}
			server.setCallHandlerFactory(new MyFunctionHandlerFactory());
			server.start();
		}
	}

	public static boolean checkRequirements() {

		File certificado = new File(ConfigUtils.getArquivoCertificado());
		File caCert = new File(ConfigUtils.getNFeCacerts());

		if (certificado.exists() && caCert.exists()) {
			return true;
		} else {
			throw new RuntimeException(
					"Unable to create the server check if A1 certificate is present and install keystore ");
		}
	}

}