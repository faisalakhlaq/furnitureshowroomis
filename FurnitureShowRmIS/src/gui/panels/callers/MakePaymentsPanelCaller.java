package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.MakePaymentsPanel;

import java.awt.event.ActionEvent;

import model.Payments;
import callers.ISCaller;

public class MakePaymentsPanelCaller extends ISCaller {
    private Payments payment = null;

    public MakePaymentsPanelCaller() {
    }

    public MakePaymentsPanelCaller(Payments p) {
	payment = p;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
	perform();
    }

 
    public void perform() {
	MakePaymentsPanel makePanel = null;
	if (payment != null) {
	    makePanel = new MakePaymentsPanel(payment);
	} else {
	    makePanel = new MakePaymentsPanel();
	}

	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Payments", makePanel);
    }
    }


