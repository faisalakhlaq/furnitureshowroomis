package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.ProductPanel;

import java.awt.event.ActionEvent;

import model.Product;
import database.caller.ISCaller;

public class ProductPanelCaller extends ISCaller {

    private Product p = null;

    public ProductPanelCaller() {
    }

    public ProductPanelCaller(Product p) {
	this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent p) {
	perform();
    }

    public void perform() {
	ProductPanel productPanel = null;
	if (p != null) {
	    productPanel = new ProductPanel(p);
	} else {
	    productPanel = new ProductPanel();
	}
	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Product", productPanel);
    }
}
