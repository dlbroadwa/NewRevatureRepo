package com.revature;

import MenuUtilities.WelcomeScreen;

import java.util.InputMismatchException;

public class BankSystem {


    void printMainAccountScreen(){
        System.out.println("Welcome to Bank of America:");
        System.out.println("Available options are: ");
        System.out.println("{1} Withdrawal");
        System.out.println("{2} Deposit");
        System.out.println("{3} Checking balances");
        System.out.println("{4} Set Preferences");
    }


    public static void main(String[] args) {

        boolean isValidScreenInput = false;

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.printMenu();
        while (!isValidScreenInput) {
            try {
                isValidScreenInput = welcomeScreen.getUserInputValue();
                if (isValidScreenInput){
                    if (welcomeScreen.getChoice() == 1){
                        System.out.println("Go to next screen");
                    }
                    else {
                        break;
                    }
                }
                else{
                    System.out.println("Please enter correct choice!");
                    welcomeScreen.printMenu();

                }
            } catch (InputMismatchException e) {
                e.printStackTrace();
                System.out.println("Please enter correct choice!");
                welcomeScreen.printMenu();
            }


        }//end while loop
        System.out.println("\n");

    }
}
