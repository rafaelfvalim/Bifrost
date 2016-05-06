package br.octa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	static Properties prop = new Properties();
	static InputStream input = null;

	public static String getValue(String propertie) {
		try {
			input = new FileInputStream("config/config.properties");
			prop.load(input);
			return prop.getProperty(propertie);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
