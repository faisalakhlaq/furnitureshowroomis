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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Sale;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.ManufacturerHandler;
import database.SaleHandler;
import database.caller.UpdateSaleCaller;

@SuppressWarnings("serial")
public class SalePanel extends AbstractPanel {

    private JButton editBtn = null;
    private JButton cancelBtn = null;
    private JButton calculatePriceBtn = new JButton("Calculate Total Price");
    private JButton saveBtn = null;
    private JButton exitBtn = null;
    private JButton clearBtn = null;

    private JLabel saleIdLbl = null;
    private JLabel itemLbl = null;
    private JLabel discription1Lbl = null;
    private JLabel discription2Lbl = null;
    private JLabel salePriceLbl = null;
    private JLabel dateLbl = null;
    private JLabel purchasePriceLbl = null;
    private JLabel manufacturerNameLbl = null;
    private JLabel quantityLbl = null;
    private JLabel totalPriceLbl = null;
    private JLabel totalPurchaseLbl = null;

    private JComboBox<String> manufacturerNameCbx = null;

    private JTextField saleIdTxt = null;
    private JTextField itemNameTxt = null;
    private JTextField discription1Txt = null;
    private JTextField discription2Txt = null;
    private JTextField salePriceTxt = null;
    private JTextField purchasePriceTxt = null;
    private JTextField quantityTxt = null;
    private JTextField totalPriceTxt = null;
    private JTextField totalPurchasePriceTxt = null;
    private JXDatePicker datePicker = null;

    private boolean editMode = false;
    private Sale sale = null;

    private JLabel resultMsgLbl;

    public SalePanel() {
	addPanels();
	populateManufacturerNameCbx();
	enableTextFields(true);
    }

    public SalePanel(Sale s) {
	addPanels();
	this.sale = s;
	fillTextFields();
	populateManufacturerNameCbx();
	enableTextFields(false);
    }

    private void fillTextFields() {
	if (sale == null)
	    return;
	saleIdTxt.setText(String.valueOf(sale.getSaleId()));
	itemNameTxt.setText(sale.getName());
	discription1Txt.setText(sale.getDescription1());
	discription2Txt.setText(sale.getDescription2());
	salePriceTxt.setText(String.valueOf(sale.getSalePrice()));
	purchasePriceTxt.setText(String.valueOf(sale.getPurchasePrice()));
	quantityTxt.setText(String.valueOf(sale.getQuantity()));
	totalPriceTxt.setText(String.valueOf(sale.getTotalprice()));
	totalPurchasePriceTxt.setText(String.valueOf(sale
		.getTotalPurchasePrice()));
	datePicker.setDate(sale.getDate());
    }

    private void enableTextFields(boolean enable) {
	saleIdTxt.setEnabled(false);
	itemNameTxt.setEnabled(enable);
	discription1Txt.setEnabled(enable);
	discription2Txt.setEnabled(enable);
	salePriceTxt.setEnabled(enable);
	purchasePriceTxt.setEnabled(enable);
	quantityTxt.setEnabled(enable);
	totalPriceTxt.setEnabled(false);
	totalPurchasePriceTxt.setEnabled(false);
	datePicker.setEnabled(enable);
    }

    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	editBtn = new JButton("Edit");
	editBtn.addActionListener(new EditSaleListener());
	cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new CancelEditListener());
	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new SaveSaleActionListener());
	clearBtn = new JButton("Clear");
	clearBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		clearTextFields();
	    }
	});
	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());

	calculatePriceBtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    calculateTotalPrice();
		    calculateTotalPurchasePrice();
		} catch (Exception e) {
		    new MessageDialog("Error", e.getMessage());
		    e.printStackTrace();
		}
	    }
	});
	buttonPanel.add(cancelBtn);
	buttonPanel.add(editBtn);
	buttonPanel.add(saveBtn);
	buttonPanel.add(clearBtn);
	buttonPanel.add(exitBtn);

	return buttonPanel;
    }

    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	saleIdLbl = new JLabel("Sale Id");
	itemLbl = new JLabel("Item Name");
	discription1Lbl = new JLabel("Description 1");
	discription2Lbl = new JLabel("Description 2");
	salePriceLbl = new JLabel("Sale Price");
	dateLbl = new JLabel("Date");
	purchasePriceLbl = new JLabel("Purchase price");
	manufacturerNameLbl = new JLabel("Manufacturar Name");
	quantityLbl = new JLabel("Quantity");
	totalPriceLbl = new JLabel("Total Price");
	totalPurchaseLbl = new JLabel("Total Purchase Price");
	resultMsgLbl = new JLabel();

	saleIdTxt = new JTextField(15);
	itemNameTxt = new JTextField(15);
	discription1Txt = new JTextField(15);
	discription2Txt = new JTextField(15);
	salePriceTxt = new JTextField(15);
	purchasePriceTxt = new JTextField(15);
	manufacturerNameCbx = new JComboBox<String>();
	quantityTxt = new JTextField(15);
	totalPriceTxt = new JTextField(15);
	totalPurchasePriceTxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(saleIdLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(saleIdTxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(itemLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(itemNameTxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discription1Lbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discription1Txt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discription2Lbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discription2Txt, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(salePriceLbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(salePriceTxt, c);

	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(dateLbl, c);

	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(datePicker, c);

	setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(purchasePriceLbl, c);

	setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(purchasePriceTxt, c);

	setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(manufacturerNameLbl, c);

	setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(manufacturerNameCbx, c);

	setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(quantityLbl, c);

	setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(quantityTxt, c);

	setGridBagConstraints(c, 0, 9, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(totalPriceLbl, c);

	setGridBagConstraints(c, 1, 9, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(totalPriceTxt, c);

	setGridBagConstraints(c, 2, 9, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(calculatePriceBtn, c);

	setGridBagConstraints(c, 0, 10, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(totalPurchaseLbl, c);

	setGridBagConstraints(c, 1, 10, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(totalPurchasePriceTxt, c);

	c.gridwidth = 2;
	setGridBagConstraints(c, 0, 11, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(resultMsgLbl, c);

	return centerPanel;
    }

    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Sale"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void clearTextFields() {
	saleIdTxt.setText(null);
	itemNameTxt.setText(null);
	discription1Txt.setText(null);
	discription2Txt.setText(null);
	salePriceTxt.setText(null);
	purchasePriceTxt.setText(null);
	quantityTxt.setText(null);
	totalPriceTxt.setText(null);
    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Sale saved");
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

    private class SaveSaleActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    try {
		Sale s = new Sale();

		// String tempVariable = itemNameTxt.getText();
		// s.setName(tempVariable);
		// tempVariable = discription1Txt.getText();
		// s.setDescription1(tempVariable);
		// tempVariable = discription2Txt.getText();
		// s.setDescription2(tempVariable);
		// tempVariable = salePriceTxt.getText();
		// if (!Helper.isEmpty(tempVariable)
		// && Helper.isDigit(tempVariable)) {
		// s.setSalePrice(Integer.valueOf(tempVariable));
		// } else if (!Helper.isEmpty(tempVariable)
		// && !Helper.isDigit(tempVariable)) {
		// throw new Exception("Sale price can only be digits");
		// }
		// String purchasePrice = purchasePriceTxt.getText();
		// if (!Helper.isEmpty(purchasePrice)
		// && Helper.isDigit(purchasePrice)) {
		// s.setPurchaseprice(Integer.valueOf(purchasePrice));
		// } else if (!Helper.isEmpty(purchasePrice)
		// && !Helper.isDigit(purchasePrice)) {
		// throw new Exception("Purchase Price can only be digits");
		// }
		//
		// String quantity = quantityTxt.getText();
		// if (!Helper.isEmpty(quantity) && Helper.isDigit(quantity)) {
		// s.setQuantity(Integer.valueOf(quantity));
		// } else if (!Helper.isEmpty(quantity)
		// && !Helper.isDigit(quantity)) {
		// throw new Exception("Quantity can only be digits");
		// }
		//
		// s.setTotalPrice(calculateTotalPrice());
		// s.setTotalPurchasePrice(calculateTotalPurchasePrice());
		s = getTextFieldValues();
		SaleHandler salehandler = new SaleHandler();
		salehandler.saveSale(s);
		clearTextFields();
		displayMessage(true);
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
		displayMessage(false);
	    }
	}
    }

    private double calculateTotalPrice() throws Exception {
	double totalPrice = 0;
	String price = salePriceTxt.getText();
	String quantity = quantityTxt.getText();

	if (!Helper.isEmpty(quantity) && !Helper.isDigit(quantity)) {
	    throw new Exception("Quantity can only be digits");
	}

	if (!Helper.isEmpty(price) && !Helper.isDigit(price)) {
	    throw new Exception("Sale price can only be digits");
	}
	double q = quantity.isEmpty() ? 1 : Double.valueOf(quantity);
	double p = price.isEmpty() ? 0 : Double.valueOf(price);

	totalPrice = q * p;
	totalPriceTxt.setText(String.valueOf(totalPrice));

	return totalPrice;
    }

    private double calculateTotalPurchasePrice() throws Exception {
	double totalPrice = 0;
	String quantity = quantityTxt.getText();
	String purchasePrice = purchasePriceTxt.getText();

	if (!Helper.isEmpty(purchasePrice) && !Helper.isDigit(purchasePrice)) {
	    throw new Exception("Purchase Price can only be digits");
	}

	if (!Helper.isEmpty(quantity) && !Helper.isDigit(quantity)) {
	    throw new Exception("Quantity can only be digits");
	}

	double q = quantity.isEmpty() ? 1 : Double.valueOf(quantity);
	double pp = purchasePrice.isEmpty() ? 0 : Double.valueOf(purchasePrice);

	totalPrice = pp * q;
	totalPurchasePriceTxt.setText("" + totalPrice);

	return totalPrice;

    }

    public void populateManufacturerNameCbx() {
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
	    if (sale != null) {
		manufacturerNameCbx.setSelectedItem(sale.getManufacturerName());
	    }
	}
    }

    private Sale getTextFieldValues() {
	Sale s = new Sale();
	try {
	    s.setSaleId(Integer.valueOf(saleIdTxt.getText()));
	    s.setName(itemNameTxt.getText());
	    s.setDescription1(discription1Txt.getText());
	    s.setDescription2(discription2Txt.getText());
	    String strPrice = salePriceTxt.getText();
	    double price = 0;
	    if (!Helper.isEmpty(strPrice) && Helper.isDigit(strPrice)) {
		price = Double.valueOf(strPrice);
	    }
	    s.setSalePrice(price);
	    String strPP = purchasePriceTxt.getText();
	    double pp = 0;
	    if (!Helper.isEmpty(strPP) && Helper.isDigit(strPP)) {
		pp = Double.valueOf(strPP);
	    }
	    s.setPurchaseprice(pp);
	    String qty = quantityTxt.getText();
	    int quantity = 1;
	    if (!Helper.isEmpty(qty) && Helper.isDigit(qty)) {
		quantity = Integer.valueOf(qty);
	    }
	    s.setQuantity(quantity);

	    s.setTotalPrice(calculateTotalPrice());
	    s.setTotalPurchasePrice(calculateTotalPurchasePrice());

	    s.setDate(datePicker.getDate());
	    s.setManufacturerName(String.valueOf(manufacturerNameCbx
		    .getSelectedItem()));
	} catch (Exception e) {
	    // TODO
	    e.printStackTrace();
	}
	return s;
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

    private class EditSaleListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (isInEditMode()) {
		Sale c = getTextFieldValues();

		// if the current values are equal to the sale object then
		// do not update
		if (!sale.equals(c)) {
		    try {
			UpdateSaleCaller.perform(c);
			sale = c;
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