/**
 * Withdrawal Screen
 * Prompts user for withdrawal amount
 * Calls Service to validate user input is a positive number less than current balance
 * Calls Service to withdraw that validated amount
 * Prompts option to withdraw more if balance is still over 0, or return to Home Screen
 */

package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.UserService;

import java.util.Scanner;

public class WithdrawalScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        UserService userService = ((BankAccountApplication) app).getUserService();

        while(true){
            //Start
            System.out.println("Your current balance is $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    "."
            );
            //If account balance is 0, bounce them to Home Screen.
            if (((BankAccountApplication) app).getUser().getCheckingBalance() == 0.00){
                System.out.println("You cannot make a withdrawal. Please deposit money first.");
                return new HomeScreen();
            } else {
                System.out.println("\nHow much would you like to Withdraw?");
            }

            //Withdrawal processing
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
                //Validate input is positive number less than current balance, then call Service to process withdrawal
                if (userService.withdrawalValid(input, ((BankAccountApplication) app).getUser())) {
                    userService.withdraw(((BankAccountApplication) app).getUser(), input);
                } else {
                    System.out.println(
                        "Invalid Withdrawal amount! Please enter numerical amount below your balance of " +
                        String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                        "."
                    );
                    continue;
                }
            }

            //After withdrawal
            System.out.println("Withdrawal complete. Your new balance is now $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    "." +
                    "\nWould you like to make another withdrawal?" +
                    "\n[ Y ] Make another Withdrawal" +
                    "\n[ N ] Return to Main Menu"
            );
            String input2 = scanner.nextLine();
            if (input2.equals("\\q")) {
                break;
            } else if (input2.equals("Y") || input2.equals("y")) {
                if (((BankAccountApplication) app).getUser().getCheckingBalance() == 0.00){
                    System.out.println("You cannot make a withdrawal. Please deposit money first.");
                    return new HomeScreen();
                } else {
                    continue;
                }
            } else {
                return new HomeScreen();
            }
        }
        return null;
    }
}
