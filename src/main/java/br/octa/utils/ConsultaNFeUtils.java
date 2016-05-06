package br.octa.utils;

import br.octa.enums.UFEnum;
import br.octa.enums.URLServicosEnum;

public class ConsultaNFeUtils {

	public static URLServicosEnum getUrl(String tpAmb) {
		if ("1".equals(tpAmb))
			return URLServicosEnum.PRODUCAO_NFECONSULTADEST;

		return URLServicosEnum.HOMOLOGACAO_NFECONSULTADEST;
	}

	public static UFEnum getUFConsulta() {
		PropertyUtils prop = new PropertyUtils();
		switch (prop.getValue("codigo.ibege.uf")) {
		case "11":
			return UFEnum.RO;
		case "12":
			return UFEnum.AC;
		case "13":
			return UFEnum.AM;
		case "14":
			return UFEnum.RO;
		case "15":
			return UFEnum.PA;
		case "16":
			return UFEnum.AM;
		case "17":
			return UFEnum.TO;
		case "21":
			return UFEnum.MA;
		case "22":
			return UFEnum.PI;
		case "23":
			return UFEnum.CE;
		case "24":
			return UFEnum.RN;
		case "25":
			return UFEnum.PB;
		case "26":
			return UFEnum.PE;
		case "27":
			return UFEnum.AL;
		case "28":
			return UFEnum.SE;
		case "29":
			return UFEnum.BA;
		case "31":
			return UFEnum.MG;
		case "32":
			return UFEnum.ES;
		case "33":
			return UFEnum.RJ;
		case "35":
			return UFEnum.SP;
		case "41":
			return UFEnum.PR;
		case "42":
			return UFEnum.SC;
		case "43":
			return UFEnum.RS;
		case "50":
			return UFEnum.MS;
		case "51":
			return UFEnum.MT;
		case "52":
			return UFEnum.GO;
		case "53":
			return UFEnum.DF;
		default:
			return UFEnum.SP;
		}

	}
}
