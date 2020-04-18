package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.data.EventSQLRepository;
import com.proj.data.Repository;
import com.proj.models.Event;
import com.proj.utils.ConnectionUtils;
import com.proj.utils.PostgresConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EventSignUp implements Screen {


    @Override
    public Screen doScreen(EventHandler app) throws IOException, SQLException {
        //Scanner scanner = app.getScanner();

        //**************connects to the AWS RDB**********************//
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://revatureproject-0.cxeo5zs5fqav.us-east-2.rds.amazonaws.com:5432/postgres",
                "johnproj0", "JellyBean13", "revproj0");


        Repository<Event, Integer> eventRepo = new EventSQLRepository(connectionUtils);
        EventServices eService = new EventServices(eventRepo);
        List<Event> allEvent = eService.getAllEvents();


        System.out.println("WELCOME TO THE EVENT SIGN UP PAGE: \n [here are a list of classes you may sign up for]");
        for (Event i : allEvent) {
            System.out.println("Event: " + i.getEventName());
        }

        return null;
    }
}
