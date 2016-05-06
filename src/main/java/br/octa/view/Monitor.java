package br.octa.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import br.octa.utils.ConfigUtils;

public class Monitor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  
	private JPanel pnlBotoes = null;
	private JButton btArquivo = null;
	private JTextField pathArquivo = null;
	private JPanel pnlArquivo = null;
	private JTextArea txtLog = null;  
	private JScrollPane scroll = null;
	private static SystemTray sysTray;
	
	/**
	 * This method initializes pnlBotoes	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlBotoes() {
		if (pnlBotoes == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			pnlBotoes = new JPanel();
			pnlBotoes.setLayout(null);
			pnlBotoes.setSize(new Dimension(625, 32));
			pnlBotoes.add(getBtArquivo(), gridBagConstraints);
			pnlBotoes.add(getPathArquivo(), null);
		}
		
		return pnlBotoes;
	}

	/**
	 * This method initializes btArquivo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtArquivo() {
		if (btArquivo == null) {
			btArquivo = new JButton();
			btArquivo.setSize(new Dimension(93, 21));
			btArquivo.setLocation(new Point(381, 7));
			btArquivo.setText("Abrir LOG");
			btArquivo.addActionListener(new ActionListener() {
										    public void actionPerformed(ActionEvent event) {
											    btArquivoClick(event);
										    }});
										    
		}
		return btArquivo;
	}

	private void btArquivoClick(ActionEvent ae)
	{
		JFileChooser jf = new JFileChooser();

		jf.setDialogTitle("Localizar arquivo de Log");
		jf.setApproveButtonText("OK");

		if (pathArquivo.getText().trim().length() > 0)
			jf.setCurrentDirectory(new File(pathArquivo.getText()));			
			
		if (jf.showOpenDialog(txtLog) == JFileChooser.APPROVE_OPTION)
		{
			pathArquivo.setText(jf.getSelectedFile().getAbsolutePath());
			readFile(pathArquivo.getText());
		}
	}
	
	/**
	 * This method initializes pathArquivo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPathArquivo() {
		if (pathArquivo == null) {
			pathArquivo = new JTextField();
			pathArquivo.setBounds(new Rectangle(4, 8, 375, 20));
			pathArquivo.setEditable(false);
		}
		return pathArquivo;
	}

	/**
	 * This method initializes pnlArquivo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlArquivo() {
		if (pnlArquivo == null) {
			scroll = new JScrollPane(getTxtLog(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					                             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setBounds(new Rectangle(4, 4, 614, 450));
			pnlArquivo = new JPanel();
			pnlArquivo.setLayout(null);
			pnlArquivo.setBounds(new Rectangle(4, 32, 619, 461));
			pnlArquivo.add(scroll);
			pnlArquivo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		}
		return pnlArquivo;
	}

	/**
	 * This method initializes txtLog	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtLog() {
		if (txtLog == null) {
			txtLog = new JTextArea();
			txtLog.setBounds(new Rectangle(3, 20, 191, 119));
			txtLog.setEditable(false);
		}
		return txtLog;
	}

	public void run()
	{
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monitor.createTrayIcon();
	}

	
	/**
	 * This is the default constructor
	 */
	public Monitor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(633, 530);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Integrador SAP-NFe - Filial " + ConfigUtils.getCompanyName());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(389, 285));
			jContentPane.add(getPnlBotoes(), null);
			jContentPane.add(getPnlArquivo(), null);
		}
		return jContentPane;
	}

	private void readFile (String path)
	{
		File f = new File(path);
		try
		{
			txtLog.setText("");
			if (f != null)
			{
				BufferedReader input = new BufferedReader(new FileReader(f));
				String linha = null;
				
				while ((linha = input.readLine()) != null)
					txtLog.append(linha+"\n");
			}
		}catch(Exception ex)
		{
		}
	}
	
	public static void createTrayIcon()
	{
		new IconeTray(getSysTray());
	}

	public static SystemTray getSysTray() {
		if (sysTray == null)
			sysTray = SystemTray.getSystemTray();
			
		return sysTray;
	}

	public static void setSysTray(SystemTray sysTray) {
		Monitor.sysTray = sysTray;
	}
	
}  
