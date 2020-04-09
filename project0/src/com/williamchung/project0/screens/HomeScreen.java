package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;

import java.util.Scanner;

public class HomeScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BankAccountApplication) app).getScanner();

        while(true){
            System.out.println("Your current balance is " + "$100" + ". Please make a selection:" +
                    "\n[ 1 ] Deposit to account" +
                    "\n[ 2 ] Withdraw from account" +
                    "\n[ 3 ] Transfer to different account"
            );
            String input = scanner.nextLine();

            if (input.equals("\\q")) {
                break;
            } else if (input.equals("1")) {
                return new DepositScreen();
            } else if (input.equals("2")) {
                return new WithdrawlScreen();
            } else if (input.equals("3")) {
                return new TransferScreen();
            } else {
                System.out.println("Please try a valid input");
                continue;
            }
        }
        return null;
    }
}
