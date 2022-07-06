package com.techelevator;

import java.util.Locale;
import java.util.Scanner;

public class Purchase {
    //product key
    String productKey;
    String tempProductKey;
    public Purchase(){}

    //purchase method
    public String purchasing(){

        Scanner userSelect = new Scanner(System.in);
        System.out.print("Please enter key of the item you'd like: ");
        String productKey = userSelect.nextLine();
        System.out.println("You selected: " + productKey.toUpperCase(Locale.ROOT));

        this.tempProductKey = productKey.toUpperCase(Locale.ROOT);
        return tempProductKey;
    }


    //getter

    public String getTempProductKey() {
        return tempProductKey;
    }

    public String getProductKey() {
        return productKey;
    }

}
