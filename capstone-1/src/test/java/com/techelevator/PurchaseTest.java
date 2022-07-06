package com.techelevator;

import com.techelevator.Purchase;
import org.junit.Assert;
import org.junit.Test;

public class PurchaseTest {

    Purchase testPurchase = new Purchase();

    @Test
    public void testProductKey() {

        String actualProductKey = testPurchase.getTempProductKey();
        String expectProductKey = testPurchase.getProductKey();

        Assert.assertEquals(expectProductKey, actualProductKey);
    }
}