package br.octa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.octa.enums.LayoutEnum;
import br.octa.view.BifrostView;

public class ViewUtils {

	public static void writeFileLog(String xml, LayoutEnum layout) {
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss");
		String folder = ConfigUtils.getFolderLog();
		String path = folder + "/" + layout.valor() + "_" + dateformat.format(date) + ".xml";
		
		BifrostView.getDataLog();
		try {
			Files.write(Paths.get(path), xml.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> getFilesFromFolder(String folder) {
		List<String> files = new ArrayList<String>();
		try {
			Files.walk(Paths.get(folder)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					files.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return files;
	}
}
