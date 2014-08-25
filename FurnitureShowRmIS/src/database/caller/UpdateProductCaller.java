package database.caller;

import gui.dailogue.MessageDialog;

import java.awt.event.ActionEvent;

import model.Product;
import callers.ISCaller;
import database.ProductHandler;

public class UpdateProductCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

    }

    public static void perform(Product customer) throws Exception {
	if (customer == null) {
	    new MessageDialog("Error",
		    "Unable to update the product! Product = null");
	}

	ProductHandler handler = new ProductHandler();
	handler.updateProduct(customer);
    }
}