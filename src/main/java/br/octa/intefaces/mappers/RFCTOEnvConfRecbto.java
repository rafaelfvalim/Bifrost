package br.octa.intefaces.mappers;

import java.util.ArrayList;
import java.util.List;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.octa.model.NFeConsultaWSData;
import br.octa.model.NFeEnvEventoWSData;
import br.octa.to.TEnvEventoTO;
import br.octa.to.TEventoTO;

public class RFCTOEnvConfRecbto {

	private JCoFunction function;

	public RFCTOEnvConfRecbto(JCoFunction function) {
		this.function = function;
	}

	public TEnvEvento getRfcData() {

		JCoStructure I_DADOS = function.getImportParameterList().getStructure("I_DADOS");
		String HP02_VERSAO = I_DADOS.getString("HP02_VERSAO");
		String HP03_IDLOTE = I_DADOS.getString("HP02_VERSAO");

		JCoTable IT_EVENTOS = function.getTableParameterList().getTable("IT_EVENTOS");
		List<TEvento> listaEventos = new ArrayList<TEvento>();

		if (IT_EVENTOS.getNumRows() > 0) {
			
			while (IT_EVENTOS.nextRow()) {
				String HP05_VERSAO = IT_EVENTOS.getString("HP05_VERSAO");
				String HP07_ID = IT_EVENTOS.getString("HP07_ID");
				String HP08_CORGAO = IT_EVENTOS.getString("HP08_CORGAO");
				String HP09_TPAMB = IT_EVENTOS.getString("HP09_TPAMB");
				String HP10_CNPJ = IT_EVENTOS.getString("HP10_CNPJ");
				String HP11_CPF = IT_EVENTOS.getString("HP11_CPF");
				String HP12_CHNFE = IT_EVENTOS.getString("HP12_CHNFE");
				String HP13_DHEVENTO = IT_EVENTOS.getString("HP13_DHEVENTO");
				String HP14_TPEVENTO = IT_EVENTOS.getString("HP14_TPEVENTO");
				String HP15_NSEQEVENTO = IT_EVENTOS.getString("HP15_NSEQEVENTO");
				String HP16_VEREVENTO = IT_EVENTOS.getString("HP16_VEREVENTO");
				String HP18_VERSAO = IT_EVENTOS.getString("HP18_VERSAO");
				String HP19_DESCEVENTO = IT_EVENTOS.getString("HP19_DESCEVENTO");
				String HP20_XJUST = IT_EVENTOS.getString("HP20_XJUST");
				TEventoTO eventoTO = new TEventoTO(
						HP05_VERSAO, 
						HP07_ID,
						HP08_CORGAO,
						HP09_TPAMB, 
						HP10_CNPJ,
						HP11_CPF,
						HP12_CHNFE,
						HP13_DHEVENTO, 
						HP14_TPEVENTO,
						HP15_NSEQEVENTO,
						HP16_VEREVENTO, 
						HP18_VERSAO,
						HP19_DESCEVENTO, 
						HP20_XJUST
				);
				listaEventos.add(eventoTO.getEvento());
			}

		}

		return new TEnvEventoTO(listaEventos, HP03_IDLOTE, HP02_VERSAO).getTEnvEvento();
	}
	
	public NFeEnvEventoWSData getHeaderWS(){
		JCoStructure I_WSHEADER = function.getImportParameterList().getStructure("I_WSHEADER");
		String VERSAO = I_WSHEADER.getString("VERSAO");
		String UF = I_WSHEADER.getString("UF");
		String URL = I_WSHEADER.getString("URL");
		return new NFeEnvEventoWSData(VERSAO, URL, UF);
	}

}
