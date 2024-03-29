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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Manufacturer;
import database.ManufacturerHandler;
import database.caller.UpdateManufacturerCaller;

@SuppressWarnings("serial")
public class ManufacturerPanel extends AbstractPanel {
    private JButton editBtn = null;
    private JButton cancelBtn = null;
    private JButton saveBtn = null;
    private JButton exitBtn = null;
    private JButton clearBtn = null;

    private JLabel mIdLbl = null;
    private JLabel mNameLbl = null;
    private JLabel contactPerson1Lbl = null;
    private JLabel contactPerson2Lbl = null;
    private JLabel tNumberLbl = null;
    private JLabel cellnumberLbl = null;
    private JLabel addressLbl = null;
    private JLabel webLbl = null;
    private JLabel accountNumberLbl = null;

    private JTextField mIdTxt = null;
    private JTextField mNameTxt = null;
    private JTextField contactPerson1Txt = null;
    private JTextField contactPerson2Txt = null;
    private JTextField tNumberTxt = null;
    private JTextField cellNumberTxt = null;
    private JTextField addressTxt = null;
    private JTextField webTxt = null;
    private JTextField accountNumberTxt = null;

    private JLabel resultMsgLbl;

    private boolean editMode = false;
    private Manufacturer manufacturer = null;

    public ManufacturerPanel() {
	addPanels();

    }

    public ManufacturerPanel(Manufacturer m) {
	addPanels();
	this.manufacturer = m;
	fillTextFields();
    }

    private void fillTextFields() {
	if (manufacturer == null)
	    return;

	mIdTxt.setText(String.valueOf(manufacturer.getmId()));

	mNameTxt.setText(manufacturer.getmName());

	contactPerson1Txt.setText(manufacturer.getContactPerson1());

	contactPerson2Txt.setText(manufacturer.getContactPerson2());

	tNumberTxt.setText(String.valueOf(manufacturer.gettNumber()));

	cellNumberTxt.setText(String.valueOf(manufacturer.getCellNumber()));

	addressTxt.setText(manufacturer.getAddress());

	webTxt.setText(manufacturer.getWeb());

	accountNumberTxt.setText(manufacturer.getAccountNumber());

	// private JTextField datetxt = null;

    }

    private void enableTextFields(boolean enable) {
	mIdTxt.setEnabled(false);
	mNameTxt.setEnabled(enable);
	contactPerson1Txt.setEnabled(enable);
	contactPerson2Txt.setEnabled(enable);
	tNumberTxt.setEnabled(enable);
	cellNumberTxt.setEnabled(enable);
	addressTxt.setEnabled(enable);
	webTxt.setEnabled(enable);
	accountNumberTxt.setEnabled(enable);
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	mIdLbl = new JLabel("Manufacturer Id");
	mNameLbl = new JLabel("Manfacturer Name");
	contactPerson1Lbl = new JLabel("Contact Person1");
	contactPerson2Lbl = new JLabel("Contact Person2");
	tNumberLbl = new JLabel("T Number");
	cellnumberLbl = new JLabel("Cell Number");
	addressLbl = new JLabel("Address");
	webLbl = new JLabel("Web");
	accountNumberLbl = new JLabel("Account Number");
	resultMsgLbl = new JLabel();

	mIdTxt = new JTextField(15);
	mIdTxt.setEnabled(false);
	mNameTxt = new JTextField(20);
	contactPerson1Txt = new JTextField(20);
	contactPerson2Txt = new JTextField(20);
	tNumberTxt = new JTextField(20);
	cellNumberTxt = new JTextField(15);
	addressTxt = new JTextField(20);
	webTxt = new JTextField(15);
	accountNumberTxt = new JTextField(20);

	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(mIdLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(mIdTxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(mNameLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(mNameTxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(contactPerson1Lbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(contactPerson1Txt, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(contactPerson2Lbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(contactPerson2Txt, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(tNumberLbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(tNumberTxt, c);

	setGridBagConstraints(c, 0, 5, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(cellnumberLbl, c);

	setGridBagConstraints(c, 1, 5, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(cellNumberTxt, c);

	setGridBagConstraints(c, 0, 6, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(addressLbl, c);

	setGridBagConstraints(c, 1, 6, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(addressTxt, c);

	setGridBagConstraints(c, 0, 7, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(webLbl, c);

	setGridBagConstraints(c, 1, 7, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(webTxt, c);

	setGridBagConstraints(c, 0, 8, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(accountNumberLbl, c);

	setGridBagConstraints(c, 1, 8, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(accountNumberTxt, c);

	c.gridwidth = 2;
	setGridBagConstraints(c, 1, 9, GridBagConstraints.LINE_START, 5, 10);
	centerPanel.add(resultMsgLbl, c);

	return centerPanel;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel buttonPanel = new GuiPanel();

	editBtn = new JButton("Edit");
	editBtn.addActionListener(new EditManfacturerListener());
	cancelBtn = new JButton("Cancel");
	cancelBtn.addActionListener(new CancelEditListener());
	saveBtn = new JButton("Save");
	saveBtn.addActionListener(new SaveManufacturerActionListener());
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
	bannerPanel.add(new JLabel("Manufacturer"), BorderLayout.CENTER);
	return bannerPanel;
    }

    private void clearTextFields() {
	mIdTxt.setText(null);
	contactPerson1Txt.setText(null);
	contactPerson2Txt.setText(null);
	tNumberTxt.setText(null);
	cellNumberTxt.setText(null);
	addressTxt.setText(null);
	webTxt.setText(null);
	accountNumberTxt.setText(null);
    }

    private void displayMessage(final boolean success) {
	Thread t = new Thread() {
	    public void run() {
		try {
		    if (success) {
			resultMsgLbl.setText("Manufacturer saved");
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

    private class SaveManufacturerActionListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
	    try {
		Manufacturer m = new Manufacturer();

		// String contactPerson1 = contactPerson1Txt.getText();
		// m.setContactPerson1(contactPerson1);
		// String contactPerson2 = contactPerson2Txt.getText();
		// m.setContactPerson2(contactPerson2);
		// String tNumber = tNumberTxt.getText();
		// if (!Helper.isEmpty(tNumber) && Helper.isDigit(tNumber)) {
		// m.settNumber(Integer.valueOf(tNumber));
		// } else if (!Helper.isEmpty(tNumber)
		// && !Helper.isDigit(tNumber)) {
		// throw new Exception(
		// "Manufacturer Telephone number can only be digits");
		// }
		// String cellNumber = cellNumberTxt.getText();
		// if (!Helper.isEmpty(cellNumber)
		// && Helper.isDigit(cellNumber)) {
		// m.setCellNumber(Integer.valueOf(cellNumber));
		// } else if (!Helper.isEmpty(cellNumber)
		// && !Helper.isDigit(cellNumber)) {
		// throw new Exception(
		// "Manufacturer Telephone number can only be digits");
		// }
		// String address = addressTxt.getText();
		// m.setAddress(address);
		// String web = webTxt.getText();
		// m.setWeb(web);
		// String accountNumber = accountNumberTxt.getText();
		// m.setAccountNumber(accountNumber);
		m = getTextFieldValues();
		ManufacturerHandler manufacturerhandler = new ManufacturerHandler();
		manufacturerhandler.saveManufacturer(m);
		clearTextFields();
		displayMessage(true);
	    } catch (Exception e) {
		new MessageDialog("Error", e.getMessage());
	    }
	}
    }

    public int getManufacturerId() {
	return Integer.valueOf(mIdTxt.getText());
    }

    // private void populateManufacturerNamesCbx() {
    // if (mNameCbx == null) {
    // mNameCbx = new JComboBox<String>();
    // }
    // ManufacturerHandler handler = new ManufacturerHandler();
    // Vector<String> names = null;
    // try {
    // names = handler.getManufacturerNames();
    // } catch (Exception e) {
    // new MessageDialog("Error", e.getMessage());
    // }
    // if (names == null) {
    // mNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>());
    // } else {
    // mNameCbx.setModel(new javax.swing.DefaultComboBoxModel<String>(
    // names));
    // }
    // }
    //
    // }
    private Manufacturer getTextFieldValues() {
	Manufacturer m = new Manufacturer();
	try {
	    m.setmId(Integer.valueOf(mIdTxt.getText()));
	    m.setmName(mNameTxt.getText());
	    m.setContactPerson1(contactPerson1Txt.getText());
	    m.setContactPerson2(contactPerson2Txt.getText());
	    m.settNumber(Integer.valueOf(tNumberTxt.getText()));
	    m.setCellNumber(Integer.valueOf(cellNumberTxt.getText()));
	    m.setAddress(addressTxt.getText());
	    m.setWeb(webTxt.getText());
	    m.setAccountNumber(accountNumberTxt.getText());

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

    private class EditManfacturerListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    if (isInEditMode()) {
		Manufacturer c = getTextFieldValues();

		if (!manufacturer.equals(c)) {
		    try {
			UpdateManufacturerCaller.perform(c);
			manufacturer = c;
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