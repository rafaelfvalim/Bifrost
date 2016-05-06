package br.octa.mappers;

import com.sap.conn.jco.JCoFunction;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.octa.to.TConsNFeDestTO;

public class SAPConsultaNFeDestinadasMapper {

	public TConsNFeDest SapTOXml(JCoFunction function) {
		
		// TODO aqui mapear a RFC
		String cnpj = null;
		String indEmi = null;
		String tpAmb = null;
		String ultNSU = null;
		String versao = null;
		String xServ = null;
		String indNFe = null;
		TConsNFeDestTO tConsNFeDestTO = new TConsNFeDestTO(cnpj, indEmi, tpAmb, ultNSU, versao, xServ, indNFe);
		return tConsNFeDestTO.geTConsNFeDest();
	}

	public void XmlTOSap(TRetConsNFeDest ret) {

	}

}
