package br.octa.utils;

import java.nio.charset.StandardCharsets; // java 7
import javax.xml.bind.DatatypeConverter;

public class Base64Utils 
{
   private Base64Utils() {}

   public static String encode(String value) throws Exception {
      return  DatatypeConverter.printBase64Binary
          (value.getBytes(StandardCharsets.UTF_8)); // use "utf-8" if java 6
   }

   public static String decode(String value) throws Exception {
      byte[] decodedValue = DatatypeConverter.parseBase64Binary(value);
      return new String(decodedValue, StandardCharsets.UTF_8); // use "utf-8" if java 6
   }

   public static void main(String[] args) throws Exception {
      String test = "try this howto";

      String res1 = Base64Utils.encode(test);
      System.out.println
        (test + " base64 -> " + res1);

      String res2 = Base64Utils.decode(res1);
      System.out.println( res1 + " string --> "  + res2);

      /*
       * output
       *  try this howto base64 -> dHJ5IHRoaXMgaG93dG8=
       *  dHJ5IHRoaXMgaG93dG8= string --> try t
       */
      }
   
}