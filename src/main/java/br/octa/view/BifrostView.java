package br.octa.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.google.common.io.Files;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import br.inf.portalfiscal.security.NFeBuildAllCacerts;
import br.octa.server.server.sap.Server;
import br.octa.utils.ConfigUtils;
import br.octa.utils.JCOPropertyUtils;
import br.octa.utils.JCOUtils;
import br.octa.utils.KsUtils;
import br.octa.utils.ViewUtils;
import javax.swing.JSeparator;
import java.awt.Toolkit;

public class BifrostView {

	private JFrame frmBifrostInetegrator;
	private boolean sapOnline;
	private boolean sefazOnline;
	private static JTextArea txtAreaLog;
	private static JTextArea txtAreaLogCertificado;
	private Server server;
	private static JTable tableLog;
	private JTextField txtCertificado;
	private JPasswordField txtSenha;
	private static JLabel lblProgressBar;
	private JTextField txtConnectionCount;
	private JTextField txtSapHost;
	private JTextField txtSapGW;
	private JTextField txtProgramId;
	private JTextField txtUser;
	private JPasswordField txtPasssword;
	private JTextField txtClient;
	private JTextField txtLanguage;
	private JTextField txtSysNumber;
	private static JTextArea textAreaLogConexao;
	private JTextField txtKeyRegistro;
	private JTextField txtCnpjRegistro;
	private JTextField txtNumeroSistemaRegistro;
	private JTextField txtChaveInstalacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					} catch (Exception e) {
					}
					BifrostView window = new BifrostView();
					window.frmBifrostInetegrator.setVisible(true);

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
		getDataLog();
		initializeFields();
	}

	private void initializeFields() {
		txtLanguage.setText(JCOPropertyUtils.getValue("jco.client.lang", "ABAP_AS_WITH_POOL.jcoDestination"));
		txtUser.setText(JCOPropertyUtils.getValue("jco.client.user", "ABAP_AS_WITH_POOL.jcoDestination"));
		// txtPasssword.setText(JCOPropertyUtils.getValue("jco.client.passwd",
		// "ABAP_AS_WITH_POOL.jcoDestination"));
		txtSysNumber.setText(JCOPropertyUtils.getValue("jco.client.sysnr", "ABAP_AS_WITH_POOL.jcoDestination"));
		txtClient.setText(JCOPropertyUtils.getValue("jco.client.client", "ABAP_AS_WITH_POOL.jcoDestination"));
		txtSapGW.setText(JCOPropertyUtils.getValue("jco.server.gwserv", "SERVER.jcoServer"));
		txtProgramId.setText(JCOPropertyUtils.getValue("jco.server.progid", "SERVER.jcoServer"));
		txtConnectionCount.setText(JCOPropertyUtils.getValue("jco.server.connection_count", "SERVER.jcoServer"));
		txtSapHost.setText(JCOPropertyUtils.getValue("jco.server.gwhost", "SERVER.jcoServer"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		sapOnline = false;
		frmBifrostInetegrator = new JFrame();
		frmBifrostInetegrator.setTitle("Bifrost Inetegrator MD v1.0");
		frmBifrostInetegrator.setIconImage(Toolkit.getDefaultToolkit().getImage("images/network_transmit_receive.png"));
		frmBifrostInetegrator.setResizable(false);
		frmBifrostInetegrator.setBounds(100, 100, 970, 591);
		frmBifrostInetegrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBifrostInetegrator.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		frmBifrostInetegrator.getContentPane().add(panelPrincipal, "cardPanelPrincipal");

		JLabel iconStatusSap = new JLabel("");
		iconStatusSap.setBounds(185, 6, 55, 29);
		panelPrincipal.add(iconStatusSap);
		iconStatusSap.setIcon(new ImageIcon("images/disconnect.png"));

		JPanel panelConfiguracao = new JPanel();
		frmBifrostInetegrator.getContentPane().add(panelConfiguracao, "cardConfiguracao");
		panelConfiguracao.setLayout(null);

		JPanel panelConfigSAP = new JPanel();
		frmBifrostInetegrator.getContentPane().add(panelConfigSAP, "cardConfingSAP");
		panelConfigSAP.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 20, 421, 320);
		panelConfigSAP.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblUser = new JLabel("Usu\u00E1rio*:");
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblUser);

		txtUser = new JTextField();
		panel.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblPassword = new JLabel("Senha*:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblPassword);

		txtPasssword = new JPasswordField();
		panel.add(txtPasssword);
		txtPasssword.setColumns(10);

		JLabel lblClient = new JLabel("Client*:");
		lblClient.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClient.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblClient);

		txtClient = new JTextField();
		panel.add(txtClient);
		txtClient.setColumns(10);

		JLabel lblSysNumber = new JLabel("System Number*:");
		lblSysNumber.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSysNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblSysNumber);

		txtSysNumber = new JTextField();
		panel.add(txtSysNumber);
		txtSysNumber.setColumns(10);

		JLabel label = new JLabel("Language*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(label);

		txtLanguage = new JTextField();
		panel.add(txtLanguage);
		txtLanguage.setColumns(10);

		JLabel lblNumeroConexoes = new JLabel("Numero de Threads:");
		lblNumeroConexoes.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblNumeroConexoes);
		lblNumeroConexoes.setHorizontalAlignment(SwingConstants.RIGHT);

		txtConnectionCount = new JTextField();
		panel.add(txtConnectionCount);
		txtConnectionCount.setColumns(10);

		JLabel lblSapHost = new JLabel("SAP Host*:");
		lblSapHost.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblSapHost);
		lblSapHost.setHorizontalAlignment(SwingConstants.RIGHT);

		txtSapHost = new JTextField();
		panel.add(txtSapHost);
		txtSapHost.setColumns(10);

		JLabel lblSapGw = new JLabel("SAP Gateway*:");
		lblSapGw.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblSapGw);
		lblSapGw.setHorizontalAlignment(SwingConstants.RIGHT);

		txtSapGW = new JTextField();
		panel.add(txtSapGW);
		txtSapGW.setColumns(10);

		JLabel lblProgramId = new JLabel("Programa Registrado*:");
		lblProgramId.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(lblProgramId);
		lblProgramId.setHorizontalAlignment(SwingConstants.RIGHT);

		txtProgramId = new JTextField();
		panel.add(txtProgramId);
		txtProgramId.setColumns(10);

		JLabel label_6 = new JLabel("");
		panel.add(label_6);

		JButton btnGerarConfig = new JButton("Salvar");
		btnGerarConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				File SERVER = new File("SERVER.jcoServer");
				File ABAP_AS_WITH_POOL = new File("ABAP_AS_WITH_POOL.jcoDestination");
				File ABAP_AS_WITHOUT_POOL = new File("ABAP_AS_WITHOUT_POOL.jcoDestination");

				if (SERVER.exists() && ABAP_AS_WITH_POOL.exists() && ABAP_AS_WITHOUT_POOL.exists()) {
					if (SERVER.delete()) {
						setLogConfigSAP("Reset SERVER.jcoServer");
					}
					if (ABAP_AS_WITH_POOL.delete()) {
						setLogConfigSAP("Reset ABAP_AS_WITH_POOL.jcoDestination");

					}
					if (ABAP_AS_WITHOUT_POOL.delete()) {
						setLogConfigSAP("Reset ABAP_AS_WITHOUT_POOL.jcoDestination");

					}
				}

				if (validate()) {
					String sapHost = txtSapHost.getText();
					String user = txtUser.getText();
					String sapSysNumber = txtSysNumber.getText();
					String sapClient = txtClient.getText();
					String password = txtPasssword.getText();
					String language = txtLanguage.getText();
					String sapGateway = txtSapGW.getText();
					String sapProgramId = txtProgramId.getText();
					String sapConnectionCount = txtConnectionCount.getText();
					setLogConfigSAP("Gerando configuração SAP");
					JCOUtils.createDestination(sapHost, sapSysNumber, sapClient, user, password, language, sapGateway,
							sapProgramId, sapConnectionCount);
					setLogConfigSAP("Configuração realizada com sucesso!");
				}
			}
		});
		btnGerarConfig.setFont(new Font("SansSerif", Font.BOLD, 14));
		panel.add(btnGerarConfig);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(456, 20, 486, 320);
		panelConfigSAP.add(panel_1);
		panel_1.setLayout(null);

		JTextPane txtpnConfiguraoDaConexo = new JTextPane();
		txtpnConfiguraoDaConexo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtpnConfiguraoDaConexo.setText(
				"Configura\u00E7\u00E3o da conex\u00E3o entre o integrador e o sistema SAP.\r\nForne\u00E7a os dados da conex\u00E3o.\r\n");
		txtpnConfiguraoDaConexo.setEditable(false);
		txtpnConfiguraoDaConexo.setBounds(6, 6, 474, 308);
		panel_1.add(txtpnConfiguraoDaConexo);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(23, 352, 919, 181);
		panelConfigSAP.add(scrollPane_3);

		textAreaLogConexao = new JTextArea();
		scrollPane_3.setViewportView(textAreaLogConexao);

		lblProgressBar = new JLabel("");
		lblProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgressBar.setBounds(251, 506, 482, 16);
		panelConfiguracao.add(lblProgressBar);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 102, 952, 389);
		panelConfiguracao.add(scrollPane_2);

		txtAreaLogCertificado = new JTextArea();
		scrollPane_2.setViewportView(txtAreaLogCertificado);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(6, 6, 952, 76);
		panelConfiguracao.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSelecionarCertificado = new JButton("Selecionar");
		btnSelecionarCertificado.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSelecionarCertificado.setBounds(642, 6, 161, 28);
		panel_2.add(btnSelecionarCertificado);

		txtCertificado = new JTextField();
		txtCertificado.setBounds(258, 6, 376, 28);
		panel_2.add(txtCertificado);
		txtCertificado.setColumns(10);

		JButton btnInstalarCeriti = new JButton("Instalar Certificado");
		btnInstalarCeriti.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnInstalarCeriti.setBounds(638, 40, 165, 28);
		panel_2.add(btnInstalarCeriti);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(258, 40, 376, 28);
		panel_2.add(txtSenha);

		JLabel lblSenhaCertificado = new JLabel("Senha do Certificado:");
		lblSenhaCertificado.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSenhaCertificado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenhaCertificado.setBounds(6, 46, 232, 16);
		panel_2.add(lblSenhaCertificado);

		JLabel lblCertificado = new JLabel("Certificado A1:");
		lblCertificado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCertificado.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCertificado.setBounds(114, 12, 127, 16);
		panel_2.add(lblCertificado);

		JLabel lblLogs = new JLabel("Logs:");
		lblLogs.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblLogs.setBounds(6, 85, 31, 16);
		panelConfiguracao.add(lblLogs);
		btnInstalarCeriti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProgressbarStart();
				if ("".equals(txtCertificado.getText())) {
					JOptionPane.showMessageDialog(null, "Selecione um certificado válido");
				} else if ("".equals(txtSenha.getText())) {
					JOptionPane.showMessageDialog(null, "Favor informar a senha do certificado");
				} else {
					File certificado = new File(txtCertificado.getText());
					setLogCeritificado("Carregando Certificado " + certificado.getName());
					Path p = Paths.get(certificado.getPath());
					try {
						Files.copy(certificado, new File("config/" + p.getFileName()));
					} catch (IOException e1) {
						e1.printStackTrace();
						setLogCeritificado(e1.getMessage());
					}
					ConfigUtils.setSenhaCertificado(txtSenha.getText());
					ConfigUtils.setArquivoCertificado("config/" + p.getFileName().toString());

					setLogCeritificado("Iniciando o processo de geração da keystore");
					Thread t = new Thread(new NFeBuildAllCacerts());
					t.start();

				}
			}

		});
		btnSelecionarCertificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				chooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Cerficiado pfx", "pfx");
				chooser.addChoosableFileFilter(filter);
				int option = chooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					txtCertificado.setText(file.getPath());
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		frmBifrostInetegrator.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Principal");
		menuBar.add(mnMenu);

		JMenuItem mntmIniciar = new JMenuItem("Dashboard                               ");
		mntmIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (frmBifrostInetegrator.getContentPane().getLayout());
				cl.show(frmBifrostInetegrator.getContentPane(), "cardPanelPrincipal");
			}
		});
		mnMenu.add(mntmIniciar);

		JSeparator separator = new JSeparator();
		mnMenu.add(separator);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnMenu.add(mntmSair);

		JMenu mnNewMenu = new JMenu("Configura\u00E7\u00E3o");
		menuBar.add(mnNewMenu);

		JMenuItem mntmCertificado = new JMenuItem("Instalar Certificado                  ");
		mntmCertificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (frmBifrostInetegrator.getContentPane().getLayout());
				cl.show(frmBifrostInetegrator.getContentPane(), "cardConfiguracao");
			}
		});
		mnNewMenu.add(mntmCertificado);
		JMenuItem mntmConfigurarConexaoSap = new JMenuItem("Configurar Conexao SAP                 ");
		mntmConfigurarConexaoSap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (frmBifrostInetegrator.getContentPane().getLayout());
				cl.show(frmBifrostInetegrator.getContentPane(), "cardConfingSAP");
			}
		});
		mnNewMenu.add(mntmConfigurarConexaoSap);
		JMenu mnAjuda = new JMenu("Ajuda           ");
		menuBar.add(mnAjuda);
		JMenuItem mntmManual = new JMenuItem("Manual                                  ");
		mnAjuda.add(mntmManual);
		JMenuItem mntmSobre = new JMenuItem("Sobre                                   ");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder msg = new StringBuilder();
				msg.append("Octa Especialista em TI\n");
				msg.append("Bifrost Integrador Manifesto de Destinatário\n");
				msg.append("Copyright Octa© 2015-2016\n");
				JOptionPane.showMessageDialog(null, msg.toString(), "Sobre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAjuda.add(mntmSobre);
		
		JSeparator separator_1 = new JSeparator();
		mnAjuda.add(separator_1);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (frmBifrostInetegrator.getContentPane().getLayout());
				cl.show(frmBifrostInetegrator.getContentPane(), "cardRegistro");
				
			}
		});
		mnAjuda.add(mntmRegistrar);

		JButton btnIniciarInetegrador = new JButton("Iniciar Integrador");
		btnIniciarInetegrador.setBounds(7, 7, 168, 28);
		btnIniciarInetegrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = Server.getInstance();
				if (sapOnline) {
					iconStatusSap.setIcon(new ImageIcon("images/disconnect.png"));
					btnIniciarInetegrador.setText("Iniciar Integrador");
					sapOnline = false;
					server.stop();
				} else {
					server.start();
					iconStatusSap.setIcon(new ImageIcon("images/connect.png"));
					btnIniciarInetegrador.setText("Parar Integrador");
					sapOnline = true;
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 337, 952, 192);

		txtAreaLog = new JTextArea();
		scrollPane.setViewportView(txtAreaLog);
		txtAreaLog.setEditable(false);
		panelPrincipal.setLayout(null);
		panelPrincipal.add(btnIniciarInetegrador);
		panelPrincipal.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(7, 70, 951, 235);
		panelPrincipal.add(scrollPane_1);
		DefaultTableModel model = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // JTable Model
		model.addColumn("#");
		model.addColumn("Data");
		model.addColumn("Interface");
		tableLog = new JTable(model);

		tableLog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableLog.getColumnModel().getColumn(0).setPreferredWidth(140);
		tableLog.getColumnModel().getColumn(1).setPreferredWidth(400);
		tableLog.getColumnModel().getColumn(2).setPreferredWidth(400);

		scrollPane_1.setViewportView(tableLog);

		JLabel lblMensagensProcessadas = DefaultComponentFactory.getInstance().createLabel("Mensagens Processadas:");
		lblMensagensProcessadas.setBounds(7, 42, 148, 16);
		panelPrincipal.add(lblMensagensProcessadas);

		JLabel lblLogMonitor = new JLabel("Log Monitor:");
		lblLogMonitor.setBounds(7, 317, 111, 16);
		panelPrincipal.add(lblLogMonitor);
		
		JPanel panelRegistro = new JPanel();
		frmBifrostInetegrator.getContentPane().add(panelRegistro, "cardRegistro");
		panelRegistro.setLayout(null);
		
		txtKeyRegistro = new JTextField();
		txtKeyRegistro.setBounds(222, 82, 519, 28);
		panelRegistro.add(txtKeyRegistro);
		txtKeyRegistro.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(437, 242, 90, 28);
		panelRegistro.add(btnRegistrar);
		
		txtCnpjRegistro = new JTextField();
		txtCnpjRegistro.setColumns(10);
		txtCnpjRegistro.setBounds(222, 42, 519, 28);
		panelRegistro.add(txtCnpjRegistro);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setHorizontalAlignment(SwingConstants.CENTER);
		lblCnpj.setBounds(461, 23, 41, 16);
		panelRegistro.add(lblCnpj);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblKey.setBounds(461, 67, 41, 16);
		panelRegistro.add(lblKey);
		
		JLabel lblNewLabel = new JLabel("<html><body>\r\n<center>\r\nOl\u00E1 <br> \r\nVoc\u00EA ainda n\u00E3o tem uma chave de registro?<br>\r\nObtenha uma no site www.klustter.com.br.\r\n</center></body></html>");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(235, 267, 494, 86);
		panelRegistro.add(lblNewLabel);
		
		txtNumeroSistemaRegistro = new JTextField();
		txtNumeroSistemaRegistro.setColumns(10);
		txtNumeroSistemaRegistro.setBounds(222, 122, 519, 28);
		panelRegistro.add(txtNumeroSistemaRegistro);
		
		JLabel lblNumeroDoSistema = new JLabel("Numero do Sistema SAP");
		lblNumeroDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDoSistema.setBounds(368, 108, 227, 16);
		panelRegistro.add(lblNumeroDoSistema);
		
		txtChaveInstalacao = new JTextField();
		txtChaveInstalacao.setColumns(10);
		txtChaveInstalacao.setBounds(222, 162, 519, 28);
		panelRegistro.add(txtChaveInstalacao);
		
		JLabel lblChaveDoSistema = new JLabel("Chave do Sistema");
		lblChaveDoSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblChaveDoSistema.setBounds(368, 147, 227, 16);
		panelRegistro.add(lblChaveDoSistema);
		
		JButton btnChaveSistema = new JButton("Gerar Chave do Sistema");
		btnChaveSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChaveInstalacao.setText(KsUtils.gKey(txtCnpjRegistro.getText()));
				ConfigUtils.setCNPJ(txtCnpjRegistro.getText());
			}
		});
		btnChaveSistema.setBounds(385, 202, 193, 28);
		panelRegistro.add(btnChaveSistema);
	}

	public static void infoView(String msg) {
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		txtAreaLog.append(dateformat.format(date) + " :: " + msg + "\n");
	}

	public static void addRowLog(int count, String data, String layout) {
		DefaultTableModel model = (DefaultTableModel) tableLog.getModel();
		model.addRow(new Object[] { count, data, layout });
	}

	public static void getDataLog() {

		while (tableLog.getRowCount() > 0) {
			((DefaultTableModel) tableLog.getModel()).removeRow(0);
		}
		int count = 0;
		for (String filePath : ViewUtils.getFilesFromFolder(ConfigUtils.getFolderLog())) {
			Path p = Paths.get(filePath);
			String fileName = p.getFileName().toString();
			String layout = fileName.substring(0, fileName.lastIndexOf("_"));
			String data = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf(".xml"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = null;
			try {
				date = formatter.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String dateFormated = dateformat.format(date);
			BifrostView.addRowLog(count, dateFormated, layout);
			count++;
		}

	}

	public static void setLogCeritificado(String msg) {
		txtAreaLogCertificado.append(msg + "\n");
	}

	public static void setLogConfigSAP(String msg) {
		textAreaLogConexao.append(msg + "\n");
	}

	public static void setProgressbarStart() {
		lblProgressBar.setIcon(new ImageIcon("images/104.gif"));
	}

	public static void setProgressbarStop() {
		lblProgressBar.setIcon(null);
	}

	public boolean validate() {

		if ("".equals(txtUser.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o nome do usuário");
			return false;
		} else if ("".equals(txtPasssword.getText())) {
			JOptionPane.showMessageDialog(null, "Informe a senha");
			return false;
		} else if ("".equals(txtClient.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o client sap");
			return false;
		} else if ("".equals(txtLanguage.getText())) {
			JOptionPane.showMessageDialog(null, "Informe a language sap");
			return false;
		} else if ("".equals(txtProgramId.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o programa registrado");
			return false;
		} else if ("".equals(txtSapGW.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o gateway SAP");
			return false;
		} else if ("".equals(txtSapHost.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o IP ou nome do host SAP");
			return false;
		} else if ("".equals(txtConnectionCount.getText())) {
			JOptionPane.showMessageDialog(null, "Informe o numero de threads");
			return false;
		}
		return true;
	}
}
