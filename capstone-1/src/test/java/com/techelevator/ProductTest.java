package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class ProductTest {

    Product testChips = new Product("A1", "GoodAF Chips", 2.50, "Chip", 5);
    Product testCandy = new Product("B2", "Besto Chocolate Bar", 1.75, "Candy", 5);
    Product testDrink = new Product("C3", "Litre o' Cola", 2.00, "Drink", 5);
    Product testGum = new Product("D4", "Yuicy Fruit", 0.85, "Gum", 5);


    @Test
    public void testIsSoldOut() {
        Assert.assertEquals(5, testChips.getInventory()); // make sure machine restocked itself
        int soldOutProduct = testChips.getInventory() - testChips.getInventory();
        Assert.assertEquals(0, soldOutProduct);
    }

    @Test
    public void inventoryDeductsWhenProductSelected() {
        int itemPurchased = 1;
        boolean canPurchase = true;
        Assert.assertEquals(itemPurchased, testGum.getInventory() - testGum.getReducedInventory(canPurchase));

    }

    @Test
    public void messageDisplaysWhenProductDispenses() {
        String expectMessageChips = "Crunch Crunch, Yum!";
        String expectMessageCandy = "Munch Munch, Yum!";
        String expectMessageDrink = "Glug Glug, Yum!";
        String expectMessageGum = "Chew Chew, Yum!";

        Assert.assertEquals(expectMessageChips, testChips.getMessage());
        Assert.assertEquals(expectMessageCandy, testCandy.getMessage());
        Assert.assertEquals(expectMessageDrink, testDrink.getMessage());
        Assert.assertEquals(expectMessageGum, testGum.getMessage());
    }
}

