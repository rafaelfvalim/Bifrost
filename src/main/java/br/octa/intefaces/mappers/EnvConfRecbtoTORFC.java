package br.octa.intefaces.mappers;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.TretEvento;

public class EnvConfRecbtoTORFC {

	public static void setRFCData(JCoFunction function, TRetEnvEvento ret) {
		JCoTable IT_RETORNO = function.getTableParameterList().getTable("IT_RETORNO");

		if (ret.getRetEvento().size() == 0) {
			//Se a mensagem vier sem conteudo
			IT_RETORNO.appendRow();
			headerRFC(IT_RETORNO, ret);
		}

		for (TretEvento r : ret.getRetEvento()) {
			IT_RETORNO.appendRow();
			headerRFC(IT_RETORNO, ret);
			IT_RETORNO.setValue("HR10_VERSAO", r.getVersao());

			TretEvento.InfEvento inf = new TretEvento.InfEvento();
			IT_RETORNO.setValue("HR12_ID", inf.getId());
			IT_RETORNO.setValue("HR13_TPAMB", inf.getTpAmb());
			IT_RETORNO.setValue("HR14_VERAPLIC", inf.getVerAplic());
			IT_RETORNO.setValue("HR15_CORGAO", inf.getCOrgao());
			IT_RETORNO.setValue("HR16_CSTAT", inf.getCStat());
			IT_RETORNO.setValue("HR17_XMOTIVO", inf.getXMotivo());
			IT_RETORNO.setValue("HR18_CHNFE", inf.getChNFe());
			IT_RETORNO.setValue("HR19_TPEVENTO", inf.getTpEvento());
			IT_RETORNO.setValue("HR20_XEVENTO", inf.getXEvento());
			IT_RETORNO.setValue("HR21_NSEQEVENTO", inf.getNSeqEvento());
			IT_RETORNO.setValue("HR22_CNPJDEST", inf.getCNPJDest());
			IT_RETORNO.setValue("HR23_CPFDEST", inf.getCPFDest());
			IT_RETORNO.setValue("HR24_EMAILDEST", inf.getEmailDest());
			IT_RETORNO.setValue("HR25_DHREGEVENTO", inf.getDhRegEvento());
			IT_RETORNO.setValue("HR26_NPROT", inf.getNProt());
		}
	}

	private static void headerRFC(JCoTable IT_RETORNO, TRetEnvEvento ret) {

		IT_RETORNO.setValue("HR02_VERSAO", ret.getVersao());
		IT_RETORNO.setValue("HR03_IDLOTE", ret.getIdLote());
		IT_RETORNO.setValue("HR04_TPAMB", ret.getTpAmb());
		IT_RETORNO.setValue("HR05_VERAPLIC", ret.getVerAplic());
		IT_RETORNO.setValue("HR06_CORGAO", ret.getCOrgao());
		IT_RETORNO.setValue("HR07_CSTAT", ret.getCStat());
		IT_RETORNO.setValue("HR08_XMOTIVO", ret.getXMotivo());

	}

}
