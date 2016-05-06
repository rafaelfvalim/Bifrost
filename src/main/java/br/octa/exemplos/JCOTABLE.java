package br.octa.exemplos;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class JCOTABLE {

	
	public void gravartabela(JCoFunction gvFunctionSAPDic){
      	JCoTable RTABLE = gvFunctionSAPDic.getImportParameterList().getTable("I_RTABLE");
//		if(txfTabelaDe_WS2.getText().indexOf("*") > 0)
        {
			RTABLE.appendRow();
			RTABLE.setValue("SIGN",  "I");
			RTABLE.setValue("OPTION","CP");
			RTABLE.setValue("LOW", 	 "");
        }
	}
	
	
	public void lertabela(){
		
		
	}
	
	private void loadDD07(JCoTable DD07T, String alias)
	{
		if (DD07T.getNumRows() > 0) 
        {
//			dbutil.Execute("Begin;", alias);
        	DD07T.firstRow();
            do {
	              String sql = new String("insert into DOMVALUE values('" + DD07T.getValue("DOMNAME").toString() 	 + "', '"
	            			                                       + DD07T.getValue("VALPOS").toString() 	 + "', '"
	            			                                       + DD07T.getValue("DOMVALUE_L").toString() + "', '"
	            			                                       + DD07T.getValue("DOMVAL_LD").toString()  + "', '"                                     
	            			                                       + DD07T.getValue("DOMVAL_HD").toString()  + "', '"
	                                                               + DD07T.getValue("DDTEXT").toString()     + "')");
//            	     dbutil.execSqlUpd(SQL,alias);
               } while(DD07T.nextRow());
//            dbutil.Execute("commit;", alias);
        }
	}
}
