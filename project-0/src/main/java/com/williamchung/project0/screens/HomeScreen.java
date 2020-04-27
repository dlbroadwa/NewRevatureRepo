/**
 * Home Screen
 * Routes user to one of three functional screens: Deposit, Withdrawal, Transfer
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;

import java.util.Scanner;

public class HomeScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        //Inject Scanner. Service not used in this one.
        Scanner scanner = ((BankAccountApplication) app).getScanner();

        while(true){
            System.out.println("Your current balance is $" +
                    String.format("%.2f", ((BankAccountApplication) app).getUser().getCheckingBalance()) +
                    ". Please make a selection:" +
                    "\n[ 1 ] Deposit to account" +
                    "\n[ 2 ] Withdraw from account" +
                    "\n[ 3 ] Transfer to another person's account"
            );
            String input = scanner.nextLine();

            if (input.equals("\\q")) {
                break;
            } else if (input.equals("1")) {
                return new DepositScreen();
            } else if (input.equals("2")) {
                return new WithdrawalScreen();
            } else if (input.equals("3")) {
                return new TransferScreen();
            } else {
                System.out.println("Please enter a valid input");
                continue;
            }
        }
        return null;
    }
}
