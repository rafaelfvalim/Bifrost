package br.octa.md.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TDownloadNFe;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TRetDownloadNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe.ProcNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe.ProcNFeGrupoZip;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class RetDownloadNFe {
	final static Logger logger = Logger.getLogger(RetDownloadNFe.class);

	TRetDownloadNFe retDownloadNfe = null;
	
	public TRetDownloadNFe.RetNFe.ProcNFe createProcNFe(String schema, Element any){
		TRetDownloadNFe.RetNFe.ProcNFe procNfe = new TRetDownloadNFe.RetNFe.ProcNFe();
		procNfe.setAny(any);
		procNfe.setSchema(schema);
		return procNfe;
	}
	
	public TRetDownloadNFe.RetNFe.ProcNFeGrupoZip createProcNFeGrupoZip(byte[] nfeZip, byte[] protNfeZip){
		TRetDownloadNFe.RetNFe.ProcNFeGrupoZip procNFeGrupoZip = new TRetDownloadNFe.RetNFe.ProcNFeGrupoZip();
		procNFeGrupoZip.setNFeZip(nfeZip);
		procNFeGrupoZip.setProtNFeZip(protNfeZip);
		return procNFeGrupoZip;
	}
	
	public TRetDownloadNFe.RetNFe createRetNfe(String cHNfe, String cStat, ProcNFe procNfe,
			ProcNFeGrupoZip procNFeGrupoZip, String xMotivo) {
		TRetDownloadNFe.RetNFe retNFe = new TRetDownloadNFe.RetNFe();
		retNFe.setChNFe(cHNfe);
		retNFe.setCStat(cStat);
		retNFe.setProcNFe(procNfe);
		retNFe.setProcNFeGrupoZip(procNFeGrupoZip);
		retNFe.setXMotivo(xMotivo);
		return retNFe;
	}

	public TRetDownloadNFe createDownloadNfe(String cStat, XMLGregorianCalendar dhResp, String tpAmb, String verAplic,
			String versao, String xMotivo, List<TRetDownloadNFe.RetNFe> listRetNfe) {
		logger.info("Gerando envento em lote da mensagem");
		retDownloadNfe.setCStat(cStat);
		retDownloadNfe.setDhResp(dhResp);
		retDownloadNfe.setTpAmb(tpAmb);
		retDownloadNfe.setVerAplic(verAplic);
		retDownloadNfe.setVersao(versao);
		retDownloadNfe.setXMotivo(xMotivo);
		for (RetNFe retNFe : listRetNfe) {
			retDownloadNfe.getRetNFe().add(retNFe);		
		}
		return retDownloadNfe;
	}

	public String toXML() {
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
		sw_aux = sw_aux.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" ", "");
		return sw_aux;
	}

	public TRetDownloadNFe createRetDownloadNfeFromXMl(String xmlRetDownloadNFe) {
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
