package com.company.screens.admin;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.*;
import com.company.screens.admin.orders.viewCustOrders;

import java.util.Scanner;

public class Menu implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

//view admin options
        System.out.println(
                "Choose an option, then press Enter: \n" +
                "1. Update inventory \n" +
                "2. Add inventory item \n" +
                "3. Remove inventory item \n" +
                "4. View low inventory items \n" +
                "5. View customer orders \n" +
                "Press 0 to exit");

        //listen for input
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                //update inventory
                System.out.println("Update inventory");
                return new UpdateInventory();
            case 2:
                //add inventory item
                System.out.println("Add inventory items");
                return new AddInventory();
            case 3:
                //remove inventory items
                System.out.println("Remove inventory items");
                return new RemoveInventory();
            case 4:
                //view low inventory items
                System.out.println("View low inventory items");
                return new ViewLow();
            case 5:
                //view the orders
                System.out.println("View customer orders");
                return new viewCustOrders();
            case 0:
                //exit
                System.out.println("K, byeeee");
                break;
            default:
                System.out.println("Invalid entry, let's try that again \n");
                return new Menu();


        }
        return null;
    }



}

