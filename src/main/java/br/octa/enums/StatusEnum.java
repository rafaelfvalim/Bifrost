package br.octa.enums;

public enum StatusEnum {
	SUCESSO("Sucesso"),ERRO("Erro");

	String valor = "";

	StatusEnum(String valor) {
		this.valor = valor;
	}

	public String valor() {
		return valor;
	}
}
