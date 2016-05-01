package br.octa.enums;

public enum TpAmbEnum {

	HOMLOGACAO("1"), PRODUCAO("2");
	String valor;

	TpAmbEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
