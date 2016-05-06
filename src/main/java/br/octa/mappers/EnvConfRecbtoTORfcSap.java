package br.octa.mappers;

import java.util.ArrayList;
import java.util.List;

import com.sap.conn.jco.JCoFunction;

import br.inf.portalfiscal.nfe.TEnvEvento;
import br.inf.portalfiscal.nfe.TEvento;
import br.octa.to.TEnvEventoTO;
import br.octa.to.TEventoTO;

public class EnvConfRecbtoTORfcSap {

	protected TEnvEventoTO envEventoTO;
	// Declare os tipos de acordo com a rfc
	// Exemplo:
	// protected JCoStructure rfcHeader = null;
	// protected JCoTable rfcItem;
	// protected JCoTable rfcRef;
	// protected JCoTable rfcDup;
	// protected JCoTable rfcVol;

	public EnvConfRecbtoTORfcSap(TEnvEvento document, JCoFunction function) {
		// inicializar a rfc
		// Exemplo:
		// rfcHeader = function.getImportParameterList().getStructure("XML_IN");
		// rfcHeader = function.getImportParameterList().getStructure("XML_IN");
	}

	public TEnvEvento getMap() {
		String versaoLeiaute = null;
		String idLote = null;
		String versaoLeiatueEvento = null;
		return new TEnvEventoTO(getMapEventos(), idLote, versaoLeiaute).getTEnvEvento();
	}

	public List<TEvento> getMapEventos() {
		List<TEvento> listEvento = new ArrayList<TEvento>();
		// Aqui campos da rfc
		// Lembrando que esse evento pode ser uma lista
		String versaoEvento = null;
		String chNFe = null;
		String cnpj = null;
		String cOrgao = null;
		String cpf = null;
		String dhEvento = null;
		String id = null;
		String nSeqEvento = null;
		String tpAmb = null;
		String tpEvento = null;
		String verEvento = null;
		String descEvento = null;
		String versao = null;
		String xJust = null;
		String versaoDetEvento = null;
		TEventoTO enEventoTO = new TEventoTO(null, versaoEvento, chNFe, cnpj, cOrgao, cpf, dhEvento, id, nSeqEvento,
				tpAmb, tpEvento, verEvento, descEvento, versaoDetEvento, xJust);
		listEvento.add(enEventoTO.getEvento());
		return listEvento;
	}

}
