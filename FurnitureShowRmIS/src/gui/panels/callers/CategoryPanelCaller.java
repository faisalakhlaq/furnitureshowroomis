package gui.panels.callers;

import gui.panels.CategoryPanel;
import gui.panels.DesktopTabbedPane;

import java.awt.event.ActionEvent;

import callers.ISCaller;

import model.Category;

public class CategoryPanelCaller extends ISCaller {
    private Category category = null;

    public CategoryPanelCaller() {
    }

    public CategoryPanelCaller(Category c) {
	category = c;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

    public void perform() {
	CategoryPanel categoryPanel = null;
	if (category != null) {
	    categoryPanel = new CategoryPanel(category);
	} else {
	    categoryPanel = new CategoryPanel();
	}

	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Category", categoryPanel);
    }

}
