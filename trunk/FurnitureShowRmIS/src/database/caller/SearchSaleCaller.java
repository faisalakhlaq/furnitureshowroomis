package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;
import gui.panels.callers.SalesPanelCaller;
import gui.searchpanels.SearchSalePanel;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import callers.ISCaller;

import model.Sale;
import database.SaleHandler;

public class SearchSaleCaller extends ISCaller {
    private SearchSalePanel panel = null;

    public SearchSaleCaller(SearchSalePanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    SaleHandler handler = new SaleHandler();
	    try {
		Sale s = handler.searchSale(panel.getSId());

		if (s == null) {
		    new MessageDialog("Sorry", "No Sale Found",
			    JOptionPane.INFORMATION_MESSAGE);
		} else {
		    ClosePanelCaller.perform(panel);
		    SalesPanelCaller caller = new SalesPanelCaller(s);
		    caller.perform();
		}
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

}
