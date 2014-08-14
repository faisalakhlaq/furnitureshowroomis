package gui.deletepanels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.panels.callers.ClosePanelCaller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.Helper;
import callers.ISCaller;

@SuppressWarnings("serial")
public abstract class AbstractDeletePanel extends AbstractPanel implements
	ItemListener {

    private JComboBox<Integer> idCbx = null;
    private JTextField nameTxt = null;
    private JButton deleteBtn = null;
    private JButton exitBtn = null;

    public AbstractDeletePanel() {
	addPanels();
	idCbx.addItemListener(this);
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel p = new GuiPanel();
	GridBagLayout layout = new GridBagLayout();
	p.setLayout(layout);

	JLabel idLbl = new JLabel("ID");
	idCbx = new JComboBox<Integer>();
	idCbx.setModel(getComboBoxModel());
	JLabel nameLbl = new JLabel("Name");
	nameTxt = new JTextField(20);

	GridBagConstraints c = new GridBagConstraints();

	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	p.add(idLbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	p.add(idCbx, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	p.add(nameLbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	p.add(nameTxt, c);

	return p;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel p = new GuiPanel();
	deleteBtn = new JButton("Delete");

	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());

	p.add(deleteBtn);
	p.add(exitBtn);
	return p;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel p = new GuiPanel();
	p.add(new JLabel("Delete"));
	return p;
    }

    protected abstract javax.swing.DefaultComboBoxModel<Integer> getComboBoxModel();

    protected void addButtonListener(ISCaller searchListener) {
	deleteBtn.addActionListener(searchListener);
    }

    protected String getId() throws Exception {
	String id = idCbx.getSelectedItem().toString();

	if (Helper.isEmpty(id)) {
	    throw new Exception("ID Cannot be null");
	}

	return id;
    }

    public void refreshComboBoxModel() {
	idCbx.setModel(getComboBoxModel());
	nameTxt.setText(null);
    }

    protected void setNameText(String name) {
	nameTxt.setText(name);
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
