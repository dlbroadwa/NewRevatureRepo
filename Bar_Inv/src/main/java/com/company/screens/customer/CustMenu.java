package com.company.screens.customer;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;

import java.util.Scanner;

public class CustMenu implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

        //view options
        System.out.println("Choose an option, then press Enter:");
        System.out.println("1. View our stock and place an order");
        System.out.println("2. Check your open orders");
        System.out.println("Press 0 to exit");

        //listen for input
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                //view inventory and place order
                System.out.println("View our stock and place an order");
                return new ViewInventory();
            case 2:
                //display order history for user
                System.out.println("Check your open orders");
                return new PrevOrders();
            case 0:
                //exit
                System.out.println("K, byeeee");
                break;
            default:
                System.out.println("Invalid entry, let's try that again \n");
                return new CustMenu();


        }
        return null;
    }

}