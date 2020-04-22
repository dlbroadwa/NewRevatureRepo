package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.models.Event;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


//*****************************************Administrator Main Menu***************************************************//
/**
 * this is the main screen for the administrators
 * it will give the admin an option to change their events or see all people signed up for an event
 */

public class AdminScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws FileNotFoundException, SQLException {
        Scanner scanner = app.getScanner();

        EventServices eService = app.getEService();
        List<Event> allEvent = eService.getAllEvents();


        System.out.println("WELCOME TO THE ADMIN EVENT HANDLER! \n[here are the events you have created] \n");
         for (Event i : allEvent) {
             System.out.println(" Event: [" +i.getEventID() +"] : " + i.getEventName() +".");
        }

        System.out.println("\n What would you like to do? (type number) \n [1] : Edit Events: (change en event) \n [9] : to log out");


        try {

            int adminNumber = scanner.nextInt();
            switch(adminNumber){
                case 1:
                    return new ChangeEvent();
                case 9:
                    return new LoginScreen();
                default:
                    System.out.println("Invalid selection: please try again");
                    return new AdminScreen();
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID OPTION - PLEASE CHOOSE FROM LIST");
            scanner.nextLine();
            return new AdminScreen();
        }
    }
}
