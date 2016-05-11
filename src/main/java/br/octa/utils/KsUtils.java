package br.octa.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KsUtils {
	public static String gKey(String cnpj) {
		try {
			String cS = Sys.getCPUS();
			String mS = Sys.getMS();
			return BlowFishUtils.cript(cS + "_" + mS + "_" + cnpj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setKey(String chave) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("src/main/resources/s/p.bifrost", "UTF-8");
		writer.println(chave);
		writer.close();
	}

	public static String readk() {
		try {
			return new String(Files.readAllBytes(Paths.get("src/main/resources/s/p.bifrost")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static boolean validateK(String cnpj) throws ParseException, IOException {
		String chave = BlowFishUtils.decript(readk());
		String[] blocks = chave.split("_");
		String cS = blocks[0];
		String mS = blocks[1];
		String pj = blocks[2];
		String dateKey = blocks[3];

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		Date date = formatter.parse(dateKey);
		Date today = new Date();
		if (cS.equals(Sys.getCPUS())
				&& pj.equals(cnpj)
				&& mS.equals(Sys.getMS())
				&& today.compareTo(date) < 0) {
			return true;
		}
		return false;
	}

}
