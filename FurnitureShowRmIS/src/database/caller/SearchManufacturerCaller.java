package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;
import gui.panels.callers.ManufacturerPanelCaller;
import gui.searchpanels.SearchManufacturerPanel;

import java.awt.event.ActionEvent;

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

		ClosePanelCaller.perform(panel);
		ManufacturerPanelCaller caller = new ManufacturerPanelCaller(m);
		caller.perform();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }
}