package gui.searchpanels.callers;

import gui.panels.DesktopTabbedPane;
import gui.searchpanels.SearchProductPanel;

import java.awt.event.ActionEvent;

import database.caller.ISCaller;

public class SearchProductPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	SearchProductPanel p = new SearchProductPanel();
	pane.addPanel("Search Product", p);
    }

}
