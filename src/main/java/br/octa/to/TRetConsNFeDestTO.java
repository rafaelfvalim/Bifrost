package br.octa.to;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret;

public class TRetConsNFeDestTO  {
	final static Logger logger = Logger.getLogger(TRetConsNFeDestTO.class);
	TRetConsNFeDest retConsNFeDest;

	public TRetConsNFeDestTO(String cStat, XMLGregorianCalendar dhResp, String indCont, List<Ret> ret, String tpAmb,
			String ultNSU, String verAplic, String versao, String xMotivo) {
		retConsNFeDest = new TRetConsNFeDest();
		retConsNFeDest.setCStat(cStat);
		retConsNFeDest.setDhResp(dhResp);
		retConsNFeDest.setIndCont(indCont);
		retConsNFeDest.setTpAmb(tpAmb);
		retConsNFeDest.setUltNSU(ultNSU);
		retConsNFeDest.setVerAplic(verAplic);
		retConsNFeDest.setVersao(versao);
		retConsNFeDest.setXMotivo(xMotivo);
	
	}
	
	public TRetConsNFeDestTO(TRetConsNFeDest ret){
		retConsNFeDest = ret;
	}
	
	
	public TRetConsNFeDest getTRetConsNFeDest(){
		return retConsNFeDest;
	}
	

}
