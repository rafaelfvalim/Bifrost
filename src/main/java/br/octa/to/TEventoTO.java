package br.octa.to;

import org.apache.log4j.Logger;
import org.w3._2000._09.xmldsig_.SignatureType;

import br.inf.portalfiscal.nfe.TEvento;

public class TEventoTO {
	final static Logger logger = Logger.getLogger(TEventoTO.class);
	TEvento evento;
	TEvento.InfEvento infEvento;
	TEvento.InfEvento.DetEvento detEvento;

	public TEventoTO() {
	}

	public TEventoTO(SignatureType signature, String versao, String chNFe, String cnpj, String cOrgao, String cpf,
			String dhEvento, String id, String nSeqEvento, String tpAmb, String tpEvento, String verEvento,
			String descEvento, String versaoDetEvento, String xJust) {
		evento = new TEvento();
		infEvento = new TEvento.InfEvento();
		detEvento = new TEvento.InfEvento.DetEvento();

		detEvento.setDescEvento(descEvento);
		detEvento.setVersao(versaoDetEvento);
		detEvento.setXJust(xJust);

		infEvento.setChNFe(chNFe);
		infEvento.setCNPJ(cnpj);
		infEvento.setCOrgao(cOrgao);
		infEvento.setCPF(cpf);
		infEvento.setDetEvento(detEvento);

		evento.setSignature(signature);
		evento.setVersao(versao);
		evento.setInfEvento(infEvento);

	}

	public TEvento getEvento() {
		return evento;
	}

	public TEvento.InfEvento getInfEvento() {
		return infEvento;
	}

	public TEvento.InfEvento.DetEvento getDetEvento() {
		return detEvento;
	}
}
