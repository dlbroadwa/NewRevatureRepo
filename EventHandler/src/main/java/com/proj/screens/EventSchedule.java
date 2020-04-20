package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventSchedule implements Screen {
    @Override
    public Screen doScreen(EventHandler app) {
        Scanner scanner = app.getScanner();
        System.out.println("Here is a list of all your events \n [8] go back \n [9] log out");

        try {

            int scan = scanner.nextInt();
            switch(scan){
                case 8:
                    return new UserScreen();
                case 9:
                    return new LoginScreen();
                default:
                    System.out.println("Invalid selection: please try again");
                    return new AdminScreen();
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR");
            scanner.nextLine();
            return new AdminScreen();
        }
    }
}
