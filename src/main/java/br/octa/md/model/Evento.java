package br.octa.md.model;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.nfe.TEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento;
import br.inf.portalfiscal.nfe.TEvento.InfEvento.DetEvento;

public class Evento {
	final static Logger logger = Logger.getLogger(Evento.class);

	public TEvento createEvento(String versaoLeiaute, String id, String cOrgao, String tpAmb, String CNPJ, String CPF,
			String chNFe, String dhEvento, String tpEvento, String nSeqEvento, String verEvento, String versaoEvento,
			String descEvento, String xJust, String Signature) {

		DetEvento detEvento = new DetEvento();
		detEvento.setDescEvento(descEvento);
		detEvento.setVersao(versaoEvento);
		detEvento.setXJust(xJust);

		InfEvento infEvento = new InfEvento();
		infEvento.setId(id);
		infEvento.setCOrgao(cOrgao);
		infEvento.setTpAmb(tpAmb);
		infEvento.setCNPJ(CNPJ);
		infEvento.setCPF(CPF);
		infEvento.setChNFe(chNFe);
		infEvento.setDhEvento(dhEvento);
		infEvento.setTpEvento(tpEvento);
		infEvento.setNSeqEvento(nSeqEvento);
		infEvento.setVerEvento(verEvento);
		infEvento.setDetEvento(detEvento);

		TEvento envento = new TEvento();
		envento.setInfEvento(infEvento);
		//envento.setSignature();
		envento.setVersao(versaoEvento);
		return envento;
	}
	

}
