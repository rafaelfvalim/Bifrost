package br.octa.to;

import java.util.List;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;

public class TEnvEventoTO {
	final static Logger logger = Logger.getLogger(TEnvEventoTO.class);

	TEnvEvento envEvento;

	public TEnvEventoTO(List<TEvento> evento, String idLote, String versao) {
		envEvento = new TEnvEvento();
		for (TEvento tEvento : evento) {
			envEvento.getEvento().add(tEvento);
		}
		envEvento.setVersao(versao);
		envEvento.setIdLote(idLote);

	}

	public TEnvEvento getTEnvEvento() {
		return envEvento;
	}
}
