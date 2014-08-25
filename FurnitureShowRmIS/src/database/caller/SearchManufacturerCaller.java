package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;
import gui.panels.callers.ManufacturerPanelCaller;
import gui.searchpanels.SearchManufacturerPanel;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import callers.ISCaller;

import model.Manufacturer;
import database.ManufacturerHandler;

public class SearchManufacturerCaller extends ISCaller {
    private SearchManufacturerPanel panel = null;

    public SearchManufacturerCaller(SearchManufacturerPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    ManufacturerHandler handler = new ManufacturerHandler();
	    try {
		Manufacturer m = handler.searchManufacturer(panel.getMId());
		if (m != null) {
		    ClosePanelCaller.perform(panel);
		    ManufacturerPanelCaller caller = new ManufacturerPanelCaller(
			    m);
		    caller.perform();
		} else {
		    new MessageDialog("Sorry", "No manufactuer found",
			    JOptionPane.INFORMATION_MESSAGE);
		}
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }
}