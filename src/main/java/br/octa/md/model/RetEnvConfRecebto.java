package br.octa.md.model;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class RetEnvConfRecebto {
	final static Logger logger = Logger.getLogger(RetEnvConfRecebto.class);

	TRetEnvEvento retEnvEvento = null;

	public TRetEnvEvento createRetEnvEventoFromXMl(String xmlRetEnvEvento) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TRetEnvEvento> element = (JAXBElement<TRetEnvEvento>) unmarshaller
					.unmarshal(new StringReader(xmlRetEnvEvento));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public TRetEnvEvento createRetEnvConfRecebto(String cOrgao, String cStat, String idLote, String tpAmb, String verAplic,
			String versao, String xMotivo) {
		logger.info("Gerando envento da mensagem");
		retEnvEvento = new TRetEnvEvento();
		retEnvEvento.setCOrgao(cOrgao);
		retEnvEvento.setCStat(cStat);
		retEnvEvento.setIdLote(idLote);
		retEnvEvento.setTpAmb(tpAmb);
		retEnvEvento.setVerAplic(verAplic);
		retEnvEvento.setVersao(versao);
		retEnvEvento.setXMotivo(xMotivo);
		return retEnvEvento;
	}

	public String toXML(){
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(retEnvEvento);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static String strValueOf(TRetEnvEvento retEnvEvento)
			throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext
				.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TRetEnvEvento> element = new ObjectFactory().createRetEnvEvento(retEnvEvento);
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
}
