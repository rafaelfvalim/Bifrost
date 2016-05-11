package br.octa.test;

import br.octa.helpers.ConsNFeDestHelper;
import br.octa.to.TConsNFeDestTO;

public class ConsNFeDestServiceSyncTest {

	public static void main(String[] args) {
		String versao = "1.01";
		String tpAmb = "2";
		String xServ = "CONSULTAR NFE DEST";
		String cnpj = "24144943000156";
		String indNFe = "0";
		String indEmi = "0";
		String ultNSU = "0";

		TConsNFeDestTO consNFeDestTO = new TConsNFeDestTO(cnpj, indEmi, tpAmb, ultNSU, versao, xServ, indNFe);
		System.out.println(ConsNFeDestHelper.toXML(consNFeDestTO.geTConsNFeDest()));
		//
		// ConsNFeDestServiceWS service = new ConsNFeDestServiceWS();
		//
		// TRetConsNFeDest ret =
		// service.sendConsNfeDest(consNFeDestTO.geTConsNFeDest());

		// System.out.println(RetConsNFeDestHelper.toXML(ret));
	}

}
