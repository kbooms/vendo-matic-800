package com.techelevator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PurchaseLogger {

//    Formatting for log.txt
//                    01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00
//                    >01/01/2016 12:00:15 PM FEED MONEY: $5.00 $10.00
//                    >01/01/2016 12:00:20 PM Crunchie B4 $10.00 $8.50
//                    >01/01/2016 12:01:25 PM Cowtales B2 $8.50 $7.50
//                    >01/01/2016 12:01:35 PM GIVE CHANGE: $7.50 $0.00



    public static void logPurchase(String message){
        File search = new File("log.txt");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formattedDateTime = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formatted = dateTime.format(formattedDateTime);

        try(PrintWriter logWriter = new PrintWriter(new FileOutputStream(search, true))){
            logWriter.println(formatted + " " + message);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
