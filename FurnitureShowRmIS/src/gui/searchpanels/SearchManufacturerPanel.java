package gui.searchpanels;

import database.caller.SearchManufacturerCaller;
import utils.Helper;

@SuppressWarnings("serial")
public class SearchManufacturerPanel extends AbstractSearchPanel {

    public SearchManufacturerPanel() {
	addButtonListener(new SearchManufacturerCaller(this));
    }

    public int getMId() throws Exception {
	int id = 0;
	String strId = getId();
	if (!Helper.isDigit(strId)) {
	    throw new Exception("The Manufacturer ID should be a digit");
	} else {
	    id = Integer.valueOf(strId);
	}
	return id;
    }

}