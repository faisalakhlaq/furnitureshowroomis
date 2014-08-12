package utils;

import gui.panels.MainPanel;

import javax.swing.SwingUtilities;

public class ShowroomISDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {

	    @Override
	    public void run() {
		MainPanel panel = new MainPanel();
		panel.createAndDisplayGui();
	    }
	});
    }
}
