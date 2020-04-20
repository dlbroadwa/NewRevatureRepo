package com.company.screens.admin;

import com.company.DAO.fileIO.ReadWholeInv;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.ItemService;

import java.util.Scanner;

public class RemoveInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        ItemService itemService = ((BarInventoryApplication)app).getItemService();
        System.out.println("Here's what we have now:");
        itemService.getAllItems();
//        ReadWholeInv.printAll();
        //remove unwanted items and their associated values
        System.out.println("Enter ID number of item to remove");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        itemService.removeItem(id);



        System.out.println("Awesome, got it. Want to remove another? [y/n]"); //choose to remove another or return to menu
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new RemoveInventory();
        } else {
            return new Menu();
        }
    }
}
