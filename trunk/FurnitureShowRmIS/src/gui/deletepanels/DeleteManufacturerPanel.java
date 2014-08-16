package gui.deletepanels;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import model.Manufacturer;
import utils.Helper;
import database.ManufacturerHandler;
import database.caller.DeleteManufacturerCaller;

@SuppressWarnings("serial")
public class DeleteManufacturerPanel extends AbstractDeletePanel {
    public DeleteManufacturerPanel() {
	super();
	addButtonListener(new DeleteManufacturerCaller(this));

    }

    @Override
    protected DefaultComboBoxModel<Integer> getComboBoxModel() {
	DefaultComboBoxModel<Integer> model = null;
	ManufacturerHandler handler = new ManufacturerHandler();
	try {
	    Vector<Integer> ids = handler.getAllManufacturerIds();
	    model = new DefaultComboBoxModel<Integer>(ids);
	} catch (Exception e) {
	    model = new DefaultComboBoxModel<Integer>();
	}
	return model;
    }

    public int getManufacturerId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Manufacturer ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
	try {
	    int id = getManufacturerId();
	    ManufacturerHandler handler = new ManufacturerHandler();
	    Manufacturer m = handler.searchManufacturer(id);
	    setNameText(m.getmName());
	} catch (Exception e) {
	    e.printStackTrace();
	    setNameText(null);
	}

    }
}