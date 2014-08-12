package gui.panels.callers;

import gui.GuiPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import database.caller.ISCaller;

public class ClosePanelCaller extends ISCaller {

    @Override
    public void actionPerformed(ActionEvent e) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.removeTabAt(pane.getSelectedIndex());
    }

    public static void perform(GuiPanel p) {
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.remove(p);
    }
}
