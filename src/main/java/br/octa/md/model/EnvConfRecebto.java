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
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class EnvConfRecebto {
	final static Logger logger = Logger.getLogger(EnvConfRecebto.class);
	
	TEnvEvento envEvento = null;
	
	public TEnvEvento createEnvConfRecebto(String versaoLeiaute, String idLote, String versaoLeiatueEvento,
			List<TEvento> envento) {
		logger.info("Gerando envento em lote da mensagem");
		envEvento = new TEnvEvento();
		envEvento.setIdLote(idLote);
		envEvento.setVersao(versaoLeiatueEvento);
		for (TEvento tEvento : envento) {
			envEvento.getEvento().add(tEvento);
		}

		return envEvento;
	}

	public TEnvEvento createEnvEvento(String versaoLeiaute, String idLote, String versaoLeiatueEvento,
			TEvento envento) {
		logger.info("Gerando envento da mensagem");
		envEvento = new TEnvEvento();
		envEvento.setIdLote(idLote);
		envEvento.setVersao(versaoLeiatueEvento);
		envEvento.getEvento().add(envento);
		return envEvento;
	}

	public String toXML(){
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(envEvento);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static String strValueOf(TEnvEvento envEvento)
			throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext
				.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TEnvEvento> element = new ObjectFactory().createEnvEvento(envEvento);
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
	
	public TEnvEvento createEnvConfRecebtoFromXMl(String xmlEnvEvento) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TEnvEvento> element = (JAXBElement<TEnvEvento>) unmarshaller
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
