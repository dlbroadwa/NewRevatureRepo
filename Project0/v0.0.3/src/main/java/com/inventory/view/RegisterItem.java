package com.inventory.view;

import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;

public class RegisterItem implements Screen{
    public Item getNew(){
        ConsoleOut.println("You are currently in the process of registering a new Item.");

        ConsoleOut.println("What is the id of the new item?");
        ConsoleOut.print("Item ID: ");
        int id = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the name of the new item?");
        ConsoleOut.print("Item name: ");
        String name = ConsoleIn.nextLine().trim();

        ConsoleOut.println("What is the financial value of the new item?");
        ConsoleOut.print("Item value: ");
        double value = ConsoleIn.nextDouble();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("How many days before the item reaches expiration?");
        ConsoleOut.print("Item shelf life: ");
        short shelfLife = ConsoleIn.nextShort();
        ConsoleIn.nextLine();   //this consumes the carriage return

        Item newItem = new Item(id, name, value, shelfLife);
        ConsoleOut.println("We have the following information for the new item: ");
        ConsoleOut.println(newItem.toString());
        return newItem;
    }
}
