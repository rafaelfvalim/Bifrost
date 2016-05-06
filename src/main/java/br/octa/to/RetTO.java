package br.octa.to;

import javax.xml.datatype.XMLGregorianCalendar;

import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResCCe;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResCanc;
import br.inf.portalfiscal.nfe.TRetConsNFeDest.Ret.ResNFe;

public class RetTO {

	private Ret ret = new Ret();
	private ResCanc resCanc = new ResCanc();
	private ResNFe resNFe = new ResNFe();
	private ResCCe resCCe = new ResCCe();

	public RetTO() {
	}

	public ResCanc getResCanc() {
		return resCanc;
	}
	public ResNFe getResNFe() {
		return resNFe;
	}
	public ResCCe getResCCe() {
		return resCCe;
	}
	
	public Ret getRet(){
		return ret;
	}
	public void setResCanc(String chNFe, String cnpj, String cpf, String cSitCon, String cSitNFe, String dEmi, XMLGregorianCalendar DhRecbto, String digVal, String ie, String nsu, String tpNF, String vnf, String xNome) {
		resCanc.setChNFe(chNFe);
		resCanc.setCNPJ(cnpj);
		resCanc.setCPF(cpf);
		resCanc.setCSitConf(cSitCon);
		resCanc.setCSitNFe(cSitNFe);
		resCanc.setDEmi(dEmi);
		resCanc.setDhRecbto(DhRecbto);
		resCanc.setDigVal(digVal);
		resCanc.setIE(ie);
		resCanc.setNSU(nsu);
		resCanc.setTpNF(tpNF);
		resCanc.setVNF(vnf);
		resCanc.setXNome(xNome);
		ret.setResCanc(resCanc);
	}
	public void setResCCe(String chNFe, String desEvento, String dhEvento, XMLGregorianCalendar DhRecbto, String nSeqEvento, String nsu, String tpEvento, String tpNF, String xCorrecao) {
		resCCe.setChNFe(chNFe);
		resCCe.setDescEvento(desEvento);
		resCCe.setDhEvento(dhEvento);
		resCCe.setDhRecbto(DhRecbto);
		resCCe.setNSeqEvento(nSeqEvento);
		resCCe.setNSU(nsu);
		resCCe.setTpEvento(tpEvento);
		resCCe.setTpNF(tpNF);
		resCCe.setXCorrecao(xCorrecao);
		ret.setResCCe(resCCe);
	}

	public void setResNFe(String chNFe, String cnpj, String cpf, String cSitCon, String cSitNFe, String dEmi, XMLGregorianCalendar DhRecbto, String digVal, String ie, String nsu, String tpNF, String vnf, String xNome) {
		resNFe.setChNFe(chNFe);
		resNFe.setCNPJ(cnpj);
		resNFe.setCPF(cpf);
		resNFe.setCSitConf(cSitCon);
		resNFe.setCSitNFe(cSitNFe);
		resNFe.setDEmi(dEmi);
		resNFe.setDhRecbto(DhRecbto);
		resNFe.setDigVal(digVal);
		resNFe.setIE(ie);
		resNFe.setNSU(nsu);
		resNFe.setTpNF(tpNF);
		resNFe.setVNF(vnf);
		resNFe.setXNome(xNome);
		ret.setResNFe(resNFe);
	}


}