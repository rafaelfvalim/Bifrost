package br.octa.utils;

import br.octa.enums.URLServicosEnum;

public class EnvioEventoUtils {

	public static String getUrl() {
		if ("1".equals(ConfigUtils.getTpAmb()))
			return URLServicosEnum.PRODUCAO_RECEPCAOEVENTO.value();

		return URLServicosEnum.HOMOLOGACAO_RECEPCAOEVENTO.value();
	}

}
