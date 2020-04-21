package com.company.Validation;
/**
 * Validation service that verifies user inputs
 */

import java.util.Scanner;

import com.company.Banking.BankCustomer;
public class Validate {
    public BankCustomer currentCustomer = new BankCustomer();

    /**
     * Verify if user input is a valid int
     * @return
     */
    public int checkInt(){
        Scanner sc = new Scanner(System.in);
        int number;
        do {

            while (!sc.hasNextInt()) {
                System.out.println("That's not a valid number!");
                sc.next();
            }
            number = sc.nextInt();

        } while (number <= 0);
        return number;
    }

    /**
     * Takes user input and verifies if it is a double
     * @return valid double
     */
    public double checkDouble(){

        Scanner sc = new Scanner(System.in);
        double number;

        do {
            while (!sc.hasNextDouble()) {
                System.out.println("That's not a valid number!");
                sc.next(); // this is important!
            }

            number = sc.nextDouble();

        } while (number <= 0);
        return number;

    }

    /**
     * Verifies if the amount withdrawn isnt more than what is in the account
     * @param amountWithdrawn amount wanted to withdraw
     * @param amountInAccount amount in account
     * @return boolean if the amount is valid
     */
    public boolean isWithdrawable(double amountWithdrawn, double amountInAccount)
    {
        if(amountInAccount - amountWithdrawn < 0)
        {
            return false;
        }

        return true;
    }

}
