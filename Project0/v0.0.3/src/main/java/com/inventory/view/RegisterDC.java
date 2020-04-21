package com.inventory.view;

import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.DistributionCenter;

public class RegisterDC implements Screen{
    @Override
    public DistributionCenter getNew(){
        ConsoleOut.println("You are currently in the process of registering a new Distribution Center.");
        ConsoleOut.println("What is the id of the new distribution center?");
        ConsoleOut.print("ID: ");
        int id = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        ConsoleOut.println("In what state does this new distribution center reside?");
        ConsoleOut.print("State: ");
        String state = consoleIn.nextLine().trim();

        ConsoleOut.println("Which city?");
        ConsoleOut.print("City: ");
        String city = consoleIn.nextLine().trim();

        ConsoleOut.println("Street address?");
        ConsoleOut.print("Address: ");
        String address = consoleIn.nextLine().trim();

        ConsoleOut.println("Zip code?");
        ConsoleOut.print("Zip: ");
        int zipCode = consoleIn.nextInt();

        DistributionCenter distributionCenter = new DistributionCenter(id, state, city, address, zipCode);
        ConsoleOut.println("We have the following information for the new warehouse: ");
        ConsoleOut.println(distributionCenter.toString());
        return distributionCenter;
    }

    public DistributionCenter getNewWithId(int id){
        ConsoleOut.println("You are currently in the process of registering a new Distribution Center.");

        ConsoleOut.println("In what state does this new distribution center reside?");
        ConsoleOut.print("State: ");
        String state = consoleIn.nextLine().trim();

        ConsoleOut.println("Which city?");
        ConsoleOut.print("City: ");
        String city = consoleIn.nextLine().trim();

        ConsoleOut.println("Street address?");
        ConsoleOut.print("Address: ");
        String address = consoleIn.nextLine().trim();

        ConsoleOut.println("Zip code?");
        ConsoleOut.print("Zip: ");
        int zipCode = consoleIn.nextInt();

        DistributionCenter distributionCenter = new DistributionCenter(id, state, city, address, zipCode);
        ConsoleOut.println("We have the following information for the new warehouse: ");
        ConsoleOut.println(distributionCenter.toString());
        return distributionCenter;
    }
}
