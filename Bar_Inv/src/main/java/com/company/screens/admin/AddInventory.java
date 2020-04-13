package com.company.screens.admin;

import com.company.DAO.AddItems;
import com.company.DAO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;

import java.util.Scanner;

public class AddInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication) app).getScanner();
        String[] newItem = new String[5];
        System.out.println("Here's what we have now:");
        ReadWholeInv.printAll();
        //add new items and populate their initial values
        System.out.println("Add inventory items:\n" +
                "Item name:");
        newItem[0] = scanner.nextLine();
        System.out.println("ID number");
        newItem[1] = scanner.nextLine();
        System.out.println("Quantity on hand");
        newItem[2] = scanner.nextLine();
        System.out.println("Quantity that makes you nervous");
        newItem[3] = scanner.nextLine();
        System.out.println("How many do you normally want on hand?");
        newItem[4] = scanner.nextLine();

        //add a new line to inventory.csv
        AddItems.adder(newItem);

        System.out.println("Awesome, got it. Want to add another? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new AddInventory();
        } else {
            return new Menu();
        }
    }
}
