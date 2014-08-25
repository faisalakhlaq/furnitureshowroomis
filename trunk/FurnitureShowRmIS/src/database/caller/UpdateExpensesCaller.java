package database.caller;

import gui.dailogue.MessageDialog;

import java.awt.event.ActionEvent;

import model.Expenses;
import callers.ISCaller;
import database.ExpensesHandler;

public class UpdateExpensesCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

    }

    public static void perform(Expenses customer) throws Exception {
	if (customer == null) {
	    new MessageDialog("Error",
		    "Unable to update the expenses! Expenses = null");
	}

	ExpensesHandler handler = new ExpensesHandler();
	handler.updateExpenses(customer);
    }
}
