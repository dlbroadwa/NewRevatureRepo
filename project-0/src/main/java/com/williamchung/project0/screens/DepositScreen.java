/**
 * Deposit Screen
 * Prompts user for deposit amount
 * Calls Service to validate user input is a positive number
 * Calls Service to deposit that validated amount
 * Prompts option to deposit more or return to Home Screen
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.UserService;

import java.util.Scanner;

public class DepositScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //Inject scanner and service
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        UserService userService = ((BankAccountApplication) app).getUserService();
        while(true){
            //Start
            System.out.println("Your current balance is $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    "." +
                    "\nHow much would you like to Deposit?"
            );

            //Deposit processing
            String input = scanner.nextLine();
            if (
                input.equals("quit") ||
                input.equals("QUIT") ||
                input.equals("exit") ||
                input.equals("EXIT") ||
                input.equals("cancel") ||
                input.equals("CANCEL")
            ) {
                return new HomeScreen();
            } else {
                //Validate input and then call Service to process deposit
                if (userService.depositValid(input)) {
                    userService.deposit(((BankAccountApplication) app).getUser(), input);
                } else {
                    System.out.println("Invalid deposit amount! Please enter numerical amount above $0.00.");
                    continue;
                }
            }

            //After deposit
            System.out.println("Deposit complete. Your new balance is now $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    "." +
                    "\nWould you like to make another deposit?" +
                    "\n[ Y ] Make another Deposit" +
                    "\n[ N ] Return to Main Menu"
            );
            String input2 = scanner.nextLine();
            if (input2.equals("\\q")) {
                break;
            } else if (input2.equals("Y") || input2.equals("y")) {
                continue;
            } else {
                return new HomeScreen();
            }
        }
        return null;
    }
}