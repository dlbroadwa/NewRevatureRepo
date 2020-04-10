package com.company.Menu;

import com.company.Banking.BankCustomer;
import com.company.Validation.Validate;

import java.util.Scanner;

public class Menu {
    Validate validation = new Validate();
    BankCustomer customer = new BankCustomer();

    public void runMenu(){

        Scanner sc = new Scanner(System.in);
        int number;
        double amount;
        boolean canTransfer;
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

                    customer = validation.verifyLogin(userName, passWord);

                    if(customer.getId() == 0)
                    {
                        System.out.println("That is not a valid username or password");
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
                                    System.out.println("Would you like to deposit in\n");
                                    System.out.println("1. Checking\n");
                                    System.out.println("2. Savings\n");
                                    int accountType = validation.checkInt();
                                    switch(accountType)
                                    {
                                        case 1:
                                            System.out.println("How much would you like to deposit?");
                                            amount = validation.checkDouble();
                                            canTransfer = validation.verifyAmount(customer.getCheckings(), amount);

                                    }


                                    break;
                                case 2:
                                    System.out.println("How much would you like to withdrawal?");
                                    amount = validation.checkDouble();

                                    break;
                                case 3:
                                    System.out.print("How do you want to transfer?");
                                    break;
                                case 4:
                                    System.out.println("Checkings: " + customer.getCheckings());
                                    System.out.println("Savings: " + customer.getSavings());
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
