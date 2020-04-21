package com.inventory.view;

import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Stockpile;

public class ReceiveShipment implements Screen{
    @Override
    public Stockpile getNew() {
        ConsoleOut.println("You are currently in the process of receiving a Warehouse Shipment.");

        ConsoleOut.println("What is the id of the warehouse that is receiving the shipment?");
        ConsoleOut.print("Warehouse ID: ");
        int warehouseId = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the id of the item contained in the shipment?");
        ConsoleOut.print("Item ID: ");
        int itemId = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("How many of the item were contained in the shipment?");
        ConsoleOut.print("Quantity: ");
        int quantity = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        Stockpile newStockpile = new Stockpile(warehouseId, itemId, quantity);
        ConsoleOut.println("We have the following information for the shipment: ");
        ConsoleOut.println(newStockpile.toString());
        return newStockpile;
    }
}
