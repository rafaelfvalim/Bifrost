package br.octa.to;

import java.util.List;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.TretEvento;

public class TRetEnvEventoTO extends TRetEnvEvento {
	final static Logger logger = Logger.getLogger(TRetEnvEventoTO.class);
	TRetEnvEvento retEnvEvento;

	public TRetEnvEventoTO(String xMotivo, String versao, String verAplic, String tpAmb, List<TretEvento> listRetEvento,
			String idLote, String cStat, String cOrgao) {
		retEnvEvento = new TRetEnvEvento();
		retEnvEvento.setCOrgao(cOrgao);
		retEnvEvento.setCStat(cStat);
		retEnvEvento.setIdLote(idLote);
		retEnvEvento.setTpAmb(tpAmb);
		retEnvEvento.setVerAplic(verAplic);
		retEnvEvento.setVersao(versao);
		retEnvEvento.setXMotivo(xMotivo);
		for (TretEvento retEvento : listRetEvento) {
			retEnvEvento.getRetEvento().add(retEvento);
		}

	}

	public TRetEnvEvento getRetEnvEvento() {
		return retEnvEvento;
	}
}
