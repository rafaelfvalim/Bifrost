package br.octa.service.nfe.ws;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.inf.portalfiscal.security.LoadDigitalSignature;
import br.inf.portalfiscal.www.nfe.wsdl.nfeconsultadest.NFeConsultaDestStub;
import br.octa.enums.LayoutEnum;
import br.octa.helpers.ConsNFeDestHelper;
import br.octa.helpers.RetEnvConfRecebtoHelper;
import br.octa.model.NFeConsultaWSData;
import br.octa.utils.ConfigUtils;
import br.octa.utils.ConsultaNFeUtils;
import br.octa.utils.ViewUtils;
import br.octa.view.BifrostView;

public class ConsNFeDestServiceWS {
	final static Logger logger = Logger.getLogger(ConsNFeDestServiceWS.class);
	private TConsNFeDest evento;
	private NFeConsultaWSData nfConsultaWSData;
	
	
	public ConsNFeDestServiceWS(TConsNFeDest evento, NFeConsultaWSData nfConsultaWSData) {
		this.evento = evento;
		this.nfConsultaWSData = nfConsultaWSData;
	}

	public TRetConsNFeDest sendConsNfeDest() {

		new LoadDigitalSignature();

		StringBuilder xml = new StringBuilder();
		try {
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append(ConsNFeDestHelper.toXML(evento));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callWs(xml);
	}

	private TRetConsNFeDest callWs(StringBuilder xml) {
		try {
			info("XML Enviado: " + xml);
			ViewUtils.writeFileLog(xml.toString(), LayoutEnum.NFECCONSULTA);

			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			NFeConsultaDestStub.NfeDadosMsg dadosMsg = new NFeConsultaDestStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			NFeConsultaDestStub.NfeCabecMsg nfeCabecMsg = new NFeConsultaDestStub.NfeCabecMsg();
			nfeCabecMsg.setCUF(nfConsultaWSData.getUf());
			nfeCabecMsg.setVersaoDados(nfConsultaWSData.getVersaoDados());
			NFeConsultaDestStub.NfeCabecMsgE nfeCabeMsgE = new NFeConsultaDestStub.NfeCabecMsgE();
			nfeCabeMsgE.setNfeCabecMsg(nfeCabecMsg);
			NFeConsultaDestStub stub = new NFeConsultaDestStub(nfConsultaWSData.getUrl());
			NFeConsultaDestStub.NfeConsultaNFDestResult result = stub.nfeConsultaNFDest(dadosMsg, nfeCabeMsgE);
			
			ViewUtils.writeFileLog(result.getExtraElement().toString(), LayoutEnum.RETORNO_NFECCONSULTA);
			info("Chamada Websevice realizada com sucesso!");
			return ConsNFeDestHelper.parseResultToObj(result);
		} catch (RemoteException | XMLStreamException e) {
			info(e.getMessage());
		}

		return null;
	}

	public static void info(String msg) {
		Logger.getLogger(ConsNFeDestServiceWS.class).info(msg);
		BifrostView.infoView(msg);
	}
}
