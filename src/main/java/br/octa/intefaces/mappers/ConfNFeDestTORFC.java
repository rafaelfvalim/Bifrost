package br.octa.intefaces.mappers;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import br.inf.portalfiscal.nfe.TRetConsNFeDest;

public class ConfNFeDestTORFC {

	public static void setRFCData(JCoFunction function, TRetConsNFeDest ret) {
		JCoTable tretorno = function.getTableParameterList().getTable("T_RETORNO");

		if (ret.getRet().size() == 0) {
			//Se a mensagem vier sem conteudo
			tretorno.appendRow();
			headerRFC(tretorno, ret);
		}

		for (TRetConsNFeDest.Ret r : ret.getRet()) {
			tretorno.appendRow();
			TRetConsNFeDest.Ret.ResNFe resNFe = r.getResNFe();
			TRetConsNFeDest.Ret.ResCanc resCanc = r.getResCanc();
			TRetConsNFeDest.Ret.ResCCe resCCe = r.getResCCe();
			// header
			headerRFC(tretorno, ret);
			// table RETs
			// ResNFe
			tretorno.setValue("IR12_NSU", resNFe.getNSU());
			tretorno.setValue("IR13_CHNFE", resNFe.getChNFe());
			tretorno.setValue("IR14_CNPJ", resNFe.getCNPJ());
			tretorno.setValue("IR15_CPF", resNFe.getCPF());
			tretorno.setValue("IR16_XNOME", resNFe.getXNome());
			tretorno.setValue("IR17_IE", resNFe.getIE());
			tretorno.setValue("IR18_DEMI", resNFe.getDEmi());
			tretorno.setValue("IR19_TPNF", resNFe.getTpNF());
			tretorno.setValue("IR20_VNF", resNFe.getVNF());
			tretorno.setValue("IR21_DIGVAL", resNFe.getDigVal());
			tretorno.setValue("IR22_DHRECBTO", resNFe.getDhRecbto().toGregorianCalendar().getTime().toString());
			tretorno.setValue("IR23_CSITNFE", resNFe.getCSitNFe());
			tretorno.setValue("IR24_CSITCONF", resNFe.getCSitConf());
			// ResCanc
			tretorno.setValue("IR26_NSU", resCanc.getNSU());
			tretorno.setValue("IR27_CHNFE", resCanc.getChNFe());
			tretorno.setValue("IR28_CNPJ", resCanc.getCNPJ());
			tretorno.setValue("IR29_CPF", resCanc.getCPF());
			tretorno.setValue("IR30_XNOME", resCanc.getXNome());
			tretorno.setValue("IR31_IE", resCanc.getIE());
			tretorno.setValue("IR32_DEMI", resCanc.getDEmi());
			tretorno.setValue("IR33_TPNF", resCanc.getTpNF());
			tretorno.setValue("IR34_VNF", resCanc.getVNF());
			tretorno.setValue("IR35_DIGVAL", resCanc.getDigVal());
			tretorno.setValue("IR36_DHRECBTO", resCanc.getDhRecbto());
			tretorno.setValue("IR37_CSITNFE", resCanc.getCSitNFe());
			tretorno.setValue("IR38_CSITCONF", resCanc.getCSitConf());
			// ResCCe
			tretorno.setValue("IR40_NSU", resCCe.getNSU());
			tretorno.setValue("IR41_CHNFE", resCCe.getChNFe());
			tretorno.setValue("IR42_DHEVENTO", resCCe.getDhEvento());
			tretorno.setValue("IR43_TPEVENTO", resCCe.getTpEvento());
			tretorno.setValue("IR44_NSEQEVENTO", resCCe.getNSeqEvento());
			tretorno.setValue("IR45_DESCEVENTO", resCCe.getDescEvento());
			tretorno.setValue("IR46_XCORRECAO", resCCe.getXCorrecao());
			tretorno.setValue("IR47_TPNF", resCCe.getTpNF());
			tretorno.setValue("IR48_DHRECBTO", resCCe.getDhRecbto().toGregorianCalendar().getTime());

		}

	}

	private static void headerRFC(JCoTable tretorno, TRetConsNFeDest ret) {
		tretorno.setValue("IR02_VERSAO", ret.getVersao());
		tretorno.setValue("IR03_TPAMB", ret.getTpAmb());
		tretorno.setValue("IR04_VERAPLIC", ret.getVerAplic());
		tretorno.setValue("IR05_CSTAT", ret.getCStat());
		tretorno.setValue("IR06_XMOTIVO", ret.getXMotivo());
		tretorno.setValue("IR07_DHRESP", ret.getDhResp().toGregorianCalendar().getTime().toString());
		tretorno.setValue("IR08_INDCONT", ret.getIndCont());
		tretorno.setValue("IR09_ULTNSU", ret.getUltNSU());
	}
}
