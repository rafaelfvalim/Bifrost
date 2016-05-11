package br.octa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyUtils {

	static Properties prop = new Properties();
	static InputStream input = null;
	static OutputStream output = null;

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

	public static void setPropertyAndStore(String key, String value){
		try{
			File file = new File("config/config.properties").getAbsoluteFile();
			FileReader inFile = new FileReader(file);
			BufferedReader buf = new BufferedReader(inFile);
			
			StringBuffer textoArquivo = new StringBuffer();
			String property = buf.readLine();
			
			while(property != null) {
				if (property.startsWith(key)) {
					property = property.substring(0,property.indexOf("=") + 1) + value;
				}
				textoArquivo.append(property);
				textoArquivo.append(System.getProperty("line.separator"));
				property = buf.readLine();
			}
			
			FileWriter outFile = new FileWriter(file);
			outFile.write(textoArquivo.toString());
			outFile.close();
		}
		catch(Exception e){
			System.out.println("Erro gravando propriedade " + key + ".");
		}
	}	

}
