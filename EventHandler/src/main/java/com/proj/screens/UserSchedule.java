package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.clients.ScheduleService;
import com.proj.models.Event;
import com.proj.models.Schedule;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
