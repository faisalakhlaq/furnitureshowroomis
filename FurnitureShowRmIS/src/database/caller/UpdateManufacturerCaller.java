package database.caller;

import gui.dailogue.MessageDialog;

import java.awt.event.ActionEvent;

import model.Manufacturer;
import callers.ISCaller;
import database.ManufacturerHandler;

public class UpdateManufacturerCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {

	// TODO Auto-generated method stub
    }

    public static void perform(Manufacturer customer) throws Exception {
	if (customer == null) {
	    new MessageDialog("Error",
		    "Unable to update the manfacturer! Manufacturer = null");
	}

	ManufacturerHandler handler = new ManufacturerHandler();
	handler.updateManufacturer(customer);
    }
}
