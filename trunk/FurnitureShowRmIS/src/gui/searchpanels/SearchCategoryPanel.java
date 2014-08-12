package gui.searchpanels;

import database.caller.SearchCategoryCaller;
import utils.Helper;

@SuppressWarnings("serial")
public class SearchCategoryPanel extends AbstractSearchPanel {
    public SearchCategoryPanel() {
	addButtonListener(new SearchCategoryCaller(this));
    }

    public int getCategoryId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Category ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

}
