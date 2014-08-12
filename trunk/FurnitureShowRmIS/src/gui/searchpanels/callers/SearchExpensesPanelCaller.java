package gui.searchpanels.callers;

import gui.panels.DesktopTabbedPane;
import gui.searchpanels.SearchExpensesPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;


public class SearchExpensesPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	SearchExpensesPanel p = new SearchExpensesPanel();
	pane.addPanel("Search Expenses", p);
    }

}
