package gui.toolbar;

import gui.buttons.ToolBarButton;
import gui.panels.callers.CategoryPanelCaller;
import gui.panels.callers.ExitSystemCaller;
import gui.panels.callers.ExpensesPanelCaller;
import gui.panels.callers.ManufacturerPanelCaller;
import gui.panels.callers.ProductPanelCaller;
import gui.panels.callers.SalesPanelCaller;

import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MainViewToolBar extends ISToolBar {
    public MainViewToolBar() {
	setFloatable(true);
	addButtons();
    }

    public void addButtons() {
	addExitBtn();
	addSaleBtn();
	addExpensesBtn();
	addManufacturerBtn();
	addProductBtn();
	addCategoryBtn();
    }

    private void addCategoryBtn() {
	ToolBarButton categoryBtn = new ToolBarButton(
		"Insert-Update-Delete Category",
		"/resources/category_icon.png", new CategoryPanelCaller());
	add(categoryBtn);
    }

    private void addProductBtn() {
	ToolBarButton productBtn = new ToolBarButton(
		"Insert-Update-Delete Product", "/resources/product.png",
		new ProductPanelCaller());
	add(productBtn);
    }

    private void addManufacturerBtn() {
	ToolBarButton manufacturerBtn = new ToolBarButton(
		"Insert-Update-Delete Manufacturer",
		"/resources/manufacturer.png", new ManufacturerPanelCaller());
	add(manufacturerBtn);
    }

    private void addExpensesBtn() {
	ToolBarButton expensesBtn = new ToolBarButton(
		"Insert-Update-Delete Expenses", "/resources/expense.png",
		new ExpensesPanelCaller());
	add(expensesBtn);
    }

    private void addSaleBtn() {
	ToolBarButton saleBtn = new ToolBarButton("Insert-Update-Delete Sale",
		"/resources/add_sale.png", new SalesPanelCaller());
	add(saleBtn);
    }

    private void addExitBtn() {
	ToolBarButton exitBtn = new ToolBarButton("Exit System",
		"/resources/exit_icon.png", new ExitSystemCaller());
	exitBtn.setMnemonic(KeyEvent.VK_W);
	add(exitBtn);
    }

}