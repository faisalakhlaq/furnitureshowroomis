package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Payments;

import org.jdesktop.swingx.JXDatePicker;

import database.ManufacturerHandler;
import database.PaymentsHandler;

@SuppressWarnings("serial")
public class MakePaymentsPanel extends AbstractPanel {
    private JButton savebtn = null;

    private JButton exitbtn = null;

    private JButton calculatebtn = null;

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

    public MakePaymentsPanel() {
	addPanels();
	populateManufacturerNameCbx();
    }

    public MakePaymentsPanel(Payments p) {
	addPanels();
	this.payment = p;
	fillTextFields();
	populateManufacturerNameCbx();
    }

    private void fillTextFields() {
	if (payment == null)
	    return;

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
	calculatebtn = new JButton("Calculate");

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
	exitbtn = new JButton("Exit");
	exitbtn.addActionListener(new ClosePanelCaller());

	buttonPanel.add(savebtn);

	buttonPanel.add(exitbtn);

	return buttonPanel;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Make Payments"), BorderLayout.CENTER);
	return bannerPanel;
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

}
