package com.inventory.controller;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.*;
import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Shipment;
import com.inventory.model.Stockpile;
import com.inventory.view.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.List;

public class Controller {
    public Controller() {
        establishDBConnection();
        run();
    }

    private static final String ERR_WRT = "Error writing to the online database. Returning you to the main menu.";
    private ConsoleIn consoleIn = ConsoleIn.getInstance();

    private void run() {
        boolean userExits = false;

        while(!userExits){
            ConsoleOut.println("Welcome to the Inventory Assistant terminal. What would you like to do today? Please enter just the corresponding number and press your enter key to begin.");
            ConsoleOut.println("You may: \n0: Exit this program. \n1: Register a new warehouse. \n2: Register a new item. " +
                    "\n3: Receive a shipment at a warehouse. " +
                    "\n4: Register a new distribution center. " +
                    "\n5: Send a new shipment from a warehouse to a distribution center.");

            int userChoice = getInt();

            switch (userChoice){
                case 0:
                    ConsoleOut.println("Ok. Exiting the program.");
                    userExits = true;
                    break;
                case 1:
                    try {
                        int newId = new WarehouseCRUD().getNextId(0);
                        new WarehouseCRUD().create(0, new RegisterWarehouse().getNewWithId(newId));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 2:
                    try {
                        int newId = new ItemCRUD().getNextId(0);
                        new ItemCRUD().create(0, new RegisterItem().getNewWithId(newId));
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 3:
                    try {
                        List<Stockpile> current = new StockpileCRUD().readAll(0);
                        Stockpile newStock = new ReceiveShipment().getNew();
                        Stockpile existingStockpile = duplicateStockpile(current, newStock);
                        if(existingStockpile == null){
                            new StockpileCRUD().create(0, newStock);
                        }
                        else{
                            Stockpile newQuantity = new Stockpile(newStock.getWarehouseId(), newStock.getItemId(),
                                    existingStockpile.getQuantity() + newStock.getQuantity());
                            new StockpileCRUD().update(0, existingStockpile, newQuantity);
                        }
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 4:
                    try {
                        int newId = new DistributionCenterCRUD().getNextId(0);
                        new DistributionCenterCRUD().create(0, new RegisterDC().getNewWithId(newId));
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 5:
                    try {
                        int newId = new DcOrderCRUD().getNextId(0);
                        Shipment shipment = new SendShipment().getNewWithId(newId);
                        updateStockpile(shipment);
                        new DcOrderCRUD().create(0, shipment.getDcOrder());
                        new DcOrderItemsCRUD().create(0, shipment.getDcOrderItems());
                        ConsoleOut.println("Your shipment has been sent.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                default:
                    ConsoleOut.println("Invalid Input. Please enter just an integer number corresponding to one of the options shown above.");
            }
        }
    }

    private void updateStockpile(@NotNull Shipment shipment) throws SQLException {
        Stockpile oldStockpile = new StockpileCRUD().read(0,
                shipment.getStockpile().getWarehouseId(), shipment.getStockpile().getItemId());
        int stockpileQuantity = oldStockpile.getQuantity();
        stockpileQuantity -= shipment.getStockpile().getQuantity();
        Stockpile updatedStockpile = new Stockpile(shipment.getStockpile().getWarehouseId(),
                shipment.getStockpile().getItemId(), stockpileQuantity);
        new StockpileCRUD().update(0, oldStockpile, updatedStockpile);
    }

    @Nullable
    private Stockpile duplicateStockpile(@NotNull List<Stockpile> stockpiles, Stockpile newStockpile){
        for(Stockpile s: stockpiles){
            if((s.getItemId() == newStockpile.getItemId()) && (s.getWarehouseId() == newStockpile.getWarehouseId())){
                return s;
            }
        }
        return null;
    }

    private int getInt(){
        while(true){
            try{
                return Integer.parseInt(consoleIn.nextLine().substring(0, 1));
            }
            catch(Exception e){
                ConsoleOut.println("Invalid Input. Please enter just an integer number.");
            }
        }
    }

    private void establishDBConnection(){
        try {
            PostgresSQLService.addDBConnection("jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/db1",
                    "davide", "jw8s0F4");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to db. Program ends.");
            System.exit(1);
        }
    }
}
