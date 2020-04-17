package com.company.screens.customer;

import com.company.DAO.fileIO.ReadWholeInv;
import com.company.DAO.models.Item;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.services.ItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        ItemService itemService = ((BarInventoryApplication)app).getItemService();
        //view all inventory
        System.out.println("View the inventory list");
//        ReadWholeInv.printAll();
        itemService.getAllItemsForCustomer();

        //place orders
        System.out.println("Enter the ID number of the item you want");
        int id = scanner.nextInt();
        System.out.println("How many do you want?");
        int quant = scanner.nextInt();
        Item i = itemService.itemByID(id);


        return new CustMenu();
    }
}
