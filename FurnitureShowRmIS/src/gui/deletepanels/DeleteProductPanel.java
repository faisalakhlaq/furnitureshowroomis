package gui.deletepanels;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import model.Product;
import utils.Helper;
import database.ProductHandler;
import database.caller.DeleteProductCaller;

@SuppressWarnings("serial")
public class DeleteProductPanel extends AbstractDeletePanel {

    public DeleteProductPanel() {
	super();
	addButtonListener(new DeleteProductCaller(this));
    }

    @Override
    protected DefaultComboBoxModel<Integer> getComboBoxModel() {
	DefaultComboBoxModel<Integer> model = null;
	ProductHandler handler = new ProductHandler();
	try {
	    Vector<Integer> ids = handler.getAllProductsIds();
	    model = new DefaultComboBoxModel<Integer>(ids);
	} catch (Exception e) {
	    model = new DefaultComboBoxModel<Integer>();
	}
	return model;
    }

    public int getProductId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Product ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
	
	try {
	    int id = getProductId();
	    ProductHandler handler = new ProductHandler();
	    Product p = handler.searchProduct(id);
	    setNameText(p.getProductName());
	} catch (Exception e) {
	    e.printStackTrace();
	    setNameText(null);
	}
    }
}
