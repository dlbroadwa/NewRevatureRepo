package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.EventServices;
import com.proj.data.EventSQLRepository;
import com.proj.data.Repository;
import com.proj.models.Event;
import com.proj.utils.ConnectionUtils;
import com.proj.utils.PostgresConnectionUtil;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws FileNotFoundException, SQLException {
        Scanner scanner = app.getScanner();

              //**************connects to the AWS RDB**********************//
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://revatureproject-0.cxeo5zs5fqav.us-east-2.rds.amazonaws.com:5432/postgres",
                "johnproj0", "JellyBean13", "revproj0");
        Repository<Event, Integer> eventRepo = new EventSQLRepository(connectionUtils);
        EventServices eService = new EventServices(eventRepo);
        List<Event> allEvent = eService.getAllEvents();


        System.out.println("WELCOME TO THE ADMIN EVENT HANDLER! \n[here are the events you have created] \n");
         for (Event i : allEvent) {
              System.out.println("Event: " + i.getEventName());
        }

        System.out.println("\nWhat would you like to do? (type number) \n 1) Manage Events: (add/delete) \n 2) Check event enrollment lists");


        try {

            int adminNumber = scanner.nextInt();
            switch(adminNumber){
                case 1:
                    return new EventEditor();
                case 2:
                    return new EventEnrollment();
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
