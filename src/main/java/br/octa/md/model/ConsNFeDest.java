package br.octa.md.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class ConsNFeDest {
	final static Logger logger = Logger.getLogger(ConsNFeDest.class);
	TConsNFeDest consNFeDest = null;

	
	public TConsNFeDest createConsNfeDest(String cNPJ, String indEmi, String indNfe, String tpAmb, String ulTnsu, String versao, String xServ) {
		logger.info("Gerando envento da mensagem");
		consNFeDest = new TConsNFeDest();
		consNFeDest.setCNPJ(cNPJ);
		consNFeDest.setIndEmi(indEmi);
		consNFeDest.setIndNFe(indNfe);
		consNFeDest.setTpAmb(tpAmb);
		consNFeDest.setUltNSU(ulTnsu);
		consNFeDest.setVersao(versao);
		consNFeDest.setXServ(xServ);
		return consNFeDest;
	}

	public String toXML(){
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(consNFeDest);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static String strValueOf(TConsNFeDest consNFeDest)
			throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext
				.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TConsNFeDest> element = new ObjectFactory().createConsNFeDest(consNFeDest);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String sw_aux = sw.toString();
		/*
		 * Correção do erro de namespace
		 */
		sw_aux = sw_aux.replaceAll(
				"xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		return sw_aux;
	}
	
	public TConsNFeDest createConsNfeDestFromXMl(String xmlEnvEvento) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TConsNFeDest> element = (JAXBElement<TConsNFeDest>) unmarshaller
					.unmarshal(new StringReader(xmlEnvEvento));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
