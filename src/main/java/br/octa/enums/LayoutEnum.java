package br.octa.enums;

public enum LayoutEnum {
	NFECCONSULTA("NFECCONSULTA"), NFEEVENTO("NFEEVENTO"), RETORNO_NFECCONSULTA("RET_NFECCONSULTA"), RETORNO_NFEEVENTO(
			"RET_NFEEVENTO");
	String valor = "";

	LayoutEnum(String valor) {
		this.valor = valor;
	}

	public String valor() {
		return valor;
	}
}
