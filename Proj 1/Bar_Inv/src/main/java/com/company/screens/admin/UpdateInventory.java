package com.company.screens.admin;

import com.company.DAO.fileIO.ReadWholeInv;
import com.company.DAO.models.Item;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.ItemService;

import java.util.Arrays;
import java.util.Scanner;

public class UpdateInventory implements Screen {

    @Override
    public Screen doScreen(Application app) throws Exception {
        //update values for any item
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        ItemService itemService = ((BarInventoryApplication)app).getItemService();

        //view everything
//        ReadWholeInv.printAll();
        System.out.println("Here's the whole list:");
        itemService.getAllItems();
        System.out.println("Enter the ID number of the item you want to update");
        int id = scanner.nextInt();
        scanner.nextLine();
        Item i = itemService.itemByID(id);

        //display the picked item
        System.out.println("You picked this: \n" +
                "Item Name, onHand, lowLevel, optLevel");
        System.out.println(i.getItemName()+", "+i.getOnHand()+", "+i.getLowLevel()+", "+i.getOptLevel());

        // enter information to update
        // as of now, user must enter new values for each field
        Item update = new Item();
        update.setId(i.getId());
        System.out.println("Update item name");
        String newItemName = scanner.nextLine();
        update.setItemName(newItemName);
        System.out.println("Update quantity in stock");
        update.setOnHand(scanner.nextInt());
        System.out.println("Update the quantity that makes you nervous");
        update.setLowLevel(scanner.nextInt());
        System.out.println("Update how many do you normally want on hand");
        update.setOptLevel(scanner.nextInt());
        itemService.updateItem(update);



        //update another or go back to menu
        System.out.println("Awesome, got it. Want to update another? [y/n]");
        scanner.nextLine();
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new UpdateInventory();
        } else {
            return new Menu();
        }
    }
}
