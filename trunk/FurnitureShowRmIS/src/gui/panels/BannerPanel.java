package gui.panels;


import gui.AbstractPanel;
import gui.GuiPanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BannerPanel extends AbstractPanel {
	public BannerPanel() {
		addPanels();
	}

	public void addPanels() {
		GuiPanel panel = getCenterPanel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(panel, c);
	}

	@Override
	public GuiPanel getCenterPanel() {
		GuiPanel bannerPanel = new GuiPanel();
		bannerPanel.add(new JLabel("Furniture Showroom Information System"),
				BorderLayout.CENTER);

		return bannerPanel;
	}

	@Override
	public GuiPanel getButtonPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuiPanel getBannerPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
