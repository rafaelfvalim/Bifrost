package br.octa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;

import br.octa.view.BifrostView;

public class JCOUtils {
	static String SERVER_NAME = "SERVER";
	static String DESTINATION = "ABAP_AS_WITHOUT_POOL";
	static String DESTINATION_POOL = "ABAP_AS_WITH_POOL";

	public static void createDestination(String sapHost, String sapSysNumber, String sapClient, String user, String password, String language, String sapGateway, String sapProgramId, String sapConnectionCount) {
		
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, sapHost);
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, sapSysNumber);
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, sapClient);
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, user);
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, password);
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, language);
		createDataFile(DESTINATION, "jcoDestination", connectProperties);
		BifrostView.setLogConfigSAP("Criando configuração " + DESTINATION);
		connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");
		connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");
		createDataFile(DESTINATION_POOL, "jcoDestination", connectProperties);
		BifrostView.setLogConfigSAP("Criando configuração " + DESTINATION_POOL);
		Properties servertProperties = new Properties();
		servertProperties.setProperty(ServerDataProvider.JCO_GWHOST, sapHost);
		servertProperties.setProperty(ServerDataProvider.JCO_GWSERV, sapGateway);
		servertProperties.setProperty(ServerDataProvider.JCO_PROGID, sapProgramId);
		servertProperties.setProperty(ServerDataProvider.JCO_REP_DEST, "ABAP_AS_WITH_POOL");
		servertProperties.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, sapConnectionCount);
		createDataFile(SERVER_NAME, "jcoServer", servertProperties);
		BifrostView.setLogConfigSAP("Criando configuração " + SERVER_NAME);
	}

	static void createDataFile(String name, String suffix, Properties properties) {
		File cfg = new File(name + "." + suffix);
		if (!cfg.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream(cfg, false);
				properties.store(fos, "# Octa IT Soltions #");
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
			}
		}
	}
}
