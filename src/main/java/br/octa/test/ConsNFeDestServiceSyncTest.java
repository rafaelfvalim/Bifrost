package br.octa.test;

import br.inf.portalfiscal.nfe.TRetConsNFeDest;
import br.octa.enums.UFEnum;
import br.octa.enums.URLServicosEnum;
import br.octa.enums.VersaoDadosEnum;
import br.octa.helpers.ConsNFeDestHelper;
import br.octa.helpers.RetConsNFeDestHelper;
import br.octa.service.nfe.ws.ConsNFeDestServiceWS;
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
		ConsNFeDestServiceWS service = new ConsNFeDestServiceWS();

		UFEnum uf = UFEnum.SP;
		VersaoDadosEnum versaoEnum = VersaoDadosEnum.V1_01;
		TRetConsNFeDest ret = service.sendConsNfeDest(URLServicosEnum.PRODUCAO_NFECONSULTADEST,
				consNFeDestTO.geTConsNFeDest(), versaoEnum, uf);

		System.out.println(RetConsNFeDestHelper.toXML(ret));
	}

}
