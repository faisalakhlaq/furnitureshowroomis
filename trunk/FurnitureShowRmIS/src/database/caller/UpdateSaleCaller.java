package database.caller;

import gui.dailogue.MessageDialog;

import java.awt.event.ActionEvent;

import model.Sale;
import callers.ISCaller;
import database.SaleHandler;

public class UpdateSaleCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

    }

    public static void perform(Sale customer) throws Exception {
	if (customer == null) {
	    new MessageDialog("Error", "Unable to update the sale! Sale = null");
	}

	SaleHandler handler = new SaleHandler();
	handler.updateSale(customer);
    }
}
