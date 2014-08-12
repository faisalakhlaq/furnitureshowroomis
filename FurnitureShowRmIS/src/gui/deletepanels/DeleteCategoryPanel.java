package gui.deletepanels;

import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import utils.Helper;

import database.CategoryHandler;
import database.caller.DeleteCategoryCaller;

public class DeleteCategoryPanel extends AbstractDeletePanel {

    public DeleteCategoryPanel() {
	super();
	addButtonListener(new DeleteCategoryCaller(this));
    }

    @Override
    protected DefaultComboBoxModel<Integer> getComboBoxModel() {
	DefaultComboBoxModel<Integer> model = null;
	CategoryHandler handler = new CategoryHandler();
	try {
	    Vector<Integer> ids = handler.getAllCategoryIds();
	    model = new DefaultComboBoxModel<Integer>(ids);
	} catch (Exception e) {
	    model = new DefaultComboBoxModel<Integer>();
	}
	return model;
    }

    public int getCategoryId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Category ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }
}