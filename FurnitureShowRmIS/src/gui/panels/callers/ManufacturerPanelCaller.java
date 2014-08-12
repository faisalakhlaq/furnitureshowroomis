package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.ManufacturerPanel;

import java.awt.event.ActionEvent;

import model.Manufacturer;
import database.caller.ISCaller;

public class ManufacturerPanelCaller extends ISCaller {
    private Manufacturer manufacturer = null;

    public ManufacturerPanelCaller() {
    }

    public ManufacturerPanelCaller(Manufacturer m) {
	manufacturer = m;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

    public void perform() {
	ManufacturerPanel manufacturerPanel = null;
	if (manufacturer != null) {
	    manufacturerPanel = new ManufacturerPanel(manufacturer);
	} else {
	    manufacturerPanel = new ManufacturerPanel();
	}

	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Manufacturer", manufacturerPanel);
    }

}
