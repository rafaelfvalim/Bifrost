package br.octa.to;

import br.inf.portalfiscal.nfe.TConsNFeDest;

public class TConsNFeDestTO {
	TConsNFeDest consNFeDest;

	public TConsNFeDestTO(String cnpj, String indEmi, String tpAmb, String ultNSU, String versao, String xServ,
			String indNFe) {

		consNFeDest = new TConsNFeDest();
		consNFeDest.setCNPJ(cnpj);
		consNFeDest.setIndEmi(indEmi);
		consNFeDest.setIndNFe(indNFe);
		consNFeDest.setTpAmb(tpAmb);
		consNFeDest.setUltNSU(ultNSU);
		consNFeDest.setVersao(versao);
		consNFeDest.setXServ(xServ);
	}

	public TConsNFeDest geTConsNFeDest() {
		return consNFeDest;
	}
}
