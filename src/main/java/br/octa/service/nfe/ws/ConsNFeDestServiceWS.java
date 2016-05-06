package br.octa.service.nfe.ws;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAttribute;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.inf.portalfiscal.security.LoadDigitalSignature;
import br.inf.portalfiscal.www.nfe.wsdl.nfeconsultadest.NFeConsultaDestStub;
import br.octa.enums.UFEnum;
import br.octa.enums.URLServicosEnum;
import br.octa.enums.VersaoDadosEnum;
import br.octa.helpers.ConsNFeDestHelper;

public class ConsNFeDestServiceWS {
	final static Logger logger = Logger.getLogger(ConsNFeDestServiceWS.class);

	public TRetConsNFeDest sendConsNfeDest(URLServicosEnum url, TConsNFeDest evento, VersaoDadosEnum versaoDados,
			UFEnum uf) {

		new LoadDigitalSignature();

		StringBuilder xml = new StringBuilder();
		try {
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(ConsNFeDestHelper.toXML(evento));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callWs(url, xml, uf, versaoDados.valor());
	}

	private TRetConsNFeDest callWs(URLServicosEnum url, StringBuilder xml, UFEnum UF, String versaoDados) {
		try {
			logger.info("Call WebService  	" + url);
			logger.info("Send XML  			" + xml);

			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			NFeConsultaDestStub.NfeDadosMsg dadosMsg = new NFeConsultaDestStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			NFeConsultaDestStub.NfeCabecMsg nfeCabecMsg = new NFeConsultaDestStub.NfeCabecMsg();
			nfeCabecMsg.setCUF(UF.getCodigoIbge());
			nfeCabecMsg.setVersaoDados(versaoDados);
			NFeConsultaDestStub.NfeCabecMsgE nfeCabeMsgE = new NFeConsultaDestStub.NfeCabecMsgE();
			nfeCabeMsgE.setNfeCabecMsg(nfeCabecMsg);
			NFeConsultaDestStub stub = new NFeConsultaDestStub(url.value());
			NFeConsultaDestStub.NfeConsultaNFDestResult result = stub.nfeConsultaNFDest(dadosMsg, nfeCabeMsgE);

			logger.info("Call WebService success");
			return ConsNFeDestHelper.parseResultToObj(result);
		} catch (RemoteException | XMLStreamException e) {
			logger.info(e.getMessage());
		}

		return null;
	}

}
