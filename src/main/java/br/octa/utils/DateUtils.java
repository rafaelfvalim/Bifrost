package br.octa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getDateAsString(String patner){
		 Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(patner) ;
        return dateFormat.format(date);
	}
	
}
