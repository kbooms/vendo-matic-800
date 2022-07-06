package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

import java.util.Scanner;

public class VendingMachineCLI {

	//static variables for [] defining
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH = "Finish Transaction";


	//Original menu code
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	//added Menu code
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT = "Sales Report";

	//String[] builders
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_REPORT };
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY,PURCHASE_MENU_SELECT_PRODUCT,PURCHASE_MENU_FINISH};

	private Menu menu;
	//new purchase menu item for run method
	private PurchaseMenu purchaseMenu;
	private LoadMachine loadMachine;
	private Purchase purchase;

	private PurchaseLogger logger;

	//added purchase menu, load machine, purchase and purchaseLog to the cli constructor
	public VendingMachineCLI(Menu menu, PurchaseMenu purchaseMenu, LoadMachine loadMachine, Purchase purchase, PurchaseLogger logger) {
		this.menu = menu; this.purchaseMenu = purchaseMenu; this.loadMachine = loadMachine; this.purchase = purchase; this.logger = logger;
	}

	public void run() {
		loadMachine.machineLoaded();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				loadMachine.showProducts();
				System.out.println(System.lineSeparator());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choice2 = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice2.equals(PURCHASE_MENU_FEED_MONEY)) {
						//runs feed method and then logs the intake
						purchaseMenu.feedMoney();
						logger.logPurchase("FEED MONEY: $" + purchaseMenu.getUnAlteredBalance() +" $"+ purchaseMenu.getAdjustedBalance());
					} else if (choice2.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						//display products
						loadMachine.showProducts();
						//allow input of which 'key' to buy
						purchase.purchasing();
						//uses temp key to unlock contained key, key is converted to uppercase to allow for case insensitivity
						if(!loadMachine.getProducts().containsKey(purchase.getTempProductKey())) {
							//catches if key does not exist and returns to menu
							System.out.println("That item does not exist, returning to purchase menu: ");
						} else if((loadMachine.getProducts().get(purchase.getTempProductKey()).getInventory() == 0)){
							//catches if that item is sold out and informs customer, returning to menu
							System.out.println("\r\nSorry that item is unavailable, returning to purchase menu: ");
						}else{
							//dispenses item, adjusts inventory and balances, then logs
							System.out.println("\r\nItem purchased, dispensing " + loadMachine.getProducts().get(purchase.getTempProductKey()).getName() + ": " + loadMachine.getProducts().get(purchase.getTempProductKey()).getMessage() + System.lineSeparator());
							purchaseMenu.removeBalance(loadMachine.getProducts().get(purchase.getTempProductKey()).getCost());
							loadMachine.getProducts().get(purchase.getTempProductKey()).getReducedInventory(true);
							logger.logPurchase(loadMachine.getProducts().get(purchase.getTempProductKey()).getName() + " " + loadMachine.getProducts().get(purchase.getTempProductKey()).getKey() + " $" + purchaseMenu.getUnAlteredBalance() + " $" + purchaseMenu.getAdjustedBalance());
						}
					} else if (choice2.equals(PURCHASE_MENU_FINISH)) {
						//returns change and adjusts balances, then logs before breaking loop to return to main menu
						purchaseMenu.returnChange();
						logger.logPurchase("CHANGE DISPENSED: $" + purchaseMenu.getUnAlteredBalance() + " $" + purchaseMenu.getBalance());
						break;
					}
					continue;
				}
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				//thanks customer and ends program
				System.out.println("\nThank you for using the Vendo-matic 800");break;
			}else if (choice.equals(MAIN_MENU_OPTION_REPORT)){
				//secret programs
			}
		}
	}
//BreakPoint fixed, needed to add purchaseMenu into the code as a new object and into the vending cli
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		PurchaseMenu purchaseMenu = new PurchaseMenu(System.in, System.out);
		LoadMachine loadMachine = new LoadMachine();
		Purchase purchase = new Purchase();
		PurchaseLogger logger = new PurchaseLogger();
		VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenu, loadMachine, purchase, logger);
		cli.run();
	}
}
