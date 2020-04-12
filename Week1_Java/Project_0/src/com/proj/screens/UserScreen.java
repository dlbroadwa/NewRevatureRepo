package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;

public class UserScreen implements Screen {


    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((SchedulingApplication)app).getScanner();

        System.out.println("WELCOME: \n What would you like to do? (type number) \n 1) Sign up for an event \n 2) Check your event schedule");
        int userNumber = scanner.nextInt();

        while (true) {
            if (userNumber == 1) {
                return (Screen) new EventSignUpScreen();
            } else if (userNumber == 2) {
                return (Screen) new EventScheduleScreen();
            } else {
                System.out.println("Not a valid input: try again");
                break;
            }
        }
        return null;
    }
}
