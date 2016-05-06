package br.octa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IoUtils {
	public static FileInputStream openFile(String files) throws IOException
	{
		File file = new File(files);
		FileInputStream linhas = new FileInputStream(file);
		return linhas;
	}
	
	public String getSqlCreatDB(String file)
	{
		FileInputStream fin = null;
		String sqlCreatDB = new String();
		try
		{
			fin = openFile(file);
			int ch;
			while ((ch = fin.read()) != -1)
			{
				sqlCreatDB = sqlCreatDB + (char) ch;
			}	
		}
	    catch(FileNotFoundException e)
	    {
	      System.out.println(e.getLocalizedMessage());
	      return null;
	    }
	    catch(IOException ioe)
	    {
	      System.out.println("Exception while reading the file" + ioe);
	      return null;
	    }
	    return sqlCreatDB;
	}
	
	public String getConteudoFileSqlDB(String file)
	{
		FileInputStream fin = null;
		String sqlCreatDB = new String();
		try
		{
			fin = openFile(file);
			int ch;
			while ((ch = fin.read()) != -1)
			{
				sqlCreatDB = sqlCreatDB + (char) ch;
			}	
		}
	    catch(FileNotFoundException e)
	    {
	      System.out.println(e.getLocalizedMessage());
	      return null;
	    }
	    catch(IOException ioe)
	    {
	      System.out.println("Exception while reading the file" + ioe);
	      return null;
	    }
	    return sqlCreatDB;
	}
	
	public static String readFile( String file ) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	}
}
