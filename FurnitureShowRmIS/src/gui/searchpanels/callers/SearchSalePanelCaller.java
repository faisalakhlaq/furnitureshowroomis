package gui.searchpanels.callers;

import gui.panels.DesktopTabbedPane;
import gui.searchpanels.SearchSalePanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;


public class SearchSalePanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	SearchSalePanel p = new SearchSalePanel();
	pane.addPanel("Search Sale", p);
    }

}
