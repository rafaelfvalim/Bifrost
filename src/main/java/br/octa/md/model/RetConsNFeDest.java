package br.octa.md.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.ObjectFactory;
import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResCCe;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResCanc;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResNFe;
import br.inf.portalfiscal.nfe.TRetEnvEvento;

public class RetConsNFeDest {
	final static Logger logger = Logger.getLogger(RetConsNFeDest.class);
	TRetConsNFeDest retConsNFeDest = null;

	
	public TRetConsNFeDest.Ret.ResNFe createResNfe(String nome, String vnf, String tpNf, String nsu, String ie, String digVal, XMLGregorianCalendar dhRecbto, String dEmi, String cSitNfe, String cSitCon, String cpf, String cnpj, String chNfe){
		TRetConsNFeDest.Ret.ResNFe resNfe = new TRetConsNFeDest.Ret.ResNFe();
		resNfe.setChNFe(chNfe);
		resNfe.setCNPJ(cnpj);
		resNfe.setChNFe(cpf);
		resNfe.setCSitConf(cSitCon);
		resNfe.setCSitNFe(cSitNfe);
		resNfe.setDEmi(dEmi);
		resNfe.setDhRecbto(dhRecbto);
		resNfe.setDigVal(digVal);
		resNfe.setIE(ie);
		resNfe.setNSU(nsu);
		resNfe.setTpNF(tpNf);
		resNfe.setVNF(vnf);
		resNfe.setXNome(nome);
	return resNfe;
	}
	
	public TRetConsNFeDest.Ret.ResCCe createResCCe(String xCorrecao, String tpNf, String tpEnvento, String nsu, String nSeqEvento, XMLGregorianCalendar dhRebto, String dhEvento, String descEnvento, String chNfe){
		TRetConsNFeDest.Ret.ResCCe resCce = new TRetConsNFeDest.Ret.ResCCe();
		resCce.setChNFe(chNfe);
		resCce.setDescEvento(descEnvento);
		resCce.setDhEvento(dhEvento);
		resCce.setDhRecbto(dhRebto);
		resCce.setNSeqEvento(nSeqEvento);
		resCce.setNSU(nsu);
		resCce.setTpEvento(tpEnvento);
		resCce.setTpNF(tpNf);
		resCce.setXCorrecao(xCorrecao);
		return resCce;
	}
	

	public TRetConsNFeDest.Ret.ResCanc createResCanc(String nome, String vnf, String tpNf, String nsu, String ie, String digVal, XMLGregorianCalendar dhRecbto, String dEmi, String cStiNfe, String csSitCon, String cpf, String cnpj, String chNfe){
		TRetConsNFeDest.Ret.ResCanc resCanc =  new TRetConsNFeDest.Ret.ResCanc();
		resCanc.setChNFe(chNfe);
		resCanc.setCNPJ(cnpj);
		resCanc.setCPF(cpf);
		resCanc.setCSitConf(csSitCon);
		resCanc.setCSitNFe(cStiNfe);
		resCanc.setDEmi(dEmi);
		resCanc.setDhRecbto(dhRecbto);
		resCanc.setDigVal(digVal);
		resCanc.setIE(ie);
		resCanc.setNSU(nsu);
		resCanc.setTpNF(tpNf);
		resCanc.setVNF(vnf);
		resCanc.setXNome(nome);
		return resCanc;
	}
	
	
	public TRetConsNFeDest.Ret createRet(ResCanc resCanc, ResCCe resCce, ResNFe resNfe) {
		TRetConsNFeDest.Ret ret = new TRetConsNFeDest.Ret();
		ret.setResCanc(resCanc);
		ret.setResCCe(resCce);
		ret.setResNFe(resNfe);
		return ret;
	}

	public TRetConsNFeDest createRetConsNFeDest(List<Ret> listRet, String cStat, XMLGregorianCalendar dhResp, String indCond,
			String tpAmb, String ulTnsu, String verAplic, String xMotivo, String versao) {
		logger.info("Gerando envento da mensagem");
		retConsNFeDest = new TRetConsNFeDest();
		for (Ret ret : listRet) {
			retConsNFeDest.getRet().add(ret);
		}
        
		retConsNFeDest.setCStat(cStat);
		retConsNFeDest.setDhResp(dhResp);
		retConsNFeDest.setIndCont(indCond);
		retConsNFeDest.setTpAmb(tpAmb);
		retConsNFeDest.setUltNSU(ulTnsu);
		retConsNFeDest.setVerAplic(verAplic);
		retConsNFeDest.setVersao(versao);
		retConsNFeDest.setXMotivo(xMotivo);

		return retConsNFeDest;
	}

	public String toXML() {
		logger.info("Gerando XML EnvConfRecebto");
		try {
			return strValueOf(retConsNFeDest);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String strValueOf(TRetConsNFeDest retConsNFeDest) throws JAXBException {
		logger.info("Criando XML do Objeto TEnvEvento ");
		JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
		Marshaller marshaller = context.createMarshaller();
		JAXBElement<TRetConsNFeDest> element = new ObjectFactory().createRetConsNFeDest(retConsNFeDest);
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

	public TRetConsNFeDest createRetConsNFeDestFromXMl(String xmlEnvEvento) {
		logger.info("Gerando envento em lote da mensagem");
		try {
			JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JAXBElement<TRetConsNFeDest> element = (JAXBElement<TRetConsNFeDest>) unmarshaller
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
