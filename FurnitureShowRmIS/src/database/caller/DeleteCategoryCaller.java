package database.caller;

import gui.dailogue.MessageDialog;
import gui.deletepanels.DeleteCategoryPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;

import database.CategoryHandler;

public class DeleteCategoryCaller extends ISCaller {

    private DeleteCategoryPanel panel = null;

    public DeleteCategoryCaller(DeleteCategoryPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    try {
		int id = panel.getCategoryId();
		CategoryHandler handler = new CategoryHandler();
		handler.deleteCategory(id);
		panel.refreshComboBoxModel();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

}
