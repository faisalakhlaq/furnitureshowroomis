package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;
import gui.panels.callers.ExpensesPanelCaller;
import gui.searchpanels.SearchExpensesPanel;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import callers.ISCaller;

import model.Expenses;
import database.ExpensesHandler;

public class SearchExpensesCaller extends ISCaller {

    private SearchExpensesPanel panel = null;

    public SearchExpensesCaller(SearchExpensesPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    ExpensesHandler handler = new ExpensesHandler();
	    try {
		Expenses e = handler.searchExpenses(panel.getEId());

		if (e == null) {
		    new MessageDialog("Sorry", "No Expenses Found",
			    JOptionPane.INFORMATION_MESSAGE);
		} else {
		    ClosePanelCaller.perform(panel);
		    ExpensesPanelCaller caller = new ExpensesPanelCaller(e);
		    caller.perform();
		}
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }
}