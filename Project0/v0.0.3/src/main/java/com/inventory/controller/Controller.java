package com.inventory.controller;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.DistributionCenterCRUD;
import com.inventory.controller.services.data.ItemCRUD;
import com.inventory.controller.services.data.StockpileCRUD;
import com.inventory.controller.services.data.WarehouseCRUD;
import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Stockpile;
import com.inventory.view.ReceiveShipment;
import com.inventory.view.RegisterDC;
import com.inventory.view.RegisterItem;
import com.inventory.view.RegisterWarehouse;
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
    private void run() {
        boolean userExits = false;

        while(!userExits){
            ConsoleOut.println("What would you like to do today? Please enter just the corresponding number and press your enter key to begin.");
            ConsoleOut.println("You may 0: Exit this program. 1: Register a new warehouse. 2: Register a new item. " +
                    "3: Receive a shipment at a warehouse. 4: Register a new distribution center. ");

            int userChoice = getInt();

            switch (userChoice){
                case 0:
                    ConsoleOut.println("Ok. Exiting the program.");
                    userExits = true;
                    break;
                case 1:
                    try {
                        new WarehouseCRUD().create(0, new RegisterWarehouse().getNew());
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 2:
                    try {
                        new ItemCRUD().create(0, new RegisterItem().getNew());
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                case 3:
                    try {
                        List<Stockpile> current= new StockpileCRUD().read(0);
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
                        new DistributionCenterCRUD().create(0, new RegisterDC().getNew());
                    } catch (SQLException e) {
                        ConsoleOut.println(ERR_WRT);
                    }
                    break;
                default:
                    ConsoleOut.println("Invalid Input. Please enter just an integer number corresponding to one of the options shown above.");
            }
        }
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
                return Integer.parseInt(ConsoleIn.next().substring(0, 1));
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
            ConsoleOut.println("Established db connection.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to db. Program ends.");
            System.exit(1);
        }
    }
}
