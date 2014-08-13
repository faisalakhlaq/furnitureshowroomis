package database.caller;

import gui.dailogue.MessageDialog;
import gui.deletepanels.DeleteExpensesPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;
import database.ExpensesHandler;

public class DeleteExpensesCaller extends ISCaller {
    private DeleteExpensesPanel panel = null;

    public DeleteExpensesCaller(DeleteExpensesPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    try {
		int id = panel.getExpensesId();
		ExpensesHandler handler = new ExpensesHandler();
		handler.deleteExpenses(id);
		panel.refreshComboBoxModel();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}

    }

}
