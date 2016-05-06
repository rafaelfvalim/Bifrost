package br.octa.mappers;

import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.inf.portalfiscal.nfe.TretEvento;

public class RFCTOEnvConfRecbto {

	private TRetEnvEvento retEnvEvento;
	private JCoFunction function;
	// Declare os tipos de acordo com a rfc
	// Exemplo:
	// protected JCoStructure rfcHeader = null;
	// protected JCoTable rfcItem;
	// protected JCoTable rfcRef;
	// protected JCoTable rfcDup;
	// protected JCoTable rfcVol;

	public RFCTOEnvConfRecbto(JCoFunction function) {
		this.function = function;
	}

	public void map() {

	}

	public void getRfcData(){
		
		JCoStructure I_DADOS = function.getImportParameterList().getStructure("I_DADOS");
		String IP02_VERSAO = I_DADOS.getString("IP02_VERSAO");
		String IP03_TPAMB = I_DADOS.getString("IP03_TPAMB");
		String IP04_XSERV = I_DADOS.getString("IP04_XSERV");
		String IP05_CNPJ = I_DADOS.getString("IP05_CNPJ");
		String IP06_INDNFE = I_DADOS.getString("IP06_INDNFE");
		String IP07_INDEMI = I_DADOS.getString("IP07_INDEMI");
		String IP08_ULTNSU = I_DADOS.getString("IP08_ULTNSU");
		
		
		

		for (TretEvento retEvento : retEnvEvento.getRetEvento()) {
			TretEvento.InfEvento infEvento = retEvento.getInfEvento();
			infEvento.getChNFe();
			// TODO: Buscar todos os campos de acordo com a RFC
		}
	}

}
