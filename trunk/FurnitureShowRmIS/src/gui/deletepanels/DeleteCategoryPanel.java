package gui.deletepanels;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import model.Category;
import utils.Helper;
import database.CategoryHandler;
import database.caller.DeleteCategoryCaller;

@SuppressWarnings("serial")
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

    @Override
    public void itemStateChanged(ItemEvent arg0) {
	try {
	    int id = getCategoryId();
	    CategoryHandler handler = new CategoryHandler();
	    Category c = handler.searchCategory(id);
	    setNameText(c.getCategoryName());
	} catch (Exception e) {
	    e.printStackTrace();
	    setNameText(null);
	}
    }
}