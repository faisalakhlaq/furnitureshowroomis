package gui.searchpanels;

import utils.Helper;
import database.caller.SearchProductCaller;

@SuppressWarnings("serial")
public class SearchProductPanel extends AbstractSearchPanel {

    public SearchProductPanel() {
	addButtonListener(new SearchProductCaller(this));
    }

    public int getPId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("Product ID should be a digit");
	}
	id = Integer.valueOf(strId.trim());
	return id;
    }
}
