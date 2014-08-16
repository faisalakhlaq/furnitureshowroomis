package gui.deletepanels.callers;

import gui.deletepanels.DeleteSalePanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

public class DeleteSalePanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Delete Sale", new DeleteSalePanel());
    }

}
