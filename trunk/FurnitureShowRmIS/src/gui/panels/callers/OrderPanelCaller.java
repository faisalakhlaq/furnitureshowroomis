package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.OrderPanel;
import gui.panels.PaymentsPanel;

import java.awt.event.ActionEvent;

import model.Orders;
import model.Payments;

import callers.ISCaller;

public class OrderPanelCaller extends ISCaller {

    private Orders order = null;

    public OrderPanelCaller() {
    }

    public OrderPanelCaller(Orders o) {
	order = o;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

 
    public void perform() {
	OrderPanel orderPanel = null;
	if (order != null) {
	    orderPanel = new OrderPanel(order);
	} else {
	    orderPanel = new OrderPanel();
	}

	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Order", orderPanel);
    }

}
