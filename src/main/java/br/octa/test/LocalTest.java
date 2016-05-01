package br.octa.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.inf.portalfiscal.nfe.TRetEnvEvento;
import br.octa.md.model.RetEnvConfRecebto;

public class LocalTest {

	public static void main(String[] args) {
		// EnvConfRecebto env = new EnvConfRecebto();
		// Evento eventoUtils = new Evento();
		// env.createEnvEvento("versao leiout", "id Lote", "Versao sadsa",
		// eventoUtils.createEvento("", "", "", "", "", "", "", "", "", "", "",
		// "", "", "", ""));
		// System.out.println(env.toXML());
		try {
			String xml = new String(Files.readAllBytes(Paths.get("xml_exemplos/retEnvEvento.xml")));
			System.out.println(xml);
			RetEnvConfRecebto retEnvConfRecebto = new RetEnvConfRecebto();
			TRetEnvEvento tRetEnvEvento = retEnvConfRecebto.createRetEnvEventoFromXMl(xml);
		    	System.out.println(tRetEnvEvento.getIdLote());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
