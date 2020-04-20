package com.inventory.controller;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.WarehouseCRUD;
import com.inventory.controller.system.ConsoleIn;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.view.RegisterWarehouse;

import java.sql.SQLException;

public class Controller {
    public Controller() {
        establishDBConnection();
        run();
    }

    private void run() {
        boolean userExits = false;

        while(!userExits){
            ConsoleOut.println("What would you like to do today? Please enter just the corresponding number and press your enter key to begin.");
            ConsoleOut.println("You may 0: Exit this program. 1: Register a warehouse.");

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
                        ConsoleOut.println("Error writing to the online database. Returning you to the main menu.");
                    }
                    break;
                default:
                    ConsoleOut.println("Invalid Input. Please enter just an integer number corresponding to one of the options shown above.");
            }
        }
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
