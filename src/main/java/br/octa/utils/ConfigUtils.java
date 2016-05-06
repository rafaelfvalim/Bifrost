package br.octa.utils;

public class ConfigUtils {
		
	
	public static String getArquivoCertificado() {
		return PropertyUtils.getValue("arquivo_certificado");
	}

	public static String getSenhaCerticado() {
		return PropertyUtils.getValue("senha_certificado");
	}

	public static String getNFeCacerts() {
		return PropertyUtils.getValue("nfecacerts");
	}
	public static String getCompanyName() {
		return PropertyUtils.getValue("company_name");
	}
	
	public static String getThreads(String name){
		return PropertyUtils.getValue(name);
	}

}
