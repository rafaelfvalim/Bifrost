package br.octa.enums;

public enum VersaoDadosEnum {

	V1_00("1.00"), 
	V1_01("1.01"), 
	V2_00("2.00"), 
	V3_10("3.10");

	String codigoEnvento;

	VersaoDadosEnum(String codigoEnvento) {
		this.codigoEnvento = codigoEnvento;
	}

	public String valor() {
		return codigoEnvento;
	}

}
