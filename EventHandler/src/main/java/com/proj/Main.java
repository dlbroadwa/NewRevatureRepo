package com.proj;


import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.data.EventSQLRepository;
import com.proj.data.Repository;
import com.proj.models.Event;
import com.proj.utils.ConnectionUtils;
import com.proj.utils.PostgresConnectionUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
       // EventHandler app = new EventHandler();
       // app.run();//****************     Connect to AWS_RDS     ******************//
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://revatureproject-0.cxeo5zs5fqav.us-east-2.rds.amazonaws.com:5432/postgres",
                "johnproj0", "JellyBean13", "revproj0");


        Repository<Event, Integer> eventRepo = new EventSQLRepository(connectionUtils);

        EventServices eService = new EventServices(eventRepo);

        List<Event> allEvent = eService.getAllEvents();


        for (Event i : allEvent) {
            System.out.println("Event " + i.getEventName());
        }
    }
}
