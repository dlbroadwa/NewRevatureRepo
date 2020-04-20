package com.company.screens.customer;

import com.company.DAO.fileIO.ReadWholeInv;
import com.company.DAO.models.Item;
import com.company.DAO.models.Order;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.AddInventory;
import com.company.screens.admin.Menu;
import com.company.services.ItemService;
import com.company.services.OrdersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewInventory implements Screen {
    @Override
    public Screen doScreen(Application app) throws Exception {
        Scanner scanner = ((BarInventoryApplication) app).getScanner();
        ItemService itemService = ((BarInventoryApplication) app).getItemService();
        OrdersService ordersService = ((BarInventoryApplication) app).getOrdersService();
        //view all inventory
        System.out.println("View the inventory list");
//        ReadWholeInv.printAll();
        itemService.getAllItemsForCustomer();
        System.out.println("Any negative numbers reflect items that are on back order.\n" +
                "You may order items on back order, but they may take longer to deliver.");

        //place orders
        System.out.println("Enter the ID number of the item you want");
        int id = scanner.nextInt();
        System.out.println("How many do you want?");
        int quant = scanner.nextInt();
        scanner.nextLine();
        String user = ((BarInventoryApplication)app).getCurrentUser();
        ordersService.addOrder(user, id, quant); //save this order

        Item i = itemService.itemByID(id); //get's the pre-order state of the inventory item
        Item update = new Item();
        //copy most of the info for the item
        update.setItemName(i.getItemName());
        update.setOptLevel(i.getOptLevel());
        update.setLowLevel(i.getLowLevel());
        update.setId(id);
        //now reduce the onHand amount of the specified item by quant
        update.setOnHand(i.getOnHand() - quant);
        itemService.updateItem(update); //update the item on the DB

        //add another order or go back to the customer menu
        System.out.println("Awesome, got it. Want to order another? [y/n]");
        String cont = scanner.nextLine();
        if (cont.equals("y")) {
            return new ViewInventory();
        } else {
            return new CustMenu();
        }
    }
}
