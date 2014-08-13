package gui.deletepanels.callers;

import gui.deletepanels.DeleteManufacturerPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

public class DeleteManufacturerPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Delete Manufacturer", new DeleteManufacturerPanel());
    }

}
