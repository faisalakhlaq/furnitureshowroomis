package gui.searchpanels;

import database.caller.SearchSaleCaller;
import utils.Helper;

@SuppressWarnings("serial")
public class SearchSalePanel extends AbstractSearchPanel {
    public SearchSalePanel() {
	addButtonListener(new SearchSaleCaller(this));
    }

    public int getSId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("Sale ID should be a digit");
	}
	id = Integer.valueOf(strId.trim());
	return id;
    }
}
