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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventSignUp implements Screen {


    @Override
    public Screen doScreen(EventHandler app) throws SQLException {
        Scanner scanner = app.getScanner();
        EventServices eService = app.getEService();
        List <Event> allEvent = eService.getAllEvents();

        System.out.println("WELCOME TO THE EVENT SIGN UP PAGE: \n [Pick an event number you wish to sign up for] \n [8] : to go back \n [9] : to log out \n");
        for (Event i : allEvent) {
            System.out.println(" Event: [" +i.getEventID() +"] : " + i.getEventName() +".");
        }

        int evenPick = scanner.nextInt();
        switch(evenPick) {
            case 1:
                //do code here
                break;
            case 2:
                //do code here
            case 3:
                //do code here
            case 4:
                //do code here
            case 5:
                //do code here
            case 8:
                return new UserScreen();
            case 9:
                return new LoginScreen();
            default:
                System.out.println("Invalid selection: please try again");
                return new EventSignUp();
            }
        return new UserScreen();
    }
}
