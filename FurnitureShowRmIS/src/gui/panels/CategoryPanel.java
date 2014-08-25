package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Category;
import database.CategoryHandler;
import database.caller.UpdateCategoryCaller;

@SuppressWarnings("serial")
public class CategoryPanel extends AbstractPanel {
    private JButton editBtn = null;
    private JButton cancelBtn = null;
    private JButton saveBtn = null;
    private JButton exitBtn = null;
    private JButton clearBtn = null;

    private JLabel categoryIdLbl = null;
    private JLabel categoryNameLbl = null;

    private JTextField categoryIdTxt = null;
    private JTextField categoryNameTxt = null;

    private Category category = null;
    private boolean editMode = false;

    private JLabel resultMsgLbl = null;

    public CategoryPanel() {
	addPanels();
	enableTextFields(true);
    }

    public CategoryPanel(Category c) {
	addPanels();
	this.category = c;
	fillTextFields();
	enableTextFields(false);
    }

    private void fillTextFields() {
	if (category == null)
	    return;
	categoryIdTxt.setText(String.valueOf(category.getCategoryId()));
	categoryNameTxt.setText(category.getCategoryName());

    }

    private void enableTextFields(boolean enable) {
	categoryIdTxt.setEnabled(false);
	categoryNameTxt.setEnabled(enable);
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	categoryIdLbl = new JLabel("Category Id");
	categoryNameLbl = new JLabel("Category Name");

	categoryIdTxt = new JTextField(8);
	categoryNameTxt = new JTextField(20);

	resultMsgLbl = new JLabel();

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

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_START, 15, 10);
	c.gridwidth = 2;
	centerPanel.add(resultMsgLbl, c);
	resultMsgLbl.setVisible(false);

	return centerPanel;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	editBtn = new JButton("Edit");
	editBtn.addActionListener(new EditCategoryListener());
	cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new CancelEditListener());
	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new SaveCategoryActionListener());
	clearBtn = new JButton("Clear");
	clearBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clearTextFields();
	    }
	});
	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());

	buttonPanel.add(cancelBtn);
	buttonPanel.add(editBtn);
	buttonPanel.add(saveBtn);
	buttonPanel.add(clearBtn);
	buttonPanel.add(exitBtn);

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

    private class SaveCategoryActionListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    try {
		Category c = new Category();

		// // String cId = categoryIdTxt.getText();
		// String categoryName = categoryNameTxt.getText();
		// if (Helper.isEmpty(categoryName)) {
		// throw new Exception("Please provide category name");
		// }
		// c.setCategoryName(categoryName);
		c = getTextFieldValues();
		CategoryHandler categoryhandler = new CategoryHandler();
		categoryhandler.saveCategory(c);
		clearTextFields();
		displayMessage(true);
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

    public int getCategoryId() {
	return Integer.valueOf(categoryIdTxt.getText());
    }

    private Category getTextFieldValues() {
	Category c = new Category();
	try {
	    c.setCategoryId(Integer.valueOf(categoryIdTxt.getText()));
	    c.setCategoryName(categoryNameTxt.getText());

	} catch (Exception e) {
	    // TODO
	    e.printStackTrace();
	}
	return c;
    }

    private void setEditMode(boolean b) {
	this.editMode = b;
	enableTextFields(b);
	if (editMode) {
	    editBtn.setText("Update");
	} else {
	    editBtn.setText("Edit");
	}
    }

    private void showCancelBtn() {
	cancelBtn.setVisible(editMode);
    }

    private boolean isInEditMode() {
	return editMode;
    }

    private class CancelEditListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    setEditMode(false);
	    showCancelBtn();
	}

    }

    private class EditCategoryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (isInEditMode()) {
		Category c = getTextFieldValues();

		if (!category.equals(c)) {
		    try {
			UpdateCategoryCaller.perform(c);
			category = c;
			new MessageDialog("Update Successful",
				"Customer was updated successfully",
				JOptionPane.INFORMATION_MESSAGE);
		    } catch (Exception e) {
			new MessageDialog("Error", e.getMessage());
		    }
		} else {
		    new MessageDialog("Result", "No values were changed",
			    JOptionPane.INFORMATION_MESSAGE);
		}
		setEditMode(false); // once the sale is edited the panel
		// will go back to un-editable mode
		showCancelBtn(); // hide the cancel button
	    } else {
		setEditMode(true);
		showCancelBtn();
	    }
	}
    }
}
