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

	public TEventoTO(String versao, String id, String cOrgao, String tpAmb, String cnpj, String cpf, String chNFe, String dhEvento, String tpEvento, String nSeqEvento, String verEvento, String versaoDetEvento, String descEvento, String xJust) {
		
		evento = new TEvento();
		infEvento = new TEvento.InfEvento();
		detEvento = new TEvento.InfEvento.DetEvento();
		
		evento.setSignature(null);
		evento.setVersao(versao);
		
		infEvento.setId(id);
		infEvento.setCOrgao(cOrgao); 
		infEvento.setTpAmb(tpAmb);
		infEvento.setCNPJ(cnpj);
		infEvento.setCPF(cpf);
		infEvento.setChNFe(chNFe);
		infEvento.setDhEvento(dhEvento);
		infEvento.setTpEvento(tpEvento);
		infEvento.setNSeqEvento(nSeqEvento);
		infEvento.setVerEvento(verEvento);
		
		detEvento.setVersao(versaoDetEvento);
		detEvento.setDescEvento(descEvento);
		detEvento.setXJust(xJust);
		infEvento.setDetEvento(detEvento);
		
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
