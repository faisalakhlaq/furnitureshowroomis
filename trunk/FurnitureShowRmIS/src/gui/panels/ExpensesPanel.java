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

import model.Expenses;

import org.jdesktop.swingx.JXDatePicker;

import utils.Helper;
import database.ExpensesHandler;

@SuppressWarnings("serial")
public class ExpensesPanel extends AbstractPanel {

    private JButton saveBtn = null;

    private JButton deleteBtn = null;

    private JButton exitBtn = null;

    private JLabel titleLbl = null;

    private JLabel amountLbl = null;

    private JLabel discriptionLbl = null;

    private JLabel dateLbl = null;

    private JTextField titleTxt = null;

    private JTextField amountTxt = null;

    private JTextField idTxt = null;

    private JTextField discriptionTxt = null;

    private JXDatePicker datePicker = null;

    private JLabel resultMsgLbl;

    private Expenses expence = null;

    public ExpensesPanel() {
	addPanels();
	enableTextFields(true);
    }

    public ExpensesPanel(Expenses e) {
	addPanels();
	this.expence = e;
	fillTextFields();
	enableTextFields(false);
    }

    private void fillTextFields() {
	if (expence == null)
	    return;

	titleTxt.setText(expence.getTitle());

	amountTxt.setText(String.valueOf(expence.getAmount()));

	idTxt.setText(String.valueOf(expence.getId()));

	discriptionTxt.setText(expence.getDescription());

	// private JTextField datetxt = null;

	datePicker.setDate(expence.getDate());
    }
    
    private void enableTextFields(boolean enable) {
	idTxt.setEnabled(false);
	titleTxt.setEnabled(enable);
	amountTxt.setEnabled(enable);
	discriptionTxt.setEnabled(enable);
   	datePicker.setEnabled(enable);
       }

    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    Expenses e = new Expenses();

		    String title = titleTxt.getText();
		    if (Helper.isEmpty(title)) {
			throw new Exception("Please provide Expenses name");
		    }
		    e.setTitle(title);
		    String description = discriptionTxt.getText();
		    e.setDescription(description);
		    String amount = amountTxt.getText();
		    if (!Helper.isEmpty(amount) && Helper.isDigit(amount)) {
			e.setAmount(Integer.valueOf(amount));
		    } else if (!Helper.isEmpty(amount)
			    && !Helper.isDigit(amount)) {
			throw new Exception("Expenses amount id can be digits");
		    }

		    ExpensesHandler expensehandler = new ExpensesHandler();
		    expensehandler.saveExpenses(e);
		    clearTextFields();
		    displayMessage(true);
		} catch (Exception e) {
		    new MessageDialog("Error", e.getMessage());

		}
	    }
	});
	

	deleteBtn = new JButton("Delete");

	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());

	buttonPanel.add(saveBtn);
	buttonPanel.add(deleteBtn);
	buttonPanel.add(exitBtn);

	return buttonPanel;
    }

    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	titleLbl = new JLabel("Title");
	amountLbl = new JLabel("Amount");
	discriptionLbl = new JLabel("Description");
	dateLbl = new JLabel("Date");
	resultMsgLbl = new JLabel();
	JLabel idLbl = new JLabel("Expense ID");

	titleTxt = new JTextField(15);
	amountTxt = new JTextField(15);
	idTxt = new JTextField();
	discriptionTxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(titleLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(titleTxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(amountLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(amountTxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discriptionLbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discriptionTxt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(dateLbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(datePicker, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(idLbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(idTxt, c);

	c.gridx = 0;
	c.gridy = 5;
	c.gridwidth = 2;
	centerPanel.add(resultMsgLbl, c);

	return centerPanel;
    }

    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Expenses"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void clearTextFields() {
	titleTxt.setText(null);
	discriptionTxt.setText(null);
	idTxt.setText(null);
	amountTxt.setText(null);

    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Expenses saved");
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

    public int getExpesesId() {
	return Integer.valueOf(idTxt.getText());
    }
}
