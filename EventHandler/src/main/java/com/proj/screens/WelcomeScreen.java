package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The welcome class is implements the Screen interface:
 * It is the first screen the users will see welcoming them into the application
 * And asking them to log into the application.
 * it will always return the UserVerification screen.
 */


public class WelcomeScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException {
        Scanner scanner = app.getScanner();
        System.out.println("WELCOME TO THE EVENT HANDLER: [are you a new user? (y/n)]");


        try {

            String welcomeUser = scanner.nextLine();
            switch(welcomeUser){
                case "y":
                    return new NewUserScreen();
                case "n":
                    return new LoginScreen();
                default:
                    System.out.println("INVALID OPTION - type 'y' or 'n' ");
                    return new WelcomeScreen();
            }
        } catch (InputMismatchException e) {
            System.out.println("Error");
            scanner.nextLine();
            return null;
        }
    }
}
