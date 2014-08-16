package database.caller;

import gui.dailogue.MessageDialog;
import gui.deletepanels.DeleteSalePanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;
import database.SaleHandler;

public class DeleteSaleCaller extends ISCaller {
    private DeleteSalePanel panel = null;

    public DeleteSaleCaller(DeleteSalePanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    try {
		int id = panel.getSaleId();
		SaleHandler handler = new SaleHandler();
		handler.deleteSale(id);
		panel.refreshComboBoxModel();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

}
