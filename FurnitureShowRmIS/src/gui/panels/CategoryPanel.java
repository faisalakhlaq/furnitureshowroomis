package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Category;
import utils.Helper;
import database.CategoryHandler;

@SuppressWarnings("serial")
public class CategoryPanel extends AbstractPanel {
    private JButton editBtn = null;

    private JButton updateBtn = null;

    private JButton deleteBtn = null;

    private JButton saveBtn = null;

    private JLabel categoryIdLbl = null;

    private JLabel categoryNameLbl = null;

    private JTextField categoryIdTxt = null;

    private JTextField categoryNameTxt = null;

    private Category category = null;

    private JLabel resultMsgLbl;

    public CategoryPanel() {
	addPanels();
    }

    public CategoryPanel(Category c) {
	addPanels();
	this.category = c;
	fillTextFields();
    }

    private void fillTextFields() {
	if (category == null)
	    return;
	categoryIdTxt.setText(String.valueOf(category.getCategoryId()));
	categoryNameTxt.setText(category.getCategoryName());

    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	categoryIdLbl = new JLabel("Category Id");
	categoryNameLbl = new JLabel("Category Name");

	categoryIdTxt = new JTextField(8);
	categoryNameTxt = new JTextField(20);

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(categoryIdLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(categoryIdTxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(categoryNameLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(categoryNameTxt, c);

	return centerPanel;

    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	editBtn = new JButton("Edit");
	editBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

	    }
	});

	updateBtn = new JButton("Update");
	updateBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		
	    }
	});
	deleteBtn = new JButton("Delete");
	deleteBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {

	    }
	});
	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    Category c = new Category();

		    String categoryName = categoryNameTxt.getText();
		    if (Helper.isEmpty(categoryName)) {
			throw new Exception("Please provide category name");
		    }
		    c.setCategoryName(categoryName);
		   
		    CategoryHandler categoryhandler = new CategoryHandler();
		    categoryhandler.saveCategory(c);
		    clearTextFields();
		    displayMessage(true);
		} catch (Exception e) {
		    new MessageDialog("Error", e.getMessage());
		}
	    }
	});
		
	
	buttonPanel.add(editBtn);
	buttonPanel.add(updateBtn);
	buttonPanel.add(deleteBtn);
	buttonPanel.add(saveBtn);

	return buttonPanel;
    }

    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Category"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void clearTextFields() {
	categoryIdTxt.setText(null);
	categoryNameTxt.setText(null);
    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Category saved");
		    } else {
			resultMsgLbl
				.setText("Sorry! Transaction unsuccessfull");
		    }
		    Thread.sleep(2000);
		    resultMsgLbl.setText(null);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	};
	t.start();
    }

    public void createAndShowGUI() {

    }

    private void setGridBagConstraints(GridBagConstraints c, int gridx,
	    int gridy, int placement, int paddingTop, int paddingLeft) {
	c.fill = GridBagConstraints.HORIZONTAL;
	c.anchor = placement;
	c.insets = new Insets(paddingTop, paddingLeft, 0, 0); // top and left
							      // padding
	c.weightx = 0.75;
	c.weighty = 0;
	c.gridx = gridx;
	c.gridy = gridy;
	c.gridwidth = 1;
    }

    public int getCategoryId() {
	return Integer.valueOf(categoryIdTxt.getText());
    }
}
