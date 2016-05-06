package br.octa.enums;

public enum TipoInterfaceEnum {

	ENVCONFRECEBTO("EnvConfRecbto"), RETENVCONFRECEBTO("RetEnvConfRecbto"), CONSNFEDEST("ConsNfeDest"), RETCONSNFEDEST(
			"RetConsNfeDest"), DOWNLOADNFE("DownloadNfe"), RETDOWNLOADNFE("RetDownloadNfe");
	String valor;

	private TipoInterfaceEnum(String valor) {
		this.valor = valor;
	}

	public String value() {
		return valor;
	}
}
