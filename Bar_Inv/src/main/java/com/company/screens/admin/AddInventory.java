package com.company.screens.admin;

import com.company.DAO.fileIO.AddItems;
import com.company.DAO.fileIO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.ItemService;

import java.io.File;
import java.util.Scanner;

public class AddInventory implements Screen {
    File inventory = new File("resources/inventory.csv");

    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication) app).getScanner();
        ItemService itemService = ((BarInventoryApplication) app).getItemService();

        System.out.println("Here's what we have now:");
        itemService.getAllItems();
//        ReadWholeInv.printAll();
        //add new items and populate their initial values
        System.out.println("Add inventory items:\n" +
                "Item name:");
        String s = scanner.nextLine();
        System.out.println("ID number");
        int id = scanner.nextInt();
        System.out.println("Quantity on hand");
        int onHand = scanner.nextInt();
        System.out.println("Quantity that makes you nervous");
        int lowLevel = scanner.nextInt();
        System.out.println("How many do you normally want on hand?");
        int optLevel = scanner.nextInt();
        scanner.nextLine();

        itemService.addItem(s, id, onHand, lowLevel, optLevel);


//        //add another or go back to the Menu
        System.out.println("Awesome, got it. Want to add another? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new AddInventory();
        } else {
            return new Menu();

        }
    }
}