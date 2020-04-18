package com.company.screens.admin;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.Screen;
import com.company.screens.admin.Menu;
import com.company.services.ItemService;

public class ViewLow implements Screen {
    @Override
    public Screen doScreen(Application app) {
        ItemService itemService = ((BarInventoryApplication)app).getItemService();
        System.out.println("These items need to be ordered soon");
        itemService.orderSoon();         //view items whose onHand<optLevel

        System.out.println("\n\n\nThese items need to be ordered now");
        itemService.orderNow();          //view items whose onHand<lowLevel

        System.out.println("\n\n\nThese items are on back order and you should really fix that");
        itemService.backOrderItems();   //view items whose onHand<=0



        return new Menu();
    }
}
