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
import javax.swing.JTextField;

import model.Product;
import utils.Helper;
import database.ProductHandler;

@SuppressWarnings("serial")
public class ProductPanel extends AbstractPanel {

    private JButton updateBtn = null;

    private JButton deleteBtn = null;

    private JButton saveBtn = null;

    private JButton exitBtn = null;

    private JLabel productIdLbl = null;

    private JLabel pNameLbl = null;

    private JLabel discription1Lbl = null;

    private JLabel discriptionLbl = null;

    private JLabel manufacturerIdLbl = null;

    private JLabel categoryIdLbl = null;

    private JLabel warrantyIdLbl = null;

    private JTextField productIdTxt = null;

    private JTextField pNameTxt = null;

    private JTextField description1Txt = null;

    private JTextField description2Txt = null;

    private JTextField manufacturerIdTxt = null;

    private JTextField categoryIdTxt = null;

    private JTextField warrantyIdTxt = null;

    private JLabel resultMsgLbl;

    private Product product = null;

    public ProductPanel() {
	addPanels();
    }

    public ProductPanel(Product p) {
	product = p;
	addPanels();
	fillTextFields();
    }

    private void fillTextFields() {
	productIdTxt.setText(String.valueOf(product.getProductId()));
	pNameTxt.setText(product.getpName());
	description1Txt.setText(product.getDescription1());
	description2Txt.setText(product.getDescription2());
	manufacturerIdTxt.setText(String.valueOf(product.getManufacturerId()));
	categoryIdTxt.setText(String.valueOf(product.getCategoryId()));
	warrantyIdTxt.setText(String.valueOf(product.getWarrantyId()));
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	productIdLbl = new JLabel("Product Id");
	pNameLbl = new JLabel("P Name");
	discription1Lbl = new JLabel("Description 1");
	discriptionLbl = new JLabel("Description ");
	manufacturerIdLbl = new JLabel("Manufacturer Id");
	categoryIdLbl = new JLabel("Cetagory Id");
	warrantyIdLbl = new JLabel("Warranty Id");
	resultMsgLbl = new JLabel();

	productIdTxt = new JTextField(8);
	pNameTxt = new JTextField(20);
	description1Txt = new JTextField(50);
	description2Txt = new JTextField(50);
	manufacturerIdTxt = new JTextField(8);
	categoryIdTxt = new JTextField(8);
	warrantyIdTxt = new JTextField(2);

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	// c.gridx = 0;
	// c.gridy = 0;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(productIdLbl, c);

	// c.gridx = 1;
	// c.gridy = 0;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(productIdTxt, c);

	// c.gridx = 0;
	// c.gridy = 1;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(pNameLbl, c);

	// c.gridx = 1;
	// c.gridy = 1;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(pNameTxt, c);

	// c.gridx = 0;
	// c.gridy = 2;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discription1Lbl, c);
	// c.gridx = 1;
	// c.gridy = 2;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(description1Txt, c);
	// c.gridx = 0;
	// c.gridy = 3;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discriptionLbl, c);
	// c.gridx = 1;
	// c.gridy = 3;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(description2Txt, c);
	// c.gridx = 0;
	// c.gridy = 4;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(manufacturerIdLbl, c);
	// c.gridx = 1;
	// c.gridy = 4;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(manufacturerIdTxt, c);

	// c.gridx = 0;
	// c.gridy = 5;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(categoryIdLbl, c);

	// c.gridx = 1;
	// c.gridy = 5;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(categoryIdTxt, c);

	// c.gridx = 0;
	// c.gridy = 6;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(warrantyIdLbl, c);

	// c.gridx = 1;
	// c.gridy = 6;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(warrantyIdTxt, c);

	return centerPanel;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

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
		    Product m = new Product();

		    String pName = pNameTxt.getText();
		    if (Helper.isEmpty(pName)) {
			throw new Exception("Please provide Product name");
		    }
		    m.setpName(pName);
		    String description1 = description1Txt.getText();
		    m.setDescription1(description1);
		    String description2 = description2Txt.getText();
		    m.setDescription2(description2);
		    String manufacturerId = manufacturerIdTxt.getText();
		    if (!Helper.isEmpty(manufacturerId)
			    && !Helper.isDigit(manufacturerId)) {
			throw new Exception(
				"Manufacturer ID can only be integer");
		    } else if (!Helper.isEmpty(manufacturerId)
			    && Helper.isDigit(manufacturerId)) {
			m.setManufacturerId(Integer.valueOf(manufacturerId));
		    }
		    String categoryId = categoryIdTxt.getText();
		    if (!Helper.isEmpty(categoryId)
			    && Helper.isDigit(categoryId)) {
			m.setCategoryId(Integer.valueOf(categoryId));
		    } else if (!Helper.isEmpty(categoryId)
			    && !Helper.isDigit(categoryId)) {
			throw new Exception(
				"Product category id can only be digits");
		    }
		    String warrantyId = warrantyIdTxt.getText();
		    if (!Helper.isEmpty(warrantyId)
			    && Helper.isDigit(warrantyId)) {
			m.setWarrantyId(Integer.valueOf(warrantyId));
		    } else if (!Helper.isEmpty(warrantyId)
			    && !Helper.isDigit(warrantyId)) {
			throw new Exception(
				"Product category id can only be digits");
		    }
		    ProductHandler producthandler = new ProductHandler();
		    producthandler.saveProduct(m);
		    clearTextFields();
		    displayMessage(true);
		} catch (Exception e) {
		    new MessageDialog("Error", e.getMessage());

		}
	    }
	});
	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());
	buttonPanel.add(updateBtn);
	buttonPanel.add(deleteBtn);
	buttonPanel.add(saveBtn);
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
	manufacturerIdTxt.setText(null);
	categoryIdTxt.setText(null);
	warrantyIdTxt.setText(null);

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

    public int getproductId() {
	return Integer.valueOf(productIdTxt.getText());
    }
}
