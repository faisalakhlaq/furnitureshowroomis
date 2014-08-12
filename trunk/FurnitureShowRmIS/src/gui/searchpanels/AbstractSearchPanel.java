package gui.searchpanels;

import gui.AbstractPanel;
import gui.GuiPanel;
import gui.panels.callers.ClosePanelCaller;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import utils.Helper;
import database.caller.ISCaller;

@SuppressWarnings("serial")
public abstract class AbstractSearchPanel extends AbstractPanel {

    private JTextField idTxt = null;
    private JButton searchBtn = null;
    private JButton exitBtn = null;

    public AbstractSearchPanel() {
	addPanels();
	idTxt.requestFocusInWindow();
	idTxt.requestFocus();
	idTxt.requestFocus(true);
    }

    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel p = new GuiPanel();
	JLabel idLbl = new JLabel("ID");
	idTxt = new JTextField(20);

	p.add(idLbl);
	p.add(idTxt);

	return p;
    }

    @Override
    public GuiPanel getButtonPanel() {
	GuiPanel p = new GuiPanel();
	searchBtn = new JButton("Search");

	exitBtn = new JButton("Exit");
	exitBtn.addActionListener(new ClosePanelCaller());

	p.add(searchBtn);
	p.add(exitBtn);
	return p;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel p = new GuiPanel();
	p.add(new JLabel("Search"));
	return p;
    }

    protected void addButtonListener(ISCaller searchListener) {
	searchBtn.addActionListener(searchListener);
    }

    protected String getId() throws Exception {
	String id = idTxt.getText();

	if (Helper.isEmpty(id)) {
	    throw new Exception("ID Cannot be null");
	}

	return id;
    }
}