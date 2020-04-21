package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.clients.ScheduleService;
import com.proj.models.Event;


import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    app.setNewEvent(allEvent.get(eventPick - 1).toString());
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
