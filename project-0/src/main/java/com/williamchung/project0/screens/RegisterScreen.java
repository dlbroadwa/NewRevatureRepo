/**
 * Registration Screen
 * Takes username, users Service to check if Username is taken by finding by and checking that it's null.
 * Also checks for length.
 * Takes password, checks for length.
 * Calls on Service with addNewUser to insert new user into database.
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.UserService;

import java.util.Scanner;

public class RegisterScreen implements Screen {

    @Override
    public Screen doScreen(Application app) {
        //Inject Scanner and Service in every Screen
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        UserService userService = ((BankAccountApplication) app).getUserService();

        while(true){
            System.out.println("To register, please enter your desired username. (Max 20 characters)");
            String inputUsername = scanner.nextLine();
            if (userService.getUserByUsername(inputUsername) != null) {
                System.out.println("Username is already taken. Please choose a different username");
                continue;
            } else if (inputUsername.length() > 20 || inputUsername.length() < 1){
                System.out.println("Username must be between 0 and 20 characters. Please choose a different username.");
                continue;
            } else {
                while(true){
                    System.out.println("Please set a password. (Max 20 characters)");
                    String inputPassword = scanner.nextLine();
                    if (inputPassword.length() > 20 || inputPassword.length() < 1) {
                        System.out.println("Password must be between 0 and 20 characters. Please set a different password.");
                        continue;
                    } else {
                        userService.addNewUser(inputUsername, inputPassword);
                        System.out.println("Your account has been created. Please log in to continue.");
                        return new LoginScreen();
                    }
                }
            }
        }
    }
}
