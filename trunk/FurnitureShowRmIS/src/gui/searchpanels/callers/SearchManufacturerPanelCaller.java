package gui.searchpanels.callers;

import gui.panels.DesktopTabbedPane;
import gui.searchpanels.SearchManufacturerPanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;


public class SearchManufacturerPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	SearchManufacturerPanel p = new SearchManufacturerPanel();
	pane.addPanel("Search Manufacturer", p);
    }

}
