package br.octa.service.nfe.ws;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.www.nfe.wsdl.recepcaoevento.RecepcaoEventoStub;
import br.octa.enums.URLServicosEnum;
import br.octa.enums.UFEnum;
import br.octa.enums.VersaoDadosEnum;
import br.octa.helpers.EnvConfRecebtoHelper;
import br.octa.helpers.TRetEnvEventoHelper;

public class EnvConfRecebtoServiceSync {

	final static Logger logger = Logger.getLogger(EnvConfRecebtoServiceSync.class);

	
	public TRetEnvEvento sendEnvEvento(TEnvEvento evento, VersaoDadosEnum versaoDados, UFEnum uf ){
		StringBuilder xml = new StringBuilder();
		try {
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(EnvConfRecebtoHelper.toXMlAssinado(evento));
			logger.info("XMl to send :"+ xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callWs(URLServicosEnum.HOMOLOGACAO_RECEPCAOEVENTO, xml, uf.getCodigoIbge(), versaoDados.valor());
	}
	
	private TRetEnvEvento callWs(URLServicosEnum url, StringBuilder xml, String UF, String versaoDados){
		try {
		 
			logger.info("Call WebService  	" + url);
			logger.info("Send XML  			" + xml);

			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			RecepcaoEventoStub.NfeDadosMsg dadosMsg = new RecepcaoEventoStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			RecepcaoEventoStub.NfeCabecMsg nfeCabecMsg = new RecepcaoEventoStub.NfeCabecMsg();
			nfeCabecMsg.setCUF(UF);
			nfeCabecMsg.setVersaoDados(versaoDados);
			RecepcaoEventoStub.NfeCabecMsgE nfeCabeMsgE = new RecepcaoEventoStub.NfeCabecMsgE();
			nfeCabeMsgE.setNfeCabecMsg(nfeCabecMsg);
			RecepcaoEventoStub stub = new RecepcaoEventoStub(url.value());
			RecepcaoEventoStub.NfeRecepcaoEventoResult result = stub.nfeRecepcaoEvento(dadosMsg, nfeCabeMsgE);

			logger.info("Call WebService success");
			return TRetEnvEventoHelper.parseResultToObj(result);
		} catch (XMLStreamException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
