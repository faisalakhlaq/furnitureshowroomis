package gui.menubar;

import gui.deletepanels.callers.DeleteCategoryPanelCaller;
import gui.deletepanels.callers.DeleteExpensesPanelCaller;
import gui.deletepanels.callers.DeleteManufacturerPanelCaller;
import gui.deletepanels.callers.DeleteProductPanelCaller;

import gui.panels.callers.CategoryPanelCaller;
import gui.panels.callers.ExpensesPanelCaller;
import gui.panels.callers.MakePaymentsPanelCaller;
import gui.panels.callers.ManufacturerPanelCaller;
import gui.panels.callers.ProductPanelCaller;
import gui.panels.callers.SalesPanelCaller;
import gui.searchpanels.callers.SearchExpensesPanelCaller;
import gui.searchpanels.callers.SearchManufacturerPanelCaller;
import gui.searchpanels.callers.SearchProductPanelCaller;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainViewMenuBar extends ISMenuBar {
    private static MainViewMenuBar instance = null;

    private MainViewMenuBar() {
	addMenus();
    }

    private void addMenus() {
	addFileMenu();
	addSaleMenu();
	addExpensesMenu();
	addManufacturerMenu();
	addProductMenu();
	addCategoryMenu();
	addPaymentsMenu();
	addHelpMenu();
    }

    private void addPaymentsMenu() {
	JMenu payments = new JMenu("Payments");
	this.add(payments);
	payments.setMnemonic(KeyEvent.VK_C);

	JMenuItem makePayments = new JMenuItem("Make Payment");
	payments.add(makePayments);
	makePayments.addActionListener(new MakePaymentsPanelCaller());

	
    }

    private void addCategoryMenu() {
	JMenu category = new JMenu("Category");
	this.add(category);
	category.setMnemonic(KeyEvent.VK_C);

	JMenuItem newCategory = new JMenuItem("New Category");
	category.add(newCategory);
	newCategory.addActionListener(new CategoryPanelCaller());

	JMenuItem editCategory = new JMenuItem("Edit Category");
	category.add(editCategory);
	editCategory.addActionListener(new CategoryPanelCaller());

	JMenuItem deleteCategory = new JMenuItem("Delete Category");
	category.add(deleteCategory);
	deleteCategory.addActionListener(new DeleteCategoryPanelCaller());

	JMenuItem updateCategory = new JMenuItem("Update Category");
	category.add(updateCategory);
	updateCategory.addActionListener(new CategoryPanelCaller());
	
	JMenuItem searchCategory = new JMenuItem("Search Category");
	category.add(searchCategory);
	searchCategory.addActionListener(new SearchProductPanelCaller());

    }

    private void addProductMenu() {
	JMenu product = new JMenu("Product");
	this.add(product);
	product.setMnemonic(KeyEvent.VK_P);

	JMenuItem newProduct = new JMenuItem("New Product");
	product.add(newProduct);
	newProduct.addActionListener(new ProductPanelCaller());

	JMenuItem editProduct = new JMenuItem("Edit Product");
	product.add(editProduct);
	editProduct.addActionListener(new ProductPanelCaller());

	JMenuItem deleteProduct = new JMenuItem("Delete Product");
	product.add(deleteProduct);
	deleteProduct.addActionListener(new DeleteProductPanelCaller());

	JMenuItem updateProduct = new JMenuItem("Update Product");
	product.add(updateProduct);
	updateProduct.addActionListener(new ProductPanelCaller());

	JMenuItem searchProduct = new JMenuItem("Search Product");
	product.add(searchProduct);
	searchProduct.addActionListener(new SearchProductPanelCaller());
    }

    private void addManufacturerMenu() {
	JMenu manufacturer = new JMenu("Manfacturer");
	this.add(manufacturer);
	manufacturer.setMnemonic(KeyEvent.VK_M);

	JMenuItem newManfacturer = new JMenuItem("New Manfacturer");
	manufacturer.add(newManfacturer);
	newManfacturer.addActionListener(new ManufacturerPanelCaller());

	JMenuItem editManfacturer = new JMenuItem("Edit Manfacturer");
	manufacturer.add(editManfacturer);
	editManfacturer.addActionListener(new ManufacturerPanelCaller());

	JMenuItem deleteManfacturer = new JMenuItem("Delete Manfacturer");
	manufacturer.add(deleteManfacturer);
	deleteManfacturer.addActionListener(new DeleteManufacturerPanelCaller());

	JMenuItem updateManfacturer = new JMenuItem("Update Manfacturer");
	manufacturer.add(updateManfacturer);
	updateManfacturer.addActionListener(new ManufacturerPanelCaller());

	JMenuItem searchManfacturer = new JMenuItem("Search Manfacturer");
	manufacturer.add(searchManfacturer);
	searchManfacturer
		.addActionListener(new SearchManufacturerPanelCaller());
    }

    private void addExpensesMenu() {
	JMenu expenses = new JMenu("Expenses");
	this.add(expenses);
	expenses.setMnemonic(KeyEvent.VK_E);

	JMenuItem newExpenses = new JMenuItem("New Expenses");
	expenses.add(newExpenses);
	newExpenses.addActionListener(new ExpensesPanelCaller());

	JMenuItem editExpenses = new JMenuItem("Edit Expenses");
	expenses.add(editExpenses);
	editExpenses.addActionListener(new ExpensesPanelCaller());

	JMenuItem updateExpenses = new JMenuItem("Update Expenses");
	expenses.add(updateExpenses);
	updateExpenses.addActionListener(new ExpensesPanelCaller());

	JMenuItem deleteExpenses = new JMenuItem("Delete Expenses");
	expenses.add(deleteExpenses);
	deleteExpenses.addActionListener(new DeleteExpensesPanelCaller());
	
	JMenuItem searchExpenses = new JMenuItem("Search Expenses");
	expenses.add(searchExpenses);
	searchExpenses.addActionListener(new SearchExpensesPanelCaller());

    }

    private void addSaleMenu() {
	JMenu sale = new JMenu("Sale");
	this.add(sale);
	sale.setMnemonic(KeyEvent.VK_S);

	JMenuItem newSale = new JMenuItem("New Sale");
	sale.add(newSale);
	newSale.addActionListener(new SalesPanelCaller());

	JMenuItem editSale = new JMenuItem("Edit Sale");
	sale.add(editSale);
	editSale.addActionListener(new SalesPanelCaller());

	JMenuItem updateSale = new JMenuItem("Update Sale");
	sale.add(updateSale);
	updateSale.addActionListener(new SalesPanelCaller());

	JMenuItem deleteSale = new JMenuItem("Delete Sale");
	sale.add(deleteSale);
	deleteSale.addActionListener(new SalesPanelCaller());
    }

    private void addHelpMenu() {
	JMenu transaction = new JMenu("Help");
	this.add(transaction);
    }

    public void addFileMenu() {
	JMenu file = new JMenu("File");
	this.add(file);
    }

    public static MainViewMenuBar getInstane() {
	if (instance == null)
	    instance = new MainViewMenuBar();

	return instance;
    }
}
