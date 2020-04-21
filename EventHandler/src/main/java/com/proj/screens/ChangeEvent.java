package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.models.Event;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//***************************************Change Event Screen For The Administrator*************************************//
/**
 * this class is for the administrator to change the events available.
 * using my events services and connection utilities it will take in user input and update the events table in my AWS RDB
 */

public class ChangeEvent implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException, SQLException {
        Scanner scanner = app.getScanner();
        EventServices eService = app.getEService();
        List<Event> allEvent = eService.getAllEvents();
        EventServices eventService = app.getEService();

        System.out.println("Choose an event number you would like to change. \n [8] : to go back \n [9] : to log out \n");
        for (Event i : allEvent) {
            System.out.println(" Event: [" + i.getEventID() + "] : " + i.getEventName() + ".");
        }

        int newEventNumber = scanner.nextInt();


        switch (newEventNumber) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Enter an event name you would switch to");
                while (true) {
                    String newEvent = scanner.nextLine();
                    if (newEvent.length() == 0 || newEvent.trim().equals(""))
                        continue;

                    eventService.updateEvent(newEvent, newEventNumber);

                    return new AdminScreen();
                }
            case 8:
            case 9:
                return new AdminScreen();

            default:
                System.out.println("Wrong selection enter a valid event number");
                return new ChangeEvent();
        }
    }
}

