package br.octa.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Choice;

public class BifrostView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BifrostView window = new BifrostView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BifrostView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		frame.getContentPane().add(panelPrincipal, "name_53292159044542");
		
		JPanel panelConfiguracao = new JPanel();
		frame.getContentPane().add(panelConfiguracao, "name_53292178728373");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Principal");
		menuBar.add(mnMenu);
		
		JMenuItem mntmIniciar = new JMenuItem("Iniciar");
		mnMenu.add(mntmIniciar);
		
		JMenu mnNewMenu = new JMenu("Configura\u00E7\u00E3o");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCertificado = new JMenuItem("Instalar Certificado");
		mnNewMenu.add(mntmCertificado);
		
		JMenu mnLog = new JMenu("Monitorar");
		menuBar.add(mnLog);
		
		JMenuItem mntmLog = new JMenuItem("Log                     ");
		mnLog.add(mntmLog);
		
		JMenu mnAjuda = new JMenu("Ajuda           ");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmManual = new JMenuItem("Manual             ");
		mnAjuda.add(mntmManual);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre         ");
		mnAjuda.add(mntmSobre);
	}
}
