package database.caller;

import gui.dailogue.MessageDialog;
import gui.deletepanels.DeleteProductPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;
import database.ProductHandler;

public class DeleteProductCaller extends ISCaller {
    private DeleteProductPanel panel = null;

    public DeleteProductCaller(DeleteProductPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    try {
		int id = panel.getProductId();
		ProductHandler handler = new ProductHandler();
		handler.deleteProduct(id);
		panel.refreshComboBoxModel();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

}
