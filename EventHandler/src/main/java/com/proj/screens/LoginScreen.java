package com.proj.screens;

import com.proj.app.EventHandler;

import java.util.Scanner;

//******************************************Login Screen**********************************************//
/**
 * The welcome class is implements the Screen interface:
 * It is the first screen the users will see welcoming them into the application
 * And asking them to log into the application.
 * it will always return the UserVerification screen.
 */

public class LoginScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) {
        Scanner scanner = app.getScanner();

        while (true) {
            System.out.println("WELCOME TO YOUR EVENT HANDLER: \n Enter your username: ");
            String userInput = scanner.nextLine();
            if (userInput.length() == 0 || userInput.trim().equals(""))
                continue;

            System.out.println("Please enter password: ");
            String passInput = scanner.nextLine();
            if (passInput.length() == 0 || passInput.trim().equals(""))
                continue;

            app.setUsername(userInput);
            app.setPassword(passInput);

            return new UserVerification();
        }
    }
}
