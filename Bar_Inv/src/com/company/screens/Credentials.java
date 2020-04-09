package com.company.screens;

import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.admin.Menu;
import com.company.screens.customer.CustMenu;

import java.lang.*;

import java.util.Scanner;

public class Credentials implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();

        System.out.println("Returning user? [y/n]");
        String ans = scanner.nextLine();

        if (ans == "y" || ans == "Y"){
            System.out.println("Enter username:");
            String userName = scanner.nextLine();
            System.out.println("Enter password");
            String pw = scanner.nextLine();
            //check if these match a pair on "users and passwords"
            return new CustMenu();
        } else if (ans == "admin"){
            System.out.println("Enter password");
            //verify password on "users and passwords"
            return new Menu();
        } else {
            System.out.println("Enter new username:");
            String newUserName = scanner.nextLine();
            System.out.println("Enter new password");
            String newPass = scanner.nextLine();
            //add this pair to "users and passwords"
            return new CustMenu();
        }


    }
}
