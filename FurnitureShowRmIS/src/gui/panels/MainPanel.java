package gui.panels;

import gui.GuiFrame;
import gui.GuiPanel;
import gui.menubar.MainViewMenuBar;
import gui.toolbar.MainViewToolBar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainPanel extends GuiFrame {

    private DesktopTabbedPane desktopPane = DesktopTabbedPane.getInstance();

    private MainViewToolBar toolBar = new MainViewToolBar();

    private BannerPanel bannerpanel = new BannerPanel();

    public MainPanel() {
	configurePanel();
	addToolBar();
	addPanel();
	addMenuBar();
    }

    private void addToolBar() {
	getContentPane().add(BorderLayout.NORTH, toolBar);
    }

    private void configurePanel() {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight = (int) (screenSize.getHeight() / 1.5);
	int screenWidth = (int) screenSize.getWidth() / 2;
	Dimension d = new Dimension(screenWidth, screenHeight);
	this.setMinimumSize(d);
    }

    private void addPanel() {
	Container pane = getContentPane();
	GuiPanel p = new GuiPanel();
	p.setLayout(new BorderLayout());
	p.add(desktopPane);
	p.add(BorderLayout.NORTH, bannerpanel);
	pane.add(BorderLayout.CENTER, p);
    }

    public void addMenuBar() {
	MainViewMenuBar menuBar = MainViewMenuBar.getInstane();
	this.setJMenuBar(menuBar);
    }

    public void createAndDisplayGui() {

	pack();
	setVisible(true);
    }
}