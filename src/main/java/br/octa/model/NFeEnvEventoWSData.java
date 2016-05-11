package br.octa.model;

public class NFeEnvEventoWSData {

	private String versaoDados;
	private String url;
	private String uf;

	public NFeEnvEventoWSData(String versaoDados, String url, String uf) {
		this.versaoDados = versaoDados;
		this.url = url;
		this.uf = uf;
	}

	public String getVersaoDados() {
		return versaoDados;
	}

	public void setVersaoDados(String versaoDados) {
		this.versaoDados = versaoDados;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
