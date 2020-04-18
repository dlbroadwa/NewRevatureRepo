package com.proj.screens;

import com.proj.app.EventHandler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) {
        Scanner scanner = app.getScanner();

        System.out.println("WELCOME TO THE USER EVENT PORTAL!");
        System.out.println("What would you like to do? [type number] \n 1) Sign up for an event \n 2) Check on your events");


        try {

            int adminNumber = scanner.nextInt();
            switch(adminNumber){
                case 1:
                    return new EventSignUp();
                case 2:
                    return new EventSchedule();
                default:
                    System.out.println("Invalid selection: please try again \n");
                    return new UserScreen();
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return new AdminScreen();
        }
    }
}