package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LoadMachine {
//map and file path
    private static Map<String, Product> products = new HashMap<>();
    private static List<String> keys = new ArrayList<>();
    private final File productInventory = new File("vendingmachine.csv");
    //constructor
    public LoadMachine(){}
    //method for assigning data from file to map
    public String machineLoaded() {
        try {
            //reading file
           Scanner inventoryInput = new Scanner(productInventory);

           while (inventoryInput.hasNextLine()){
               //splitting line into values
               String lineOfInput = inventoryInput.nextLine();
               String[] splitLine = lineOfInput.split("[|]");
            //assign values?
               Product object = new Product(splitLine[0],splitLine[1],Double.parseDouble(splitLine[2]),splitLine[3], 5 );
                //hopefully adding each new line to a different spot in map
               products.put(object.getKey(), object);
               keys.add(splitLine[0]);

           }

            //file not found catch
        } catch (FileNotFoundException e) {
            System.err.println("Inventory not found.");
        }
        return "Machine Loaded.";
    }

    //Printing out products for options 1 and 2
    public String showProducts() {


        for (int i = 0; i < keys.size(); i++) {

            System.out.println(getProducts().get(getKeys().get(i)).getKey() + " | " + getProducts().get(getKeys().get(i)).getName() + " | " + getProducts().get(getKeys().get(i)).getCost() +" | " + getProducts().get(getKeys().get(i)).isSoldOut());
        }
        return "\r\nBack to Main menu.";
    }

    //getter


    public Map<String, Product> getProducts() {
        return products;
    }

    public List<String> getKeys() {
        return keys;
    }
}
