package gui.panels;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.dailogue.MessageDialog;
import gui.panels.callers.ClosePanelCaller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Expenses;
import model.Payments;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.ExpensesHandler;

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
    }

    public MakePaymentsPanel(Payments p) {
	addPanels();
	this.payment = p;
	fillTextFields();
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

	descripitiontxt = new JTextField(15);
	totalBillTxt = new JTextField(15);
	paymentAmounttxt = new JTextField();
	balancetxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	return centerPanel;

    }

    @Override
    public GuiPanel getButtonPanel() {

	GuiPanel buttonPanel = null;
	return buttonPanel;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Expenses"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void clearTextFields() {
    }
}
