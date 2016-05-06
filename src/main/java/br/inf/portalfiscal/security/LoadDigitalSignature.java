package br.inf.portalfiscal.security;

import java.security.Security;
import java.util.List;

import org.apache.log4j.Logger;

import br.octa.utils.ConfigUtils;

/**
 * Classe que carrega o certificado digital tipo A1 para comunicação com o
 * SEFAZ
 * 
 * @author Rafael Valim
 * 
 */
public class LoadDigitalSignature {

	/**
	 * Log4j
	 */
	private static Logger logger = Logger.getLogger(LoadDigitalSignature.class);

	/**
	 * Construtor padrão gatilho para iniciar o set das propriedades do sistema
	 */
	public LoadDigitalSignature() {
		setaConfiguracoes();
	}

	/**
	 * Seta as configurações do certificado digital no Sistema Operacional
	 */
	public void setaConfiguracoes() {
		// TODO Auto-generated constructor stub
		ConfigUtils config = new ConfigUtils();
		String caminhoDoCertificadoDoCliente =config.getArquivoCertificado();
		String senhaDoCertificadoDoCliente =config.getSenhaCerticado();
		String arquivoCacertsGeradoParaCadaEstado = config.getNFeCacerts();

		logger.debug(">>>>>>Carrega Certificados");

		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		System.clearProperty("javax.net.ssl.keyStore");
		System.clearProperty("javax.net.ssl.keyStorePassword");
		System.clearProperty("javax.net.ssl.trustStore");
		System.setProperty("javax.net.ssl.keyStore", caminhoDoCertificadoDoCliente);
		System.setProperty("javax.net.ssl.keyStorePassword", senhaDoCertificadoDoCliente);
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", arquivoCacertsGeradoParaCadaEstado);

	}

}
