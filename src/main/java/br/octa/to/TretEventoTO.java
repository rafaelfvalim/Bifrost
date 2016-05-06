package br.octa.to;

import br.inf.portalfiscal.nfe.TretEvento;

public class TretEventoTO {

	TretEvento evento;
	TretEvento.InfEvento infEvento;

	public TretEventoTO(String chNFe, String cnpjDest, String cOrgao, String cpfDest, String cStat, String dhRegEvento,
			String emailDest, String idInfEvent, String nProt, String nSeqEvento, String tpAmb, String tpEvento,
			String verAplic, String xEvento, String xMotivo, String versao) {
		evento = new TretEvento();
		infEvento = new TretEvento.InfEvento();
		infEvento.setChNFe(chNFe);
		infEvento.setCNPJDest(cnpjDest);
		infEvento.setCOrgao(cOrgao);
		infEvento.setCPFDest(cpfDest);
		infEvento.setCStat(cStat);
		infEvento.setDhRegEvento(dhRegEvento);
		infEvento.setEmailDest(emailDest);
		infEvento.setId(idInfEvent);
		infEvento.setNProt(nProt);
		infEvento.setNSeqEvento(nSeqEvento);
		infEvento.setTpAmb(tpAmb);
		infEvento.setTpEvento(tpEvento);
		infEvento.setVerAplic(verAplic);
		infEvento.setXEvento(xEvento);
		infEvento.setXMotivo(xMotivo);
		evento.setInfEvento(infEvento);
		evento.setSignature(null);
		evento.setVersao(versao);
	}

	public TretEvento getRetEvento() {
		return evento;
	}

	public TretEvento.InfEvento getRetInfEvento() {
		return infEvento;
	}

}
