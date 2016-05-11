package br.octa.utils;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import br.inf.portalfiscal.security.Security;
import br.octa.server.server.sap.Server;
import br.octa.view.BifrostView;

public class ConfigUtils {

	public static String getArquivoCertificado() {
		return PropertyUtils.getValue("arquivo_certificado");
	}

	public static void setCNPJ(String cnpj) {
		PropertyUtils.setPropertyAndStore("cnpj", cnpj);
	}

	public static String getCNPJ() {
		return PropertyUtils.getValue("cnpj");
	}

	public static void setArquivoCertificado(String valor) {
		PropertyUtils.setPropertyAndStore("arquivo_certificado", valor);
	}

	private static String getSenha() {
		return PropertyUtils.getValue("senha_certificado");
	}

	public static void setSenhaCerticado(String valor) {
		criptografiaSenha(valor);
	}

	public static String getNFeCacerts() {
		return PropertyUtils.getValue("nfecacerts");
	}

	public static String getCompanyName() {
		return PropertyUtils.getValue("company_name");
	}

	public static String getThreads(String name) {
		return PropertyUtils.getValue(name);
	}

	public static String getTpAmb() {
		return PropertyUtils.getValue("sefaz.ambiente");
	}

	public static String getUF() {
		return PropertyUtils.getValue("codigo.ibege.uf");
	}

	public static String getConsultaVersaoDados() {
		return PropertyUtils.getValue("sefaz.consulta_destinatario.versao_dados");
	}

	public static String getEvnioEventoVersaoDados() {
		return PropertyUtils.getValue("sefaz.envio_evento.versao_dados");
	}

	public static String getFolderLog() {
		return PropertyUtils.getValue("log.sefaz.folder");
	}

	public static String setSenhaCertificado(String value) {
		return criptografiaSenha(value);
	}

	public static String getSenhaCertificado() {
		String senha = getSenha();
		if (senha.startsWith("ENC(") && senha.endsWith(")")) {
			return BlowFishUtils.decript(senha.substring(4, senha.length() - 1));
		}
		return "";
	}

	public static String criptografiaSenha(String senha) {
		if (senha != null) {
			if (!senha.equals("")) {
				criptToFile(senha);
			}
		}
		return senha;
	}

	private static void criptToFile(String senha) {
		PropertyUtils.setPropertyAndStore("senha_certificado",
				"ENC(" + BlowFishUtils.cript(senha).replace("\n", "") + ")");
	}

	public static boolean checkRequirements() {
		File certificado = new File(ConfigUtils.getArquivoCertificado());
		File caCert = new File(ConfigUtils.getNFeCacerts());
		File ABAP_AS_WITH_POOL = new File("ABAP_AS_WITH_POOL.jcoDestination");
		File ABAP_AS_WITHOUT_POOL = new File("ABAP_AS_WITHOUT_POOL.jcoDestination");
		File SERVER = new File("SERVER.jcoServer");
		if (Security.wihteHabbit()) {

			if (certificado.exists() && caCert.exists() && SERVER.exists() && ABAP_AS_WITH_POOL.exists()
					&& ABAP_AS_WITHOUT_POOL.exists()) {
				return true;
			} else {
				String msg = "Impossível iniciar o servidor, verifique a configuração";
				Logger.getLogger(Server.class).info(msg);
				BifrostView.infoView(msg);
				throw new RuntimeException(msg);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Olá \n Esse programa ainda não foi registrado \n Entre em contatno com a www.klustter.com.br");
			throw new RuntimeException("Programa não registrado!");
		}

	}
}
