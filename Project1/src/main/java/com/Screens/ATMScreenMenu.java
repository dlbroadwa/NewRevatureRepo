package com.Screens;

import com.model.Accounts;
import com.model.Users;

import java.util.Scanner;

/**
 * Class description: This class will provide the user with four option to choose from.
 * The class will also check for proper Integer input from user and force the user to only enter values from 1 to 4.
 * If user chooses incorrectly five times, the program will exit
 */
public class ATMScreenMenu {

    public int getUserMenuOption() {
        return userMenuOption;
    }

    public void setUserMenuOption(int userMenuOption) {
        this.userMenuOption = userMenuOption;
    }

    private int userMenuOption;

    public ATMScreenMenu (){
        this.userMenuOption = -1;
    }

    public void printATMMenuScreen(Users user){
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::");
        System.out.println("Welcome to Bank Of America ATM Service:");
        System.out.println(user.getEmail_address() + " is logged in");
        System.out.println("------User detail------");
        System.out.println("User Name: " + user.getName() + ".------User Email: " + user.getEmail_address() + ".------User Phone number: " + user.getPhone_number() + ".");
    }

    /**
     * This method will print out the user profile status.
     * @param account is the user account profile which will include the current account balance
     */
    public void printUserAccount(Accounts account){
        System.out.println("Account holder: " + account.getHolder().getEmail_address() + ".------Balance: $" +
                account.getBalance() + ".------Account Type: " + account.getAccountType() + ".------Account ID: " + account.getAccount_id());
    }

    /**
     * User will need to choose from one of these four choices.
     */
    private void printATMOptions(){
        System.out.println("{1}  Withdraw");
        System.out.println("{2}  Deposit");
        System.out.println("{3}  Review past transactions");
        System.out.println("{4}  Exit");
    }

    /**
     * This method will present user with the ATM menu screen, and will do input validation
     * Integer values between 1-4 are accepted, others are rejected.
     */
    public void printUserOptions(){
        printATMOptions();
        System.out.print("Please enter your choice: ");
        int number;
        int count = 0;
        Scanner sc = new Scanner(System.in);

        do {

            while (!sc.hasNextInt()) {
                System.out.println("That is not a number!");
                sc.next();
                printATMOptions();
                System.out.print("Please enter your choice: ");
                System.out.println();
                count++;
                if (count > 4 ){
                    break;
                }
            }

            if (count > 4){
                this.userMenuOption = 4;
                System.out.println("Incorrect input " + count + " times.  Exiting program.");
                return;
            }
            count++;
            number = sc.nextInt();
            if (number == 1 || number == 2 || number == 3 || number == 4 ) {
                break;
            }

            printATMOptions();
            System.out.println("Please enter choice from 1 to 4:");
        } while (number != 1 || number != 2 || number != 3 || number != 4);

        this.userMenuOption = number;
    }
}
