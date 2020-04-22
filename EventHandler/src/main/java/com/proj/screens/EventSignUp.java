package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.clients.ScheduleService;
import com.proj.models.Event;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The EventSignUp class uses EventService class to first retrieve all events created by admin.
 * It then takes user input through a scanner and uses the ScheduleServices to update the users events table in my AWS RDB
 * According to user input the screen will go back a page log out or go back to UserScree.
 */
public class EventSignUp implements Screen {


    @Override
    public Screen doScreen(EventHandler app) throws SQLException {
        while (true) {
            Scanner scanner = app.getScanner();
            ScheduleService sService = app.getSService();
            EventServices eService = app.getEService();
            ArrayList<Event> allEvent = (ArrayList<Event>) eService.getAllEvents();

            System.out.println("WELCOME TO THE EVENT SIGN UP PAGE: \n [Pick an event number you wish to sign up for] \n [8] : to go back \n [9] : to log out \n");
            for (Event i : allEvent) {
                System.out.println(" Event: [" + i.getEventID() + "] : " + i.getEventName() + ".");
            }


            int eventPick = scanner.nextInt();
            switch (eventPick) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    String eventName = allEvent.get(eventPick -1).toString();
                    app.setNewEvent(eventName);
                    System.out.println("you signed up for " + eventName + ", nice choice!");
                    sService.updateSchedule(app.getNewEvent(), app.getUsername());
                    return new UserScreen();
                case 8:
                    return new UserScreen();
                case 9:
                    return new LoginScreen();
                default:
                    System.out.println("Invalid selection: please try again");
                    return new EventSignUp();
            }
        }
    }
}
