package com.inventory.view;

import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;

public class RegisterItem implements Screen{
    @Override
    public Item getNew(){
        ConsoleOut.println("You are currently in the process of registering a new Item.");

        ConsoleOut.println("What is the id of the new item?");
        ConsoleOut.print("ID: ");
        int id = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the name of the new item?");
        ConsoleOut.print("Name: ");
        String name = ConsoleIn.nextLine().trim();

        ConsoleOut.println("What is the financial value of the new item?");
        ConsoleOut.print("Value: ");
        double value = ConsoleIn.nextDouble();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("How many days before the item reaches expiration?");
        ConsoleOut.print("Shelf life: ");
        int shelfLife = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        Item newItem = new Item(id, name, value, shelfLife);
        ConsoleOut.println("We have the following information for the new item: ");
        ConsoleOut.println(newItem.toString());
        return newItem;
    }
}
