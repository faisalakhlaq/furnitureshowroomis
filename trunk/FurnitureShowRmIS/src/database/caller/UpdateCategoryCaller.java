package database.caller;

import gui.dailogue.MessageDialog;

import java.awt.event.ActionEvent;

import model.Category;
import callers.ISCaller;
import database.CategoryHandler;

public class UpdateCategoryCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

    }

    public static void perform(Category customer) throws Exception {
	if (customer == null) {
	    new MessageDialog("Error",
		    "Unable to update the manfacturer! Manufacturer = null");
	}

	CategoryHandler handler = new CategoryHandler();
	handler.updateCategory(customer);
    }
}
