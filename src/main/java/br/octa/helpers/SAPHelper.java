package br.octa.helpers;

import org.apache.log4j.Logger;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;

import br.octa.exception.SAPStructureException;

public class SAPHelper {
	final static Logger logger = Logger.getLogger(SAPHelper.class);

	public static JCoStructure readRFCStructure(JCoFunction function, String name) throws SAPStructureException  {
		JCoStructure value = null;
		try{
			JCoParameterList input = function.getImportParameterList();
			value = input.getStructure(name);
		}catch(Exception e){
			logger.error(e.getMessage());
			throw new SAPStructureException("Problema ao obter estrutura " + name + " da RFC[" + function.getName() + "]. Exceção: " + e + ". Mais detalhes ver log do integrador em disco.");
		}
		return value;
	}

}
