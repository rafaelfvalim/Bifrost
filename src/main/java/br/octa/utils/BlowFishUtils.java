package br.octa.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 

public class BlowFishUtils {
	
	private static String passfrase="achavedeveter16";   
	private String CRIPTFILENAME = "/integracao/util/resourceGlv";
	//private String CRIPTFILENAME = "resourceGlv";
    
	   public static final String cript(String str)   
	   {   
	      String strCript = str;   
	         
	      try  
	      {   
	         Cipher ch = Cipher.getInstance("Blowfish");   
	         SecretKey k1 = new SecretKeySpec(passfrase.getBytes(), "Blowfish");   
	            
	         //criptografando   
	         ch.init( Cipher.ENCRYPT_MODE, k1);   
	         byte b[] = ch.doFinal(strCript.getBytes());   
	         String s1 = Conversion.byteArrayToBase64String(b);   
	         strCript = s1;   
	      }   
	      catch( Exception e)   
	      {   
	    	  System.out.println(e);   
	      }   
	               
	      return strCript;   
	   }   
	      
	   public static final String decript(String str)   
	   {   
	      String strDecript = str;   
	         
	      try  
	      {   
	         Cipher ch = Cipher.getInstance("Blowfish");   
	         SecretKey k1 = new SecretKeySpec(passfrase.getBytes(), "Blowfish");   
	            
	         //decriptografando   
	         ch.init( Cipher.DECRYPT_MODE, k1);   
	         byte b[] = Conversion.base64StringToByteArray(strDecript);   
	         byte b1[] = ch.doFinal(b);   
	         String s1 = new String(b1);   
	         strDecript = s1;   
	      }   
	      catch( Exception e)   
	      {   
	    	  System.out.println(e);
	      }   
	      return strDecript;   
	   }   

	   public static void main(String[] args) {
		   String cripted = BlowFishUtils.cript("senha");
		   System.out.println(cripted);
		   
		   String decripted = BlowFishUtils.decript(cripted);
		   System.out.println(decripted);
	   }

	   public ArrayList<ArrayList<String>> decriptFromFile() {
			  ArrayList<ArrayList<String>> cgcs = new ArrayList<ArrayList<String>>();
			  try {
				  	ArrayList<String> cgc = null;
				    String f2 = "";
				    
				    List<String> list = SecurityUtils.readTextFromJar(CRIPTFILENAME);
				    Iterator<String> it = list.iterator();
				    while(it.hasNext()) {
				    	f2 = f2 + it.next();
				    }
				    f2 = decript(f2);
			    	String[] vals = f2.split(":");
			    	for (String x : vals) {
			    		if (x != null && x.length() > 1) {
				    		cgc = new ArrayList<String>();
					    	String[] values = x.split(";");
					    	for (String s : values) {
					    		if (s != null && !s.equals(""))
					    			cgc.add(s);
					    	}
				    		cgcs.add(cgc);
			    		}
			    	}
			      }
				   catch( Exception e)   
				  {   
				     e.printStackTrace();   
				  }   
		      return cgcs;
		   }
}