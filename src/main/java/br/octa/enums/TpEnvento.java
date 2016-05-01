package br.octa.enums;

public enum TpEnvento {

	CONFIRMACAO_DA_OPERACAO("210200"), 
	CIENCIA_DA_OPERACAO("210210"), 
	DESCONHECIMENTO_DA_OPERACAO("210220"), 
	OPERCAO_NAO_REALIZADA("2102140");

	String codigoEnvento;

	TpEnvento(String codigoEnvento) {
		this.codigoEnvento = codigoEnvento;
	}

	public String valor() {
		return codigoEnvento;
	}

}
