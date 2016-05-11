package br.octa.utils;

import br.octa.enums.URLServicosEnum;

public class ConsultaNFeUtils {

	public static String getUrl() {
		
		if ("1".equals(ConfigUtils.getTpAmb()))
			return URLServicosEnum.PRODUCAO_NFECONSULTADEST.value();

		return URLServicosEnum.HOMOLOGACAO_NFECONSULTADEST.value();
	}

}
