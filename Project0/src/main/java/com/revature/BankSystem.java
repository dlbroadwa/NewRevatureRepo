package com.revature;

import MenuUtilities.ATMScreen;
import MenuUtilities.ObtainUserIdScreen;
import MenuUtilities.WelcomeScreen;

import java.util.InputMismatchException;
import java.util.LinkedList;

public class BankSystem {

    public static boolean userInitComplete = false;
    public static LinkedList <User> allBankUsers;

    public static void loadUsers(){
        BankUserDAO allUsers = new BankUserFileOperations();
        allBankUsers = allUsers.getAllUser();

    }

    public static void main(String[] args) {

        //Initialization of current user list
        loadUsers();

        boolean isValidScreenInput = false;
        boolean isValidAtmInput = false;
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.printInitialMenu();

        User currentUser ;
        while (true) {
            try {
                isValidScreenInput = welcomeScreen.getUserInputValue();
                if (isValidScreenInput) {

                    if (welcomeScreen.getChoice() == 1) {
                        System.out.println("proceed to next screen");

                        ObtainUserIdScreen obtainUserIdScreen = new ObtainUserIdScreen();
                        obtainUserIdScreen.printScreen();
                        obtainUserIdScreen.scanCustomerIdInput();


                        boolean userFound = false;
                        int count = 0;
                        while (true){
                            for (User u : allBankUsers){
                                if (obtainUserIdScreen.getCustomerId().equals(u.getUser_id())){
                                    System.out.println("Found user!\nProceed to next screen");
                                    currentUser = u;
                                    ATMScreen atmScreen = new ATMScreen();

                                    atmScreen.printMenu();

                                    while (true){

                                        try {
                                            isValidAtmInput = atmScreen.getUserInputValue();

                                            if (isValidAtmInput){
                                                System.out.println("Valid ATM input");
                                            }

                                        } catch (InputMismatchException e) {
                                            e.printStackTrace();
                                        }

                                        break;
                                    }

                                    userFound = true;
                                    break;
                                }
                            }
                            count++;
                            if (count == 3 && !userFound){
                                System.out.println("Incorrect Id entered 3 times.  Exiting program!");
                                break;
                            }
                            if (userFound){
                                break;
                            }
                            System.out.println("Incorrect User ID!!!");
                            obtainUserIdScreen = new ObtainUserIdScreen();
                            obtainUserIdScreen.printScreen();
                            obtainUserIdScreen.scanCustomerIdInput();
                        }


                        //temporary break from program
                        break;
                    }
                    else{

                        break;
                    }
                } else {
                    welcomeScreen = new WelcomeScreen();
                    System.out.println("Please enter correct entry!");
                    welcomeScreen.printInitialMenu();

                }
            } catch (InputMismatchException e) {
                welcomeScreen = new WelcomeScreen();
                System.out.println("Please enter correct entry!");
                welcomeScreen.printInitialMenu();
            }

        }//end while loop


        System.out.println("Thank you for choosing our bank!");
        System.out.println("\nExitting...");

    }
}
