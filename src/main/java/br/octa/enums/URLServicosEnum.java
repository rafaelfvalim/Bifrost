package br.octa.enums;

public enum URLServicosEnum {
	PRODUCAO_RECEPCAOEVENTO("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"),
	PRODUCAO_NFEDISTRIBUICAODFE("https://www1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx"),
	PRODUCAO_NFECONSULTADEST("https://www.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx"),
	PRODUCAO_NFEDOWNLOADNF("https://www.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx"),
	
	HOMOLOGACAO_RECEPCAOEVENTO("https://hom.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx"),
	HOMOLOGACAO_NFEDISTRIBUICAODFE("https://hom.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx"),
	HOMOLOGACAO_NFECONSULTADEST("https://hom.nfe.fazenda.gov.br/NFeConsultaDest/NFeConsultaDest.asmx"),
	HOMOLOGACAO_NFEDOWNLOADNF("https://hom.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx");
	
	String valor;

	private URLServicosEnum(String valor) {
		this.valor = valor;
	}

	public String value() {
		return valor;
	}
}
