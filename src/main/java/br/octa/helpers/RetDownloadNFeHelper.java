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
import br.inf.portalfiscal.nfe.TRetDownloadNFe;

public class RetDownloadNFeHelper {
	final static Logger logger = Logger.getLogger(RetDownloadNFeHelper.class);


	public static String toXML(TRetDownloadNFe retDownloadNfe) {
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(retDownloadNfe);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String strValueOf(TRetDownloadNFe retDownloadNfe) throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TRetDownloadNFe> element = new ObjectFactory().createRetDownloadNFe(retDownloadNfe);
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

	public static TRetDownloadNFe createRetDownloadNfeFromXMl(String xmlRetDownloadNFe) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TRetDownloadNFe> element = (JAXBElement<TRetDownloadNFe>) unmarshaller
					.unmarshal(new StringReader(xmlRetDownloadNFe));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
