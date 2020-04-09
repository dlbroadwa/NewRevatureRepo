package com.company.screens;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;

import java.util.Scanner;

public class Menu implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

//view options
        System.out.println("Choose an option, then press Enter:");
        System.out.println("1. Update inventory");
        System.out.println("2. Add inventory item");
        System.out.println("3. Remove inventory item");
        System.out.println("4. View low inventory items");
        System.out.println("Press 0 to exit");

        //listen for input
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                //update inventory
                System.out.println("Update inventory");
                break;
            case 2:
                //add inventory item
                System.out.println("Add or remove inventory items");
                break;
            case 3:
                //remove inventory items
                System.out.println("Remove inventory items");
                break;
            case 4:
                //view low inventory items
                System.out.println("View low inventory items");
            case 0:
                //exit
                break;
            default:
                System.out.println("Invalid entry");
                return new Menu();


        }
        return null;
    }



}

