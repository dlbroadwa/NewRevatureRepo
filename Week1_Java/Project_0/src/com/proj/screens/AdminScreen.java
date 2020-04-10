package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;

public class AdminScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((SchedulingApplication)app).getScanner();

        System.out.println("Welcome fellow administrator: \n What would you like to do? (type number) \n 1) Setup available class times \n 2) Check enrollment");
        int adminNumber = scanner.nextInt();

        while (true) {
            if (adminNumber == 1) {
                return (Screen) new SetScheduleScreen();
            } else if (adminNumber == 2) {
                return new CheckEnrollmentScreen();
            } else {
                System.out.println("Not a valid input: try again");
                break;
            }
        }


        return null;
    }
}
