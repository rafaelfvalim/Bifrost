package br.octa.view;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.log4j.Logger;

import br.octa.service.nfe.ws.EnvConfRecebtoServiceSync;
import br.octa.utils.ConfigUtils;

public class IconeTray {

	private TrayIcon icone;
	private PopupMenu popup;
	private Monitor monitor;
	final static Logger logger = Logger.getLogger(EnvConfRecebtoServiceSync.class);

	private void iniciaMenu()
	{
		popup = new PopupMenu();
		popup.add(createMenuItem("clickLog", "Visualizar Log", new LogClickListener(getMonitor())));
		popup.add(createMenuItem("fechar", "Fechar", new ExitClickListener()));
	}

	private MenuItem createMenuItem(String nome, String label, ActionListener listener)
	{
		MenuItem result = new MenuItem();
		result.setName(nome);
		result.setLabel(label);
		result.addActionListener(listener);
		
		return (result);
	}
	
	public IconeTray(SystemTray sysTray)
	{
		File f = new File("images/icone.gif");
		Image imageIcon = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
		
		iniciaMenu();
		
		icone = new TrayIcon(imageIcon, "Integrador SAP-NFe - Filial " + ConfigUtils.getCompanyName());
		icone.setPopupMenu(popup);
		icone.addMouseListener(new MenuClickListener(getMonitor()));
		try
		{
			sysTray.add(icone);
		}catch(Exception ex)
		{
			logger.info(ex);
		}
	}
	
	private Monitor getMonitor()
	{
		if (monitor == null)
			monitor = new Monitor();
		
		return (monitor); 
	}
	
}
