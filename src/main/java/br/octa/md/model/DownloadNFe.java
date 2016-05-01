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
import br.inf.portalfiscal.nfe.TDownloadNFe;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class DownloadNFe {
	final static Logger logger = Logger.getLogger(DownloadNFe.class);

	TDownloadNFe downloadNfe = null;

	public TDownloadNFe createDownloadNfe(String cnpj, String tpAmb, String versao, String xServ) {
		logger.info("Gerando envento em lote da mensagem");
		downloadNfe.setCNPJ(cnpj);
		downloadNfe.setTpAmb(tpAmb);
		downloadNfe.setVersao(versao);
		downloadNfe.setXServ(xServ);
		return downloadNfe;
	}

	public String toXML() {
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(downloadNfe);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String strValueOf(TDownloadNFe downloadNfe) throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TDownloadNFe> element = new ObjectFactory().createDownloadNFe(downloadNfe);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

		StringWriter sw = new StringWriter();
		marshaller.marshal(element, sw);
		String sw_aux = sw.toString();
		/*
		 * Correção do erro de namespace
		 */
		sw_aux = sw_aux.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		return sw_aux;
	}

	public TDownloadNFe createDownloadNfeFromXMl(String xmlDownloadNFe) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TDownloadNFe> element = (JAXBElement<TDownloadNFe>) unmarshaller
					.unmarshal(new StringReader(xmlDownloadNFe));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
