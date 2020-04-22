package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.clients.ScheduleService;
import com.proj.models.Event;
import com.proj.models.Schedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * The UserSchedule class will display all of the events that the user has signed up for.
 * Using the eServices method this class will retrieve all of the events a particular user has signed up for.
 * It will then always return user back to the UserScreen.
 */

public class UserSchedule implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException, SQLException {

         ScheduleService sService = app.getSService();
        List<Schedule> allEvent = sService.getAllEvents();


        System.out.println("HERE ARE ALL YOU EVENTS: ");
        while (true) {
            for (Schedule i : allEvent) {
                System.out.println(" Event: [" + i.getEventID() + "] : " + i.getEventName() + ".");
            }
            return new UserScreen();
        }
    }
}
