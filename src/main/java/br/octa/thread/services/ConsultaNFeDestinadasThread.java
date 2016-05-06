package br.octa.thread.services;

import org.apache.log4j.Logger;

import com.sap.conn.jco.JCoFunction;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.octa.enums.VersaoDadosEnum;
import br.octa.mappers.ConfNFeDestTORFC;
import br.octa.mappers.RFCTOConsNFeDest;
import br.octa.service.nfe.ws.ConsNFeDestServiceWS;
import br.octa.service.nfe.ws.EnvConfRecebtoServiceSync;
import br.octa.utils.ConsultaNFeUtils;

public class ConsultaNFeDestinadasThread implements Runnable {

	final static Logger logger = Logger.getLogger(EnvConfRecebtoServiceSync.class);

	private static final String indNfe = null;

	public ConsNFeDestServiceWS service;
	JCoFunction function;

	public ConsultaNFeDestinadasThread(JCoFunction function) {
		this.function = function;
		service  = new ConsNFeDestServiceWS();
	}

	public void run() {
		processar();
	}

	private void processar() {
		logger.info("Inicio da thread de ConsultaNfeDestinadas");
		RFCTOConsNFeDest consNFeDest = new RFCTOConsNFeDest(function);
		TConsNFeDest consulta = consNFeDest.getRFCData();
		TRetConsNFeDest trRetConsNFeDest = service.sendConsNfeDest(ConsultaNFeUtils.getUrl(consulta.getTpAmb()),consulta, VersaoDadosEnum.V1_01,ConsultaNFeUtils.getUFConsulta());
		ConfNFeDestTORFC.setRFCData(function, trRetConsNFeDest);
		logger.info("Fim da thread de ConsultaNfeDestinadas");

	}
}
