package br.octa.to;

import java.util.List;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TDownloadNFe;

public class TDownloadNFeTO {
	final static Logger logger = Logger.getLogger(TDownloadNFeTO.class);

	private TDownloadNFe downloadNFe;
	
	public TDownloadNFeTO(String cnpj, String tpAmb, String versao, String xServ, List<String> chNFe) {
		downloadNFe = new TDownloadNFe();
		downloadNFe.setCNPJ(cnpj);
		downloadNFe.setTpAmb(tpAmb);
		downloadNFe.setVersao(versao);
		downloadNFe.setXServ(xServ);
	}

	public TDownloadNFe getTDownloadNFe(){
		return downloadNFe;
	}
}
