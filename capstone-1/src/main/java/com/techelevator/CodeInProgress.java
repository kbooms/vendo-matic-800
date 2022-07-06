package com.techelevator;

import java.util.Scanner;

public class CodeInProgress {

    //Math for purchasing

    private double balance = 0;


    public double feedMoney(){
        System.out.println("Insert whole dollar amounts. Does not Accept $50 or $100 bills. ");
        Scanner userFeed = new Scanner(System.in);
        String feed = userFeed.nextLine();

        System.out.println("Balance is now: $" + Double.parseDouble(feed));
        return Double.parseDouble(feed);
    }

    public double balance(){
        double newBalance = feedMoney();
        setBalance(newBalance);

        if (balance > 0){
            newBalance = balance + feedMoney();
            setBalance(newBalance);
        }

        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

