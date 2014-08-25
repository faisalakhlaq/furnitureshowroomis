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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Product;
import database.CategoryHandler;
import database.ManufacturerHandler;
import database.ProductHandler;
import database.caller.UpdateProductCaller;

@SuppressWarnings("serial")
public class ProductPanel extends AbstractPanel {

    private JButton editBtn = null;
    private JButton cancelBtn = null;
    private JButton saveBtn = null;
    private JButton exitBtn = null;
    private JButton clearBtn = null;

    private JLabel productIdLbl = null;
    private JLabel pNameLbl = null;
    private JLabel discription1Lbl = null;
    private JLabel discriptionLbl = null;
    private JLabel manufacturerNameLbl = null;
    private JLabel categoryNameLbl = null;
    private JLabel warrantyLbl = null;

    private JTextField productIdTxt = null;
    private JTextField pNameTxt = null;
    private JTextField description1Txt = null;
    private JTextField description2Txt = null;
    private JComboBox<String> manufacturerNameCbx = null;
    private JComboBox<String> categoryNameCbx = null;
    private JTextField warrantyTxt = null;

    private JLabel resultMsgLbl;
    private boolean editMode = false;

    private Product product = null;

    public ProductPanel() {
	addPanels();
	enableTextFields(true);
	populateManufacturerNamesCbx();
	populateCategoryNamesCbx();
    }

    public ProductPanel(Product p) {
	product = p;
	addPanels();
	populateManufacturerNamesCbx();
	populateCategoryNamesCbx();
	fillTextFields();
	enableTextFields(false);
    }

    private void fillTextFields() {
	productIdTxt.setText(String.valueOf(product.getProductId()));
	pNameTxt.setText(product.getProductName());
	description1Txt.setText(product.getDescription1());
	description2Txt.setText(product.getDescription2());
	warrantyTxt.setText(String.valueOf(product.getWarranty()));
    }

    private void enableTextFields(boolean enable) {
	productIdTxt.setEnabled(false);
	pNameTxt.setEnabled(enable);
	description1Txt.setEnabled(enable);
	description2Txt.setEnabled(enable);
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	productIdLbl = new JLabel("Product Id");
	pNameLbl = new JLabel("Product Name");
	discription1Lbl = new JLabel("Description 1");
	discriptionLbl = new JLabel("Description 2");
	manufacturerNameLbl = new JLabel("Manufacturer Name");
	categoryNameLbl = new JLabel("Cetagory Name");
	warrantyLbl = new JLabel("Warranty");
	resultMsgLbl = new JLabel();

	productIdTxt = new JTextField(8);
	pNameTxt = new JTextField(20);
	description1Txt = new JTextField(50);
	description2Txt = new JTextField(50);
	manufacturerNameCbx = new JComboBox<String>();

	categoryNameCbx = new JComboBox<String>();
	warrantyTxt = new JTextField(2);

	centerPanel.setLayout(new GridBagLayout());

	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(productIdLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(productIdTxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(pNameLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(pNameTxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discription1Lbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(description1Txt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discriptionLbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(description2Txt, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(manufacturerNameLbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(manufacturerNameCbx, c);

	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(categoryNameLbl, c);

	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(categoryNameCbx, c);

	setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(warrantyLbl, c);

	setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(warrantyTxt, c);

	return centerPanel;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	editBtn = new JButton("Edit");
	editBtn.addActionListener(new EditProductListener());
	cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new CancelEditListener());
	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new SaveProductActionListener());
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

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Product"), BorderLayout.CENTER);
	return bannerPanel;

    }

    private void clearTextFields() {
	productIdTxt.setText(null);
	pNameTxt.setText(null);
	description1Txt.setText(null);
	description2Txt.setText(null);

	warrantyTxt.setText(null);

    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Product saved");
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

    private class SaveProductActionListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
	    try {
		Product m = new Product();

		// String pName = pNameTxt.getText();
		// if (Helper.isEmpty(pName)) {
		// throw new Exception("Please provide Product name");
		// }
		// m.setProductName(pName);
		// String description1 = description1Txt.getText();
		// m.setDescription1(description1);
		// String description2 = description2Txt.getText();
		// m.setDescription2(description2);
		//
		// String warrantyId = warrantyTxt.getText();
		// if (!Helper.isEmpty(warrantyId)
		// && Helper.isDigit(warrantyId)) {
		// m.setWarranty(Integer.valueOf(warrantyId));
		// } else if (!Helper.isEmpty(warrantyId)
		// && !Helper.isDigit(warrantyId)) {
		// throw new Exception(
		// "Product category id can only be digits");
		// }
		m = getTextFieldValues();
		ProductHandler producthandler = new ProductHandler();
		producthandler.saveProduct(m);
		clearTextFields();
		displayMessage(true);
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());

	    }
	}
    }

    public int getproductId() {
	return Integer.valueOf(productIdTxt.getText());
    }

    private void populateManufacturerNamesCbx() {
	if (manufacturerNameCbx == null) {
	    manufacturerNameCbx = new JComboBox<String>();
	}
	ManufacturerHandler handler = new ManufacturerHandler();
	Vector<String> names = null;
	try {
	    names = handler.getManufacturerNames();
	} catch (Exception e) {
	    new MessageDialog("Error", e.getMessage());
	}
	if (names == null) {
	    manufacturerNameCbx
		    .setModel(new javax.swing.DefaultComboBoxModel<String>());
	} else {
	    manufacturerNameCbx
		    .setModel(new javax.swing.DefaultComboBoxModel<String>(
			    names));
	}
    }

    private void populateCategoryNamesCbx() {
	if (categoryNameCbx == null) {
	    categoryNameCbx = new JComboBox<String>();
	}
	CategoryHandler handler = new CategoryHandler();
	Vector<String> categoryNames = null;
	try {
	    categoryNames = handler.getCategoryNames();
	} catch (Exception e) {
	    new MessageDialog("Error", e.getMessage());
	}
	if (categoryNames == null) {
	    categoryNameCbx
		    .setModel(new javax.swing.DefaultComboBoxModel<String>());
	} else {
	    categoryNameCbx
		    .setModel(new javax.swing.DefaultComboBoxModel<String>(
			    categoryNames));
	}
    }

    private Product getTextFieldValues() {
	Product m = new Product();
	try {
	    m.setProductId(Integer.valueOf(productIdTxt.getText()));
	    m.setProductName(pNameTxt.getText());
	    m.setDescription1(description1Txt.getText());
	    m.setDescription2(description2Txt.getText());
	    m.setManufacturerName(String.valueOf(manufacturerNameCbx
		    .getSelectedItem()));
	    m.setCategoryName(String.valueOf(categoryNameCbx.getSelectedItem()));
	    m.setWarranty(Integer.valueOf(warrantyTxt.getText()));
	} catch (Exception e) {
	    // TODO
	    e.printStackTrace();
	}
	return m;
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

    private class EditProductListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (isInEditMode()) {
		Product c = getTextFieldValues();

		if (!product.equals(c)) {
		    try {
			UpdateProductCaller.perform(c);
			product = c;
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
