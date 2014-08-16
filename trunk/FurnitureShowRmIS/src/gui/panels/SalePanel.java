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
import javax.swing.JTextField;

import model.Sale;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.ManufacturerHandler;
import database.SaleHandler;

@SuppressWarnings("serial")
public class SalePanel extends AbstractPanel {

    private JButton updateBtn = null;

    private JButton calculatePriceBtn = new JButton("Calculate Total Price");
    private JButton deleteBtn = null;

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

    private JTextField saleIdTxt = null;

    private JTextField itemNameTxt = null;

    private JTextField discription1Txt = null;

    private JTextField discription2Txt = null;

    private JTextField salePriceTxt = null;

    private JTextField purchasePriceTxt = null;

    private JComboBox<String> manufacturerNameCbx = null;

    private JTextField quantityTxt = null;

    private JTextField totalPriceTxt = null;
    private JTextField totalPurchasePriceTxt = null;
    private JXDatePicker datePicker = null;

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
	saleIdTxt.setText(String.valueOf(sale.getQuantity()));
	itemNameTxt.setText(sale.getItem());
	discription1Txt.setText(sale.getDescription1());
	discription2Txt.setText(sale.getDescription2());
	salePriceTxt.setText(String.valueOf(sale.getSalePrice()));
	purchasePriceTxt.setText(String.valueOf(sale.getPurchasePrice()));
	quantityTxt.setText(String.valueOf(sale.getQuantity()));
	totalPriceTxt.setText(String.valueOf(sale.getTotalprice()));
	totalPurchasePriceTxt.setText("0000"); // FIXME
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
	manufacturerNameCbx.setEnabled(enable);
	totalPriceTxt.setEnabled(false);
	totalPurchasePriceTxt.setEnabled(false);
	datePicker.setEnabled(enable);
    }

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
	buttonPanel.add(updateBtn);
	buttonPanel.add(deleteBtn);
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

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
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

		String tempVariable = itemNameTxt.getText();
		s.setItem(tempVariable);
		tempVariable = discription1Txt.getText();
		s.setDescription1(tempVariable);
		tempVariable = discription2Txt.getText();
		s.setDescription2(tempVariable);
		tempVariable = salePriceTxt.getText();
		if (!Helper.isEmpty(tempVariable)
			&& Helper.isDigit(tempVariable)) {
		    s.setSalePrice(Integer.valueOf(tempVariable));
		} else if (!Helper.isEmpty(tempVariable)
			&& !Helper.isDigit(tempVariable)) {
		    throw new Exception("Sale price can only be digits");
		}
		String purchasePrice = purchasePriceTxt.getText();
		if (!Helper.isEmpty(purchasePrice)
			&& Helper.isDigit(purchasePrice)) {
		    s.setPurchaseprice(Integer.valueOf(purchasePrice));
		} else if (!Helper.isEmpty(purchasePrice)
			&& !Helper.isDigit(purchasePrice)) {
		    throw new Exception("Purchase Price can only be digits");
		}
		
		String quantity = quantityTxt.getText();
		if (!Helper.isEmpty(quantity) && Helper.isDigit(quantity)) {
		    s.setQuantity(Integer.valueOf(quantity));
		} else if (!Helper.isEmpty(quantity)
			&& !Helper.isDigit(quantity)) {
		    throw new Exception("Quantity can only be digits");
		}

		s.setTotalprice(calculateTotalPrice());
		s.setTotalPurchasePrice(calculateTotalPurchasePrice());
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
	int q = quantity.isEmpty() ? 1 : Integer.valueOf(quantity);
	int p = price.isEmpty() ? 0 : Integer.valueOf(price);

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

	int q = quantity.isEmpty() ? 1 : Integer.valueOf(quantity);
	int pp = purchasePrice.isEmpty() ? 0 : Integer.valueOf(purchasePrice);

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
	}
   }

}

