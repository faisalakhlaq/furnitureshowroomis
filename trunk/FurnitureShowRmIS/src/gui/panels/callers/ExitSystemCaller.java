package gui.panels.callers;

import gui.panels.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitSystemCaller extends WindowAdapter implements ActionListener {
    public void windowClosing(WindowEvent e) {
	System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Object o = e.getSource();
	if (o instanceof MainPanel) {
	    // TODO close all the including windows and check for unsaved data
	    // prompt the user if something is unsaved
	}
	windowClosing(null);
    }
}
