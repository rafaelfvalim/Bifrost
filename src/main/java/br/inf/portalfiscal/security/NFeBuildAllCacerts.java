package br.inf.portalfiscal.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import br.octa.enums.UriCaCertsEnum;
import br.octa.utils.PropertyUtils;
import br.octa.view.BifrostView;

/**
 * Gera o arquivo com os certificados de todos os servidores da NFE. Baseado em
 * http://www.javac.com.br/jc/posts/list/34.page
 */
public class NFeBuildAllCacerts implements Runnable {

	@Override
	public void run() {

		final String CACERTS_NAME = "NFeCacerts";
		final String CACERTS_PATH = System.getProperty("user.dir") + "/src/main/resources/certificados/keystore";
		final char SEPARATOR = File.separatorChar;

		try {
			PropertyUtils prop = new PropertyUtils();
			char[] passphrase = "changeit".toCharArray();
			File file = new File(CACERTS_PATH + SEPARATOR + CACERTS_NAME);

			if (file.isFile()) {
				file.delete();
			}

			if (file.isFile() == false) {

				File dir = new File(System.getProperty("java.home") + SEPARATOR + "lib" + SEPARATOR + "security");
				file = new File(dir, CACERTS_NAME);
				if (file.isFile() == false) {
					file = new File(dir, "cacerts");
				}
			}

			info("| Loading KeyStore " + file + "...");
			InputStream in = new FileInputStream(file);
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(in, passphrase);
			in.close();

			// HOMOLOGACAO
			// //AM - https://homnfe.sefaz.am.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_AM, 443, ks);
			// //BA - https://hnfe.sefaz.ba.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_BA, 443, ks);
			// //CE - https://nfeh.sefaz.ce.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_CE, 443, ks);
			// //ES - https://app.sefaz.es.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_ES, 443, ks);
			// //GO - https://homolog.sefaz.go.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_GO, 443, ks);
			// //MG - https://hnfe.fazenda.mg.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_MG, 443, ks);
			// //MS - https://homologacao.nfe.ms.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_MS, 443, ks);
			// //MT - https://homologacao.sefaz.mt.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_MT, 443, ks);
			// //PE - https://nfehomolog.sefaz.pe.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_PE, 443, ks);
			// //PR - https://homologacao.nfe2.fazenda.pr.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_PR, 443, ks);
			// //RS - https://homologacao.nfe.sefaz.rs.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_RS, 443, ks);
			// //RS2 - https://sef.sefaz.rs.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_RS2, 443, ks);
			// //SP - https://homologacao.nfe.fazenda.sp.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SP, 443, ks);
			// //SVAN - https://hom.sefazvirtual.fazenda.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SVAN, 443, ks);
			// //SVRS - https://homologacao.nfe.sefazvirtual.rs.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SVRS, 443, ks);
			// //SVRS NfeConsultaCadastro - https://webservice.set.rn.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SVRS_CONSULTA_CADASTRO, 443,
			// ks);
			// SCAN - https://hom.nfe.fazenda.gov.br
			// get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SCAN, 443, ks);
			// SVC-AN - https://hom.svc.fazenda.gov.br
			get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_SVAN, 443, ks);
			// AN - https://hom.nfe.fazenda.gov.br
			get(UriCaCertsEnum.HOMOLOGACA0_SEFAZ_AN, 443, ks);

			// PRODUCAO
			// //AM - https://nfe.sefaz.am.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_AM, 443, ks);
			// //BA - https://nfe.sefaz.ba.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_BA, 443, ks);
			// //CE - https://nfe.sefaz.ce.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_CE, 443, ks);
			// //ES - https://app.sefaz.es.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_ES, 443, ks);
			// //GO - https://nfe.sefaz.go.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_GO, 443, ks);
			// //MG - https://nfe.fazenda.mg.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_MG, 443, ks);
			// //MS - https://nfe.fazenda.ms.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_MS, 443, ks);
			// //MT - https://nfe.sefaz.mt.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_MT, 443, ks);
			// //PE - https://nfe.sefaz.pe.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_PE, 443, ks);
			// //PR - https://nfe2.fazenda.pr.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_PR, 443, ks);
			// //RS - https://nfe.sefaz.rs.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_RS, 443, ks);
			// //RS NfeConsultaCadastro - https://sef.sefaz.rs.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_RS_CONSULTA_CASTRDO, 443, ks);
			// //SP - https://nfe.fazenda.sp.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_SP, 443, ks);
			// //SVAN - https://www.sefazvirtual.fazenda.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_SVAN, 443, ks);
			// //SVRS - https://nfe.sefazvirtual.rs.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_SVRS, 443, ks);
			// //SVRS - https://svp-ws.sefazvirtual.rs.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_SVRS2, 443, ks);
			// SCAN - https://www.scan.fazenda.gov.br
			// get(UriCaCertsEnum.PRODUCAO_SEFAZ_SCAN, 443, ks);
			// SVC-AN - https://www.svc.fazenda.gov.br
			get(UriCaCertsEnum.PRODUCAO_SEFAZ_SVC_AN, 443, ks);
			// AN - https://www.nfe.fazenda.gov.br
			get(UriCaCertsEnum.PRODUCAO_SEFAZ_AN, 443, ks);

			System.out.println(CACERTS_PATH + SEPARATOR + CACERTS_NAME);
			File cafile = new File(CACERTS_PATH + SEPARATOR + CACERTS_NAME);
			OutputStream out = new FileOutputStream(cafile);
			ks.store(out, passphrase);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			BifrostView.setLogCeritificado(e.getMessage());
		}
		BifrostView.setLogCeritificado("Certificado instalado com sucesso!");
		BifrostView.setProgressbarStop();
	}

	public static void get(UriCaCertsEnum hostEnum, int port, KeyStore ks) throws Exception {
		final int TIMEOUT_WS = 30;
		final String CACERTS_NAME = "NFeCacerts";
		final String CACERTS_PATH = System.getProperty("user.dir") + "/src/main/resources/certificados/keystore";
		final char SEPARATOR = File.separatorChar;

		String host = hostEnum.value();
		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();

		info("| Opening connection to " + host + ":" + port + "...");
		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
		socket.setSoTimeout(TIMEOUT_WS * 1000);
		try {
			info("| Starting SSL handshake...");
			socket.startHandshake();
			socket.close();
			info("| No errors, certificate is already trusted");
		} catch (SSLHandshakeException e) {
			/**
			 * PKIX path building failed:
			 * sun.security.provider.certpath.SunCertPathBuilderException:
			 * unable to find valid certification path to requested target Não
			 * tratado, pois sempre ocorre essa exceo quando o cacerts nao esta
			 * gerado.
			 */
		} catch (SSLException e) {
			error("| " + e.toString());
			BifrostView.setLogCeritificado(e.toString());
		}

		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			info("| Could not obtain server certificate chain");
		} else {
			info("| Server sent " + chain.length + " certificate(s):");
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			for (int i = 0; i < chain.length; i++) {
				X509Certificate cert = chain[i];
				sha1.update(cert.getEncoded());
				md5.update(cert.getEncoded());

				String alias = host + "-" + (i);
				ks.setCertificateEntry(alias, cert);
				info("| Added certificate to keystore '" + CACERTS_PATH + SEPARATOR + CACERTS_NAME + "' using alias '"
						+ alias + "'");
				BifrostView.setLogCeritificado("| Added certificate to keystore '" + CACERTS_PATH + SEPARATOR
						+ CACERTS_NAME + "' using alias '" + alias + "'");
			}
		}
		
	}

	private static class SavingTrustManager implements X509TrustManager {
		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
			// throw new UnsupportedOperationException();
		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			this.tm.checkServerTrusted(chain, authType);
		}
	}

	private static void info(String log) {
		System.out.println("INFO: " + log);
	}

	private static void error(String log) {
		System.out.println("ERROR: " + log);
	}

}