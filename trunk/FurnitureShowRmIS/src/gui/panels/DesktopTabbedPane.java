package gui.panels;

import gui.GuiPanel;
import gui.buttons.TabCloseButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class DesktopTabbedPane extends JTabbedPane {

    private static DesktopTabbedPane instance = null;

    private DesktopTabbedPane() {
    }

    public static DesktopTabbedPane getInstance() {
	if (instance == null)
	    instance = new DesktopTabbedPane();

	return instance;
    }

    public void addPanel(String title, final GuiPanel panel) {
	GuiPanel titlePanel = new GuiPanel();
	titlePanel.setOpaque(false);
	JLabel titleLbl = new JLabel(title);
	titlePanel.add(titleLbl);
	// add more space between the label and the button
	titleLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
	// tab button
	TabCloseButton closeButton = new TabCloseButton();
	closeButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		instance.remove(panel);
	    }
	});
	titlePanel.add(closeButton);

	add(panel);
	this.setTabComponentAt(this.indexOfComponent(panel), titlePanel);
	setSelectedComponent(panel);
    }
}
