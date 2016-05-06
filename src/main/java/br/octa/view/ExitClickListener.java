package br.octa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.octa.utils.ConfigUtils;

public class ExitClickListener implements ActionListener {

	private static final String MSG_FECHAR = "Finalizar a integração MD - SAP." 
												+ "  \n Tem certeza?";

	private static final String TITULO_JANELA = "Filial " + ConfigUtils.getCompanyName();
	
	public void actionPerformed(ActionEvent e) {
		int resposta = JOptionPane.showConfirmDialog(null, MSG_FECHAR,TITULO_JANELA,0);

		if (resposta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

}
