package gui.deletepanels;

import gui.dailogue.MessageDialog;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import model.Sale;

import utils.Helper;
import database.SaleHandler;
import database.caller.DeleteSaleCaller;

@SuppressWarnings("serial")
public class DeleteSalePanel extends AbstractDeletePanel {
    public DeleteSalePanel() {
	super();
	addButtonListener(new DeleteSaleCaller(this));
    }

    protected DefaultComboBoxModel<Integer> getComboBoxModel() {
	DefaultComboBoxModel<Integer> model = null;
	SaleHandler handler = new SaleHandler();
	try {
	    Vector<Integer> ids = handler.getAllSaleIds();
	    model = new DefaultComboBoxModel<Integer>(ids);
	} catch (Exception e) {
	    model = new DefaultComboBoxModel<Integer>();
	}
	return model;
    }

    public int getSaleId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The sale id should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

    @Override
    public void itemStateChanged(ItemEvent arg0) {
	try {
	    int id = getSaleId();
	    SaleHandler handler = new SaleHandler();
	    Sale sale = handler.searchSale(id);
	    setNameText(sale.getName());
	} catch (Exception e) {
	    new MessageDialog("Error", e.getMessage());
	    e.printStackTrace();
	}
    }

}
