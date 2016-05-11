package br.octa.helpers;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.www.nfe.wsdl.recepcaoevento.RecepcaoEventoStub.NfeRecepcaoEventoResult;
import br.octa.to.TRetEnvEventoTO;
import br.octa.view.BifrostView;

public class TRetEnvEventoHelper {
	final static Logger logger = Logger.getLogger(TRetEnvEventoTO.class);

	public static TRetEnvEvento parseResultToObj(NfeRecepcaoEventoResult result) {
		logger.info("Parse return of webservice");
		String xml = result.getExtraElement().toString();
		logger.debug("XML Retorno....: " + xml);
		JAXBContext context;
		try {
			context = JAXBContext
					.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			@SuppressWarnings("unchecked")
			JAXBElement<TRetEnvEvento> element = (JAXBElement<TRetEnvEvento>) unmarshaller
					.unmarshal(new StringReader(xml));
			logger.debug("Parse return of webservice to object sucessful!");
			return element.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void info(String msg) {
		Logger.getLogger(TRetEnvEventoHelper.class).info(msg);
		BifrostView.infoView(msg);
	}
}
