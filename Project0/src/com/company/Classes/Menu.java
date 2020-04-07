package com.company.Classes;

import Validation.Validate;

import java.util.Scanner;

public class Menu {
    Validate validation = new Validate();
    BankCustomer customer = new BankCustomer();

    public void runMenu(){

        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Hello Customer! Please enter a valid number.\n");
            System.out.println("1. Sign In");
            System.out.println("2. Exit");
            number = validation.checkInt();

            switch(number)
            {
                case 1:
                    boolean doesUserExist = true;

                    System.out.println("Please Enter Username: ");
                    String userName = sc.nextLine();
                    System.out.println("Please Enter Password: ");
                    String passWord = sc.nextLine();

                    if(!(doesUserExist = validation.checkUserExist(userName, passWord)))
                    {
                        break;
                    }
                    else {
                        System.out.println("User Successfully Logged in!");

                        do {
                            System.out.println("Hello Customer! Please enter a valid number.\n");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdrawal");
                            System.out.println("3. Transfer");
                            System.out.println("4. Check Balance");
                            System.out.println("5. Exit");
                            int selection = validation.checkInt();
                            switch(selection)
                            {
                                case 1:
                                    System.out.println("How much would you like to deposit?");
                                    break;
                                case 2:
                                    System.out.println("How much would you like to withdrawal?");
                                    break;
                                case 3:
                                    System.out.print("How do you want to transfer?");
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                            }
                        } while (number != 5);

                    }

                case 2:
                    break;
            }
        } while (number != 2);

        System.out.println("Exited Successfully");




    }
}
