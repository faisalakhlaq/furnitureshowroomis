package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.SalePanel;

import java.awt.event.ActionEvent;

import callers.ISCaller;

import model.Sale;

public class SalesPanelCaller extends ISCaller {
    private Sale s = null;

    public SalesPanelCaller() {
    }

    public SalesPanelCaller(Sale s) {
	this.s = s;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

    public void perform() {
	SalePanel salePanel = null;
	if (s != null) {
	    salePanel = new SalePanel(s);
	} else {
	    salePanel = new SalePanel();
	}
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("sale", salePanel);
    }
}
