package com.proj.screens;

import com.proj.app.EventHandler;

import java.util.InputMismatchException;
import java.util.Scanner;


//********************************************User Main Menu***********************************************************//
/**
 * this class is for users only it will diplays a menu for users to choose what they wish to do next
 * it will display options for the user, then using the scanner it will send the user to the appropriate next screen
 * depending on the user input this screen will return an EventSignUp, UserSchedule, or back to Login
 */

public class UserScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) {
        Scanner scanner = app.getScanner();

        System.out.println("WELCOME TO YOUR EVENT PORTAL! \n");
        System.out.println("What would you like to do? [type number] " +
                "\n [1] Sign up for an event \n [2] Check on your events  \n [9] log out");


        try {
            int adminNumber = scanner.nextInt();
            switch(adminNumber){
                case 1:
                    return new EventSignUp();
                case 2:
                    return new UserSchedule();
                case 9:
                    return new LoginScreen();
                default:
                    System.out.println("Invalid selection: please try again \n");
                    return new UserScreen();
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return new UserScreen();
        }
    }
}