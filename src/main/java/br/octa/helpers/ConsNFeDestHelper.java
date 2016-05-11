package br.octa.helpers;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.inf.portalfiscal.www.nfe.wsdl.nfeconsultadest.NFeConsultaDestStub.NfeConsultaNFDestResult;
import br.octa.view.BifrostView;

public class ConsNFeDestHelper{
	final static Logger logger = Logger.getLogger(ConsNFeDestHelper.class);

	public static String toXML(TConsNFeDest consNFeDest){
		info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(consNFeDest);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static String strValueOf(TConsNFeDest consNFeDest)
			throws JAXBException {
		info("Criando XML do Objeto TEnvEvento ");
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
		sw_aux = sw_aux.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"" , "");
		return sw_aux;
	}
	
	public static TConsNFeDest createConsNfeDestFromXMl(String xmlEnvEvento) {
		info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TConsNFeDest> element = (JAXBElement<TConsNFeDest>) unmarshaller.unmarshal(new StringReader(xmlEnvEvento));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static TRetConsNFeDest parseResultToObj(NfeConsultaNFDestResult result) {
		info("Parse return of webservice");
		String xml = result.getExtraElement().toString();
		info("XML Retorno....: " + xml);
		JAXBContext context;
		try {
			context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<TRetConsNFeDest> element = (JAXBElement<TRetConsNFeDest>) unmarshaller
					.unmarshal(new StringReader(xml));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
			info(e.getMessage());
		}
		return null;
	}
	public static void info(String msg) {
		Logger.getLogger(ConsNFeDestHelper.class).info(msg);
		BifrostView.infoView(msg);
	}



}
