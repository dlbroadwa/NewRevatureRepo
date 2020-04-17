package com.company.Validation;

import java.util.Scanner;

import com.company.Banking.BankCustomer;
public class Validate {
    public BankCustomer currentCustomer = new BankCustomer();

    public int checkInt(){

        Scanner sc = new Scanner(System.in);
        int number;

        do {

            while (!sc.hasNextInt()) {
                System.out.println("That's not a valid number!");
                sc.next(); // this is important!
            }

            number = sc.nextInt();

        } while (number <= 0);
        return number;

    }

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

    public boolean verifyAmount(double amountInAccount, double specifiedAmount)
    {
        if(amountInAccount - specifiedAmount < 0)
        {
            return false;
        }

        return true;
    }

}
