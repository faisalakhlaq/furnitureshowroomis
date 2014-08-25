package gui.panels;

import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Orders;
import model.Payments;

import org.jdesktop.swingx.JXDatePicker;

public class OrderPanel extends AbstractPanel {
    private JButton exitbtn = null;
    private JButton savebtn = null;
    private JLabel idlbl = null;
    private JLabel descriptionlbl = null;
    private JLabel statuslbl = null;
    private JLabel borrowlbl = null;
    private JLabel orderDatelbl = null;
    private JLabel deliveryDatelbl = null;
    private JXDatePicker orderdatePicker = null;
    private JXDatePicker deliverydatePicker = null;
    private JTextField idtxt = null;
    private JTextField borrowtxt = null;
    private JTextField descriptiontxt = null;
    private JComboBox<String> statusCbx = null;
    private Orders orders = null;
    
    public OrderPanel(Orders o) {
   	addPanels();
   	this.orders = o;
   	fillTextFields();
   	
       }
    public OrderPanel() {
	addPanels();
	
    }

    private void fillTextFields() {
   	if (orders == null)
   	    return;

   	statusCbx.getSelectedItem().toString();
   	borrowtxt.setText(String.valueOf(orders.getBorrow()));
   	descriptiontxt.setText(String.valueOf(orders.getDescription()));
   	orderdatePicker.setDate(orders.getOrderDate());
   	deliverydatePicker.setDate(orders.getDeliveryDate());
       }


    @Override
    public GuiPanel getCenterPanel() {
	GuiPanel centerPanel = new GuiPanel();

	idlbl = new JLabel("Order Id");
	descriptionlbl = new JLabel("Description");
	statuslbl = new JLabel("Status");
	borrowlbl = new JLabel("Borrow");
	orderDatelbl = new JLabel("Order Date");
	deliveryDatelbl = new JLabel("Delivery Date");
	idtxt = new JTextField(15);
	descriptiontxt = new JTextField(15);
	borrowtxt = new JTextField();
	orderdatePicker = new JXDatePicker();
	deliverydatePicker = new JXDatePicker();
	statusCbx = new JComboBox<String>();
	orderdatePicker.setDate(Calendar.getInstance().getTime());
	orderdatePicker.setFormats(new SimpleDateFormat(" dd/MM/yyyy"));
	deliverydatePicker.setDate(Calendar.getInstance().getTime());
	deliverydatePicker.setFormats(new SimpleDateFormat("dd/MM/yyyy"));


	centerPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	setGridBagConstraints(c, 0, 0, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(idlbl, c);

	setGridBagConstraints(c, 1, 0, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(idtxt, c);

	setGridBagConstraints(c, 0, 1, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(descriptionlbl, c);

	setGridBagConstraints(c, 1, 1, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(descriptiontxt, c);

	setGridBagConstraints(c, 0, 2, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(statuslbl, c);

	setGridBagConstraints(c, 1, 2, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(statusCbx, c);

	setGridBagConstraints(c, 0, 3, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(orderDatelbl, c);

	setGridBagConstraints(c, 1, 3, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(orderdatePicker, c);

	setGridBagConstraints(c, 0, 4, GridBagConstraints.LINE_START, 5, 0);
	centerPanel.add(deliveryDatelbl, c);

	setGridBagConstraints(c, 1, 4, GridBagConstraints.LINE_END, 5, 10);
	centerPanel.add(deliverydatePicker, c);

	
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
	
	buttonPanel.add(savebtn);

	exitbtn = new JButton("Exit");

	buttonPanel.add(exitbtn);

	return buttonPanel;
    }

    @Override
    public GuiPanel getBannerPanel() {
	GuiPanel bannerPanel = new GuiPanel();
	bannerPanel.add(new JLabel("Orders"), BorderLayout.CENTER);
	return bannerPanel;
    }
    @SuppressWarnings("unused")
    private void clearTextFields() {
	borrowtxt.setText(null);
	descriptiontxt.setText(null);
	

    }
}
