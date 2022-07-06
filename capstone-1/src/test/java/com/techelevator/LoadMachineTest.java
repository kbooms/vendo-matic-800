package com.techelevator;

import org.junit.Test;
import org.junit.Assert;

public class LoadMachineTest {
    LoadMachine testLoad = new LoadMachine();
    Product testProduct = new Product("A1", "TestChip", 2.00, "Chip", 5);
    boolean canShowProducts = false;

    @Test
    public void testShowProducts() {
            if (testLoad != null)
                if (testProduct != null)
                    canShowProducts = true;
    }

    @Test
    public void testMachineLoaded() {
        Assert.assertEquals("Machine Loaded.", testLoad.machineLoaded());
    }
}
