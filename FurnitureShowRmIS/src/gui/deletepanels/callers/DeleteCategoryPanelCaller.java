package gui.deletepanels.callers;

import gui.deletepanels.DeleteCategoryPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

public class DeleteCategoryPanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Delete Category", new DeleteCategoryPanel());
    }
}
