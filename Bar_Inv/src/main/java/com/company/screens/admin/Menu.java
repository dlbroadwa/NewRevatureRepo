package com.company.screens.admin;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.*;

import java.util.Scanner;

public class Menu implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

//view options
        System.out.println(
                "Choose an option, then press Enter: \n" +
                "1. Update inventory \n" +
                "2. Add inventory item \n" +
                "3. Remove inventory item \n" +
                "4. View low inventory items \n" +
                "Press 0 to exit");

        //listen for input
        int choice = scanner.nextInt();

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
