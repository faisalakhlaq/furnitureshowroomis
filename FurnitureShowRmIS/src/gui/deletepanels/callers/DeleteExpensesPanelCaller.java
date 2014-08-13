package gui.deletepanels.callers;

import gui.deletepanels.DeleteExpensesPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

public class DeleteExpensesPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Delete Expenses", new DeleteExpensesPanel());
    }

}
