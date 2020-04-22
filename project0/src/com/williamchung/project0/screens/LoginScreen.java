package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.LoginService;

import java.sql.Connection;
import java.util.Scanner;

public class LoginScreen implements Screen {

    private LoginService loginService = new LoginService();

    //This is for when I fix MySQL connection
//    private final Connection connection;
//
//    public LoginScreen(Connection connection) {
//        this.connection = connection;
//    }

    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        int threeLoginAttempts = 0;
        while(threeLoginAttempts < 3){
            //Take input with Scanner
            System.out.println("Please enter Username");
            String username = scanner.nextLine();
            if(username.equals("\\q")){
                break;
            }
            System.out.println("Please enter 4-digit PIN");
            String pin = scanner.nextLine();
            if(pin.equals("\\q")) {
                break;
            }

            //Authenticate user with LoginService
            if(loginService.authenticateUser(username, pin)){ //Hardwired true for now

//                (BankAccountApplication) app.setCurrentUser(user);
                return new HomeScreen();
            } else {
                threeLoginAttempts++;
                System.out.println("Login Invalid. Please try again. Attempt #" + threeLoginAttempts);
                continue;
                //Do not display whether Username or PIN was invalid
            }
        }
        System.out.println("Too many unsuccessful attempts. Please try again later.");
        return null;
    }
}