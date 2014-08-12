package database.caller;

import gui.dailogue.MessageDialog;
import gui.panels.callers.CategoryPanelCaller;
import gui.panels.callers.ClosePanelCaller;
import gui.searchpanels.SearchCategoryPanel;

import java.awt.event.ActionEvent;

import model.Category;
import database.CategoryHandler;

public class SearchCategoryCaller extends ISCaller {
    private SearchCategoryPanel panel = null;

    public SearchCategoryCaller(SearchCategoryPanel p) {
	panel = p;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	if (panel != null) {
	    CategoryHandler handler = new CategoryHandler();
	    try {
		Category c = handler.searchCategory(panel.getCategoryId());

		ClosePanelCaller.perform(panel);
		CategoryPanelCaller caller = new CategoryPanelCaller(c);
		caller.perform();
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}

    }

}
