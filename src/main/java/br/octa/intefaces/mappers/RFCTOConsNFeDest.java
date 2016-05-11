package br.octa.intefaces.mappers;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;

import br.inf.portalfiscal.nfe.TConsNFeDest;
import br.octa.model.NFeConsultaWSData;
import br.octa.to.TConsNFeDestTO;

public class RFCTOConsNFeDest {
	private JCoFunction function;

	public RFCTOConsNFeDest(JCoFunction function) {
		this.function = function;
	}

	public TConsNFeDest getRFCData() {
		
		JCoStructure I_DADOS = function.getImportParameterList().getStructure("I_DADOS");
		String IP02_VERSAO = I_DADOS.getString("IP02_VERSAO");
		String IP03_TPAMB = I_DADOS.getString("IP03_TPAMB");
		String IP04_XSERV = I_DADOS.getString("IP04_XSERV");
		String IP05_CNPJ = I_DADOS.getString("IP05_CNPJ");
		String IP06_INDNFE = I_DADOS.getString("IP06_INDNFE");
		String IP07_INDEMI = I_DADOS.getString("IP07_INDEMI");
		String IP08_ULTNSU = I_DADOS.getString("IP08_ULTNSU");
		
		return new TConsNFeDestTO(IP05_CNPJ, IP07_INDEMI, IP03_TPAMB, IP08_ULTNSU, IP02_VERSAO, IP04_XSERV, IP06_INDNFE).geTConsNFeDest();
	}
	
	public NFeConsultaWSData getHeaderWS(){
		JCoStructure I_WSHEADER = function.getImportParameterList().getStructure("I_WSHEADER");
		String VERSAO = I_WSHEADER.getString("VERSAO");
		String UF = I_WSHEADER.getString("UF");
		String URL = I_WSHEADER.getString("URL");
		return new NFeConsultaWSData(VERSAO, URL, UF);
	}
	
}
