package br.octa.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuClickListener implements MouseListener{

	private Monitor monitor = null;

	public MenuClickListener(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			if (monitor == null)
				monitor = new Monitor();

			monitor.run();
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}
