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

import model.Payments;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.ManufacturerHandler;
import database.PaymentsHandler;

@SuppressWarnings("serial")
public class PaymentsPanel extends AbstractPanel {
    private JButton savebtn = null;

    private JButton exitbtn = null;

    private JButton calculatebtn = new JButton("Calculate");

    private JLabel manufacturerNamelbl = null;

    private JLabel descripitionlbl = null;

    private JLabel totalBilllbl = null;

    private JLabel paymentAmountlbl = null;

    private JLabel balancelbl = null;

    private JLabel paymentDatelbl = null;

    private JComboBox<String> manufacturerNameCbx = null;

    private JTextField paymentAmounttxt = null;

    private JTextField descripitiontxt = null;

    private JTextField totalBillTxt = null;

    private JTextField discriptiontxt = null;

    private JTextField balancetxt = null;

    private JXDatePicker datePicker = null;

    private Payments payment = null;

    private JLabel resultMsgLbl = null;

    public PaymentsPanel() {
	addPanels();
	populateManufacturerNameCbx();
    }

    public PaymentsPanel(Payments p) {
	addPanels();
	this.payment = p;
	fillTextFields();
	populateManufacturerNameCbx();
    }

    private void fillTextFields() {
	if (payment == null)
	    return;

	manufacturerNameCbx.getSelectedItem().toString();

	paymentAmounttxt.setText(String.valueOf(payment.getPaymentAmount()));

	descripitiontxt.setText(String.valueOf(payment.getDescription()));

	totalBillTxt.setText(String.valueOf(payment.getTotalBill()));

	balancetxt.setText(String.valueOf(payment.getBalance()));

	discriptiontxt.setText(payment.getDescription());

	// private JTextField datetxt = null;

	datePicker.setDate(payment.getDate());
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	manufacturerNamelbl = new JLabel("Manufacturer Name");
	descripitionlbl = new JLabel("Description");
	totalBilllbl = new JLabel("Total Bill");
	paymentAmountlbl = new JLabel("Payment Amount");
	balancelbl = new JLabel("Balance");
	paymentDatelbl = new JLabel("Payment Date");

	manufacturerNameCbx = new JComboBox<String>();
	descripitiontxt = new JTextField(15);
	totalBillTxt = new JTextField(15);
	paymentAmounttxt = new JTextField();
	balancetxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(manufacturerNamelbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(manufacturerNameCbx, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(descripitionlbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(descripitiontxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(totalBilllbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(totalBillTxt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(paymentAmountlbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(paymentAmounttxt, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(balancelbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(balancetxt, c);

	setGridBagConstraints(c, 2, 4, GridBagConstraints.LINE_START, 5, 10);
	centerPanel.add(calculatebtn, c);

	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(paymentDatelbl, c);

	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(datePicker, c);

	return centerPanel;

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

    @Override
    public GuiPanel getButtonPanel() {

	GuiPanel buttonPanel = new GuiPanel();

	savebtn = new JButton("Save");
	savebtn.addActionListener(new SavePaymentsActionListener());
	exitbtn = new JButton("Exit");
	exitbtn.addActionListener(new ClosePanelCaller());

	calculatebtn.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    calculateBalance();

		} catch (Exception e) {
		    new MessageDialog("Error", e.getMessage());
		    e.printStackTrace();
		}
	    }
	});

	buttonPanel.add(savebtn);

	savebtn = new JButton("Save");

	buttonPanel.add(exitbtn);

	return buttonPanel;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Make Payments"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Payments saved");
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

    private void clearTextFields() {
	descripitiontxt.setText(null);
	totalBillTxt.setText(null);
	paymentAmounttxt.setText(null);
	balancetxt.setText(null);

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

    private double calculateBalance() throws Exception {
	double balance = 0;
	String totalBill = totalBillTxt.getText();
	String paymentAmount = paymentAmounttxt.getText();

	if (!Helper.isEmpty(paymentAmount) && !Helper.isDigit(paymentAmount)) {
	    throw new Exception("Quantity can only be digits");
	}

	if (!Helper.isEmpty(totalBill) && !Helper.isDigit(totalBill)) {
	    throw new Exception("Sale price can only be digits");
	}
	int q = paymentAmount.isEmpty() ? 1 : Integer.valueOf(paymentAmount);
	int p = totalBill.isEmpty() ? 0 : Integer.valueOf(totalBill);

	balance = p - q;
	balancetxt.setText(String.valueOf(balance));

	return balance;
    }

    private class SavePaymentsActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    try {
		Payments p = new Payments();

		String mName = manufacturerNameCbx.getSelectedItem().toString();
		p.setManufacturerName(mName);
		String paymentAmount = paymentAmounttxt.getText();
		if (!Helper.isEmpty(paymentAmount)
			&& Helper.isDigit(paymentAmount)) {
		    p.setPaymentAmount(Integer.valueOf(paymentAmount));
		} else if (!Helper.isEmpty(paymentAmount)
			&& !Helper.isDigit(paymentAmount)) {
		    throw new Exception(
			    "Payment Amount Price can only be digits");
		}
		String description = descripitiontxt.getText();
		p.setDescription(description);
		String totalBill = totalBillTxt.getText();
		if (!Helper.isEmpty(totalBill) && Helper.isDigit(totalBill)) {
		    p.setTotalBill(Integer.valueOf(totalBill));
		} else if (!Helper.isEmpty(totalBill)
			&& !Helper.isDigit(totalBill)) {
		    throw new Exception("Total Bill can only be digits");
		}
		// String balance = balancetxt.getText();
		// if (!Helper.isEmpty(balance)
		// && Helper.isDigit(balance)) {
		// p.setBalance(Integer.valueOf(balance));
		// } else if (!Helper.isEmpty(balance)
		// && !Helper.isDigit(balance)) {
		// throw new Exception("Balance can only be digits");
		// }

		java.util.Date date = datePicker.getDate();

		p.setBalance(calculateBalance());
		p.setDate(datePicker.getDate());

		PaymentsHandler paymentsHandler = new PaymentsHandler();
		paymentsHandler.savePayments(p);
		clearTextFields();
		displayMessage(true);
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());

	    }
	}
    }

}