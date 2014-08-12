package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.ExpensesPanel;

import java.awt.event.ActionEvent;

import model.Expenses;
import database.caller.ISCaller;

public class ExpensesPanelCaller extends ISCaller {

    private Expenses expense = null;

    public ExpensesPanelCaller() {
    }

    public ExpensesPanelCaller(Expenses e) {
	expense = e;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

    public void perform() {
	ExpensesPanel expensesPanel = null;
	if (expense != null) {
	    expensesPanel = new ExpensesPanel(expense);
	} else {
	    expensesPanel = new ExpensesPanel();
	}
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Expenses", expensesPanel);
    }
}
