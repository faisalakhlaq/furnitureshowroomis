package database.caller;

import gui.dailogue.MessageDialog;
import gui.deletepanels.DeleteManufacturerPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;
import database.ManufacturerHandler;

public class DeleteManufacturerCaller extends ISCaller {
    private DeleteManufacturerPanel panel = null;

    public DeleteManufacturerCaller(DeleteManufacturerPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    try {
		int id = panel.getManufacturerId();
		ManufacturerHandler handler = new ManufacturerHandler();
		handler.deleteManufacturer(id);
		panel.refreshComboBoxModel();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

}
