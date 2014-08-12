package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;
import gui.panels.callers.ProductPanelCaller;
import gui.searchpanels.SearchProductPanel;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import model.Product;
import database.ProductHandler;

public class SearchProductCaller extends ISCaller {

    private SearchProductPanel panel = null;

    public SearchProductCaller(SearchProductPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    ProductHandler handler = new ProductHandler();
	    try {
		Product m = handler.searchProduct(panel.getPId());

		if (m == null) {
		    new MessageDialog("Sorry", "No Product Found",
			    JOptionPane.INFORMATION_MESSAGE);
		} else {
		    ClosePanelCaller.perform(panel);
		    ProductPanelCaller caller = new ProductPanelCaller(m);
		    caller.perform();
		}
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }
}