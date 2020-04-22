package com.company.menus;
/**
 * First Menu when user launches app
 */

import com.company.validation.Validate;
import com.company.app.Application;
import com.company.app.BankApplication;
import java.util.Scanner;

public class WelcomeMenu implements Menu {
    @Override
    public Menu doMenu(Application app) {
        Scanner sc = ((BankApplication)app).getScanner();
        Validate validation = ((BankApplication)app).getValidate();
        int number;
        int accountType;
        // Start of Menu when application starts
        while(true) {
            System.out.println("Hello Customer! Please enter a valid number.\n");
            System.out.println("1. Sign In");
            System.out.println("2. Exit");

            number = validation.checkInt();
            switch(number)
            {
                case 1:
                    return new LoginMenu();
                case 2:
                    return null;
                default:
                    System.out.println("Thats not a valid option");
            }
        }
    }
}
