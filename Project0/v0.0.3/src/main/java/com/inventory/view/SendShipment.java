package com.inventory.view;

import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.*;

import java.time.LocalDate;

public class SendShipment {
    public Shipment getNew(){
        //I need to get the id of the DcOrder being created on this screen
        ConsoleOut.println("You are currently in the process of sending a shipment from a warehouse to a distribution center.");

        ConsoleOut.println("What is the id of this new shipment order?");
        ConsoleOut.print("ID: ");
        int dcOrderId = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the id of the distribution center receiving this shipment?");
        ConsoleOut.print("ID: ");
        int dcId = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the id of the item being sent?");
        ConsoleOut.print("ID: ");
        int itemId = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("How many of that item are you sending?");
        ConsoleOut.print("Quantity: ");
        int quantity = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("What is the id of the warehouse that contains those items?");
        ConsoleOut.print("ID: ");
        int warehouseId = ConsoleIn.nextInt();
        ConsoleIn.nextLine();   //this consumes the carriage return

        LocalDate date = LocalDate.now();

        Stockpile stockpile = new Stockpile(warehouseId, itemId, quantity);
        DcOrder dcOrder = new DcOrder(warehouseId, dcId, date, dcOrderId);
        DcOrderItems dcOrderItems = new DcOrderItems(dcOrderId, itemId, quantity);
        Shipment shipment = new Shipment(stockpile, dcOrder, dcOrderItems);

        ConsoleOut.println("We have the following information for this shipment: ");
        ConsoleOut.println(shipment.toString());

        return shipment;
    }
}
