package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;
import java.io.*;

public class EventCreatorScreen implements Screen {
    @Override
    public Screen doScreen(Application app) throws IOException {
        Scanner scanner = ((SchedulingApplication)app).getScanner();
        File allEvents = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\allEvents");
        FileWriter fw = new FileWriter(allEvents, true);
        PrintWriter pw = new PrintWriter(fw);


        while (true) {
            System.out.println("EVENT CREATOR: Enter an event name and time! [to return to previous page type 'back']");
            String newEvent = scanner.nextLine();

            if(newEvent.length() == 0 || newEvent.trim().equals("")) {
                continue;
            } else if(newEvent.equals("back")) {
                return (Screen) new AdminScreen();
            } else {
                pw.println(newEvent);
            }

            pw.close();
            break;
        }
        return (Screen) new AdminScreen();
    }
}

/*
            if(newEvent.length() == 0 || newEvent.trim().equals("")) {
                continue;
            //} else if (newEvent.equals("\\q")) {
                //break;
            } else {
                pw.println(newEvent);
            }

 */