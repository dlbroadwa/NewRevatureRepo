package com.company.screens;

import com.company.apps.Application;
import com.company.apps.Inventory;
import com.company.apps.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InventoryScreen implements Screens {
    protected Connection connection;
    protected ResultSet resultSet;
    protected Statement statement;
    protected Inventory inventory;
    protected ArrayList<Item> current_items;

    public InventoryScreen(Inventory i){
        this.inventory= i;
    }

    public Screens display(Application app, Inventory i) {
        this.current_items = inventory.getCurrent_items();
        printCurrentInventory(current_items);

        return null;
    }

    public InventoryScreen printCurrentInventory(ArrayList<Item> current_items){
        int x = 0;

        System.out.print("Your Current Inventory: \n"+
                        "--------------------------\n"+
                        "ID|  Name  | Quantity\n");

        while (x<current_items.size()) { //returns false if no more rows
            System.out.print(
                      current_items.get(x).getItem_id()+" | "
                    + current_items.get(x).getItem_name()+" | "
                    + current_items.get(x).getQuantity()+"\n"
                    );
            x++;
        }
        return null;
    }


}
