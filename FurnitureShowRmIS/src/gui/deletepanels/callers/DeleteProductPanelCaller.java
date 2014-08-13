package gui.deletepanels.callers;

import gui.deletepanels.DeleteProductPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

public class DeleteProductPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Delete Product", new DeleteProductPanel());
    }

}
