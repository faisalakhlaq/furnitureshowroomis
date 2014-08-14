package gui.deletepanels;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import utils.Helper;
import database.ExpensesHandler;
import database.caller.DeleteExpensesCaller;

public class DeleteExpensesPanel extends AbstractDeletePanel {
    public DeleteExpensesPanel() {
	super();
	addButtonListener(new DeleteExpensesCaller(this));
    }

    @Override
    protected DefaultComboBoxModel<Integer> getComboBoxModel() {
	DefaultComboBoxModel<Integer> model = null;
	ExpensesHandler handler = new ExpensesHandler();
	try {
	    Vector<Integer> ids = handler.getAllExpensesIds();
	    model = new DefaultComboBoxModel<Integer>(ids);
	} catch (Exception e) {
	    model = new DefaultComboBoxModel<Integer>();
	}
	return model;
    }

    public int getExpensesId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Expenses ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	// TODO Auto-generated method stub
	
    }

}
