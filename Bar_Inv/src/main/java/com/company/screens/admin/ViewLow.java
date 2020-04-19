package com.company.screens.admin;

import com.company.DAO.models.Item;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;
import com.company.services.ItemService;

import java.util.ArrayList;
import java.util.List;

public class ViewLow implements Screen {
    @Override
    public Screen doScreen(Application app) {
        ItemService itemService = ((BarInventoryApplication)app).getItemService();

        System.out.println("These items need to be ordered soon");
        List<Item> orderSoon = itemService.orderSoon();         //view items whose onHand<optLevel
        System.out.println(orderSoon.toString());

        System.out.println("\n\n\nThese items need to be ordered now");
        List<Item> orderNow = itemService.orderNow();          //view items whose onHand<lowLevel
        System.out.println(orderNow.toString());

        System.out.println("\n\n\nThese items are on back order and you should really fix that");
        List<Item> backOrder = itemService.backOrderItems();   //view items whose onHand<=0
        System.out.println(backOrder.toString());
        System.out.println("\n\n\n");



        return new Menu();
    }
}
