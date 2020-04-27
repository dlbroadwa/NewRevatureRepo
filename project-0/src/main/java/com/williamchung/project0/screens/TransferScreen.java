/**
 * Transfer Screen
 * takes input for amount and recipient username
 * calls service to validate both
 * if validated, calls service to process transfer
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.UserService;

import java.util.Scanner;

public class TransferScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //Inject Scanner and Service
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        UserService userService = ((BankAccountApplication) app).getUserService();
        while(true){
            //Start
            System.out.println("Your current balance is $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    "."
            );
            //If balance is 0, bounce them back Home.
            if (((BankAccountApplication) app).getUser().getCheckingBalance() == 0.00){
                System.out.println("You cannot make a transfer. Please deposit money first.");
                return new HomeScreen();
            }


            //Transfer Amount and Recipient scanning
            System.out.println("\nHow much would you like to Transfer?");
            String inputAmount = scanner.nextLine();
            if (
                inputAmount.equals("quit") ||
                inputAmount.equals("QUIT") ||
                inputAmount.equals("exit") ||
                inputAmount.equals("EXIT") ||
                inputAmount.equals("cancel") ||
                inputAmount.equals("CANCEL")
                ) {
                return new HomeScreen();
            }
            System.out.println("\nWho would you like to Transfer to?");
            String inputRecipient = scanner.nextLine();
            if (
                inputRecipient.equals("quit") ||
                inputRecipient.equals("QUIT") ||
                inputRecipient.equals("exit") ||
                inputRecipient.equals("EXIT") ||
                inputRecipient.equals("cancel") ||
                inputRecipient.equals("CANCEL")
                ) {
                return new HomeScreen();
            }


            //Call Service to validate then process transfer
            if ( userService.transferValid(inputAmount, ((BankAccountApplication) app).getUser(), inputRecipient)) {
                userService.transfer(((BankAccountApplication) app).getUser(), inputAmount, inputRecipient);
            } else {
                System.out.println(
                    "Transfer incomplete. Invalid transfer amount or recipient username." +
                    "\nPlease try again."
                );
                continue;
            }

            //After withdrawal
            System.out.println("Transfer complete. Your new balance is now $" +
                String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                "." +
                "\nWould you like to make another transfer?" +
                "\n[ Y ] Make another Transfer" +
                "\n[ N ] Return to Main Menu"
            );
            String input2 = scanner.nextLine();
            if (input2.equals("\\q")) {
                break;
            } else if (input2.equals("Y") || input2.equals("y")) {
                if (((BankAccountApplication) app).getUser().getCheckingBalance() == 0.00){
                    System.out.println("You cannot make a transfer. Please deposit money first.");
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
