package br.octa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogClickListener implements ActionListener{

	Monitor monitor = null;

	public LogClickListener(Monitor monitor)
	{
		this.monitor = monitor;
	}

	public void actionPerformed(ActionEvent e) {
		if (e != null)
		{
			if (monitor == null)
				monitor = new Monitor();

			monitor.run();
		}
	}

}
