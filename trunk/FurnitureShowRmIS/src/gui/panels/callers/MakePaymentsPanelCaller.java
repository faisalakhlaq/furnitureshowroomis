package gui.panels.callers;

import gui.panels.DesktopTabbedPane;
import gui.panels.PaymentsPanel;

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
	PaymentsPanel makePanel = null;
	if (payment != null) {
	    makePanel = new PaymentsPanel(payment);
	} else {
	    makePanel = new PaymentsPanel();
	}

	DesktopTabbedPane pane = DesktopTabbedPane.getInstance();
	pane.addPanel("Payments", makePanel);
    }
    }


