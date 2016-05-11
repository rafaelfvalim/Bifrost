package br.octa.service.nfe.ws;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.www.nfe.wsdl.recepcaoevento.RecepcaoEventoStub;
import br.octa.helpers.EnvConfRecebtoHelper;
import br.octa.helpers.TRetEnvEventoHelper;
import br.octa.model.NFeEnvEventoWSData;
import br.octa.view.BifrostView;

public class EnvConfRecebtoServiceWS {

	final static Logger logger = Logger.getLogger(EnvConfRecebtoServiceWS.class);
	TEnvEvento evento;
	NFeEnvEventoWSData nfEnvEventoWSData;
	
	public EnvConfRecebtoServiceWS(TEnvEvento evento, NFeEnvEventoWSData nfEnvEventoWSData){
		this.evento = evento;
		this.nfEnvEventoWSData = nfEnvEventoWSData;
	}
	
	public TRetEnvEvento sendEnvEvento() {
		StringBuilder xml = new StringBuilder();
		try {
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(EnvConfRecebtoHelper.toXMlAssinado(evento));
			info("XMl to send :" + xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callWs(xml);
	}

	private TRetEnvEvento callWs(StringBuilder xml) {
		try {
				
			info("XML Enviado: " + xml);
			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			RecepcaoEventoStub.NfeDadosMsg dadosMsg = new RecepcaoEventoStub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			
			RecepcaoEventoStub.NfeCabecMsg nfeCabecMsg = new RecepcaoEventoStub.NfeCabecMsg();
			nfeCabecMsg.setCUF(nfEnvEventoWSData.getUf());
			nfeCabecMsg.setVersaoDados(nfEnvEventoWSData.getVersaoDados());
			
			RecepcaoEventoStub.NfeCabecMsgE nfeCabeMsgE = new RecepcaoEventoStub.NfeCabecMsgE();
			nfeCabeMsgE.setNfeCabecMsg(nfeCabecMsg);
			
			RecepcaoEventoStub stub = new RecepcaoEventoStub(nfEnvEventoWSData.getUrl());
			RecepcaoEventoStub.NfeRecepcaoEventoResult result = stub.nfeRecepcaoEvento(dadosMsg, nfeCabeMsgE);
			info("Chamada Websevice realizada com sucesso!");
			return TRetEnvEventoHelper.parseResultToObj(result);
		} catch (XMLStreamException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void info(String msg) {
		Logger.getLogger(EnvConfRecebtoServiceWS.class).info(msg);
		BifrostView.infoView(msg);
	}
}
