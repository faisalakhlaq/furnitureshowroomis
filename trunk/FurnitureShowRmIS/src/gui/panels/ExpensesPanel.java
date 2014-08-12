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

    private JButton savebtn = null;

    private JButton deleteBtn = null;

    private JButton exitbtn = null;

    private JLabel titlelbl = null;

    private JLabel amountlbl = null;

    private JLabel discriptionlbl = null;

    private JLabel datelbl = null;

    private JTextField titletxt = null;

    private JTextField amounttxt = null;

    private JTextField idTxt = null;

    private JTextField discriptiontxt = null;

    private JXDatePicker datePicker = null;

    private JLabel resultMsgLbl;

    private Expenses expence = null;

    public ExpensesPanel() {
	addPanels();
    }

    public ExpensesPanel(Expenses e) {
	addPanels();
	this.expence = e;
	fillTextFields();
    }

    private void fillTextFields() {
	if (expence == null)
	    return;

	titletxt.setText(expence.getTitle());

	amounttxt.setText(String.valueOf(expence.getAmount()));

	idTxt.setText(String.valueOf(expence.getId()));

	discriptiontxt.setText(expence.getDescription());

	// private JTextField datetxt = null;

	datePicker.setDate(expence.getDate());
    }

    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	savebtn = new JButton("Save");
	savebtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		try {
		    Expenses e = new Expenses();

		    String title = titletxt.getText();
		    if (Helper.isEmpty(title)) {
			throw new Exception("Please provide Expenses name");
		    }
		    e.setTitle(title);
		    String description = discriptiontxt.getText();
		    e.setDescription(description);
		    String amount = amounttxt.getText();
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

	exitbtn = new JButton("Exit");
	exitbtn.addActionListener(new ClosePanelCaller());

	buttonPanel.add(savebtn);
	buttonPanel.add(deleteBtn);
	buttonPanel.add(exitbtn);

	return buttonPanel;
    }

    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	titlelbl = new JLabel("Title");
	amountlbl = new JLabel("Amount");
	discriptionlbl = new JLabel("Description");
	datelbl = new JLabel("Date");
	resultMsgLbl = new JLabel();
	JLabel idLbl = new JLabel("Expense ID");

	titletxt = new JTextField(15);
	amounttxt = new JTextField(15);
	idTxt = new JTextField();
	discriptiontxt = new JTextField(15);
	datePicker = new JXDatePicker();

	datePicker.setDate(Calendar.getInstance().getTime());
	datePicker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(titlelbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(titletxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(amountlbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(amounttxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(discriptionlbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(discriptiontxt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(datelbl, c);

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
	titletxt.setText(null);
	discriptiontxt.setText(null);
	idTxt.setText(null);
	amounttxt.setText(null);

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
