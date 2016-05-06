package br.octa.to;

import org.w3c.dom.Element;

import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe.ProcNFe;
import br.inf.portalfiscal.nfe.TRetDownloadNFe.RetNFe.ProcNFeGrupoZip;

public class TRetNFeTO {
	RetNFe retNFe;
	RetNFe.ProcNFeGrupoZip procNFeGrupoZip;
	RetNFe.ProcNFe procNFe;
	
	
	public TRetNFeTO(String chNFe, String cStat, ProcNFe procNFe, ProcNFeGrupoZip procNFeGrupoZip, byte[] procNFeZip,
			String xMotivo, byte[] retNFeProcNFeZip, byte[] nFeZip, byte[] protNFeZip, Element any, String schema) {
		retNFe = new RetNFe();
		procNFeGrupoZip = new RetNFe.ProcNFeGrupoZip();
		procNFe = new RetNFe.ProcNFe();
		
		procNFeGrupoZip.setNFeZip(nFeZip);
		procNFeGrupoZip.setProtNFeZip(protNFeZip);
		procNFe.setAny(any);
		procNFe.setSchema(schema);
		retNFe.setChNFe(chNFe);
		retNFe.setCStat(cStat);
		retNFe.setProcNFe(procNFe);
		retNFe.setProcNFeGrupoZip(procNFeGrupoZip);
		retNFe.setProcNFeZip(retNFeProcNFeZip);
		retNFe.setXMotivo(xMotivo);
	}
	
	public RetNFe getRetNFe(){
		return retNFe;
	}

}
