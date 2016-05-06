package br.octa.to;

import br.inf.portalfiscal.nfe.TEvento.InfEvento.DetEvento;

public  class DetEventoTO extends DetEvento {
	public DetEventoTO() {
		super();
	}

	public DetEventoTO(String descEvento, String versao, String xJust) {
		super();
		this.descEvento = descEvento;
		this.versao = versao;
		this.xJust = xJust;
	}

}