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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Sale;

import org.jdesktop.swingx.JXDatePicker;

import database.SaleHandler;

@SuppressWarnings("serial")
public class SalePanel extends AbstractPanel {

    private JButton updateBtn = null;

    private JButton deleteBtn = null;

    private JButton saveBtn = null;

    private JButton exitBtn = null;

    private JLabel itemlbl = null;

    private JLabel discriptionlbl = null;

    private JLabel discription1lbl = null;

    private JLabel salepricelbl = null;

    private JLabel datelbl = null;

    private JLabel purchasepricelbl = null;

    private JLabel mainufacturerlbl = null;

    private JLabel quantitylbl = null;

    private JLabel totalpricelbl = null;

    private JTextField itemtxt = null;

    private JTextField discriptiontxt = null;

    private JTextField discription1txt = null;

    private JTextField salepricetxt = null;

    private JTextField purchasepricetxt = null;

    private JTextField mainufacturertxt = null;

    private JTextField quantitytxt = null;

    private JTextField totalpricetxt = null;

    private JXDatePicker datePicker = null;

    private Sale sale = null;

    private JLabel resultMsgLbl;

    public SalePanel() {
	addPanels();
    }

    public SalePanel(Sale p) {
	sale = p;
	addPanels();
	fillTextFields();
    }

    private void fillTextFields() {
	itemtxt.setText(sale.getItem());
	discriptiontxt.setText(sale.getDescription1());
	discription1txt.setText(sale.getDescription2());
	salepricetxt.setText(String.valueOf(sale.getSalePrice()));
	purchasepricetxt.setText(String.valueOf(sale.getPurchaseprice()));
	mainufacturertxt.setText(sale.getManufacturer());
	quantitytxt.setText(String.valueOf(sale.getQuantity()));
	totalpricetxt.setText(String.valueOf(sale.getTotalprice()));
	datePicker.setDate(sale.getDate());
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
	saveBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		String iName = itemtxt.getText();
		String desc1 = discriptiontxt.getText();
		String desc2 = discription1txt.getText();
		double sPrice = Double.valueOf(salepricetxt.getText());
		double pPrice = Double.valueOf(purchasepricetxt.getText());
		java.util.Date date = datePicker.getDate();
		String manufact = mainufacturertxt.getText();
		String quantity = quantitytxt.getText();
		String tPrice = totalpricetxt.getText();

		SaleHandler salehandler = new SaleHandler();
		try {
		    salehandler.saveSales(iName, desc1, desc2, sPrice, pPrice,
			    date, manufact, quantity, tPrice);
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

    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	itemlbl = new JLabel("Item Name");
	discriptionlbl = new JLabel("Description 1");
	discription1lbl = new JLabel("Description 2");
	salepricelbl = new JLabel("Sale Price");
	datelbl = new JLabel("Date");
	purchasepricelbl = new JLabel("Purchase price");
	mainufacturerlbl = new JLabel("Mainufacturar");
	quantitylbl = new JLabel("Quantity");
	totalpricelbl = new JLabel("Total Price");
	resultMsgLbl = new JLabel();

	itemtxt = new JTextField(15);
	discriptiontxt = new JTextField(15);
	discription1txt = new JTextField(15);
	salepricetxt = new JTextField(15);
	purchasepricetxt = new JTextField(15);
	mainufacturertxt = new JTextField(15);
	quantitytxt = new JTextField(15);
	totalpricetxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	// c.gridx = 0;
	// c.gridy = 0;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(itemlbl, c);
	// c.gridx = 1;
	// c.gridy = 0;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(itemtxt, c);
	// c.gridx = 0;
	// c.gridy = 1;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discriptionlbl, c);

	// c.gridx = 1;
	// c.gridy = 1;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discriptiontxt, c);

	// c.gridx = 0;
	// c.gridy = 2;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discription1lbl, c);

	// c.gridx = 1;
	// c.gridy = 2;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discription1txt, c);

	// c.gridx = 0;
	// c.gridy = 3;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(salepricelbl, c);

	// c.gridx = 1;
	// c.gridy = 3;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(salepricetxt, c);

	// c.gridx = 0;
	// c.gridy = 4;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(datelbl, c);

	// c.gridx = 1;
	// c.gridy = 4;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(datePicker, c);
	// c.gridx = 0;
	// c.gridy = 5;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(purchasepricelbl, c);

	// c.gridx = 1;
	// c.gridy = 5;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(purchasepricetxt, c);

	// c.gridx = 0;
	// c.gridy = 6;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(mainufacturerlbl, c);

	// c.gridx = 1;
	// c.gridy = 6;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(mainufacturertxt, c);

	// c.gridx = 0;
	// c.gridy = 7;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(quantitylbl, c);

	// c.gridx = 1;
	// c.gridy = 7;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(quantitytxt, c);

	// c.gridx = 0;
	// c.gridy = 8;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(totalpricelbl, c);
	// c.gridx = 1;
	// c.gridy = 8;
	// c.gridwidth = 1;
	setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(totalpricetxt, c);

	// c.gridx = 1;
	// c.gridy = 9;
	// c.gridwidth = 1;
	centerPanel.add(resultMsgLbl, c);

	return centerPanel;
    }

    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Sale"), BorderLayout.CENTER);
	return bannerPanel;
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
}
