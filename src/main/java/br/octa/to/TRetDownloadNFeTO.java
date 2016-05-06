package br.octa.to;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import br.inf.portalfiscal.nfe.TRetDownloadNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe;

public class TRetDownloadNFeTO  {
	TRetDownloadNFe retDownloadNFe;
	
	
	public TRetDownloadNFeTO(String xMotivo, String versao, String verAplic, String tpAmb, List<RetNFe> listRetNFe,
			XMLGregorianCalendar dhResp, String cStat) {
		retDownloadNFe = new TRetDownloadNFe();
		retDownloadNFe.setCStat(cStat);
		retDownloadNFe.setDhResp(dhResp);
		retDownloadNFe.setTpAmb(tpAmb);
		retDownloadNFe.setVerAplic(verAplic);
		retDownloadNFe.setVersao(versao);
		retDownloadNFe.setXMotivo(xMotivo);
		
		
		for (RetNFe retNFe : listRetNFe) {
			retDownloadNFe.getRetNFe().add(retNFe);
		}
	}

	public TRetDownloadNFe geTRetDownloadNFe(){
		return retDownloadNFe;
	}
}
