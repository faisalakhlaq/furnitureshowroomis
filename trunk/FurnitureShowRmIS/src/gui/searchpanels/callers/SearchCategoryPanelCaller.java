package gui.searchpanels.callers;

import gui.panels.DesktopTabbedPane;
import gui.searchpanels.SearchCategoryPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;


public class SearchCategoryPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	SearchCategoryPanel p = new SearchCategoryPanel();
	pane.addPanel("Search Category", p);
    }

}
