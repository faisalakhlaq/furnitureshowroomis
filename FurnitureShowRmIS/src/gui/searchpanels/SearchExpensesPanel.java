package gui.searchpanels;

import utils.Helper;
import database.caller.SearchExpensesCaller;

@SuppressWarnings("serial")
public class SearchExpensesPanel extends AbstractSearchPanel {
    public SearchExpensesPanel() {
	addButtonListener(new SearchExpensesCaller(this));
    }

    public int getEId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Expenses ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

}
