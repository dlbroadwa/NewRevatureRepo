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
        //check to see who is using it
        System.out.println("Returning user? [y/n]");      //admin must enter "admin" to get access to admin menu
        String ans = scanner.nextLine();

        if (ans.equals("y")){                              //Returning user enters username and password
            System.out.println("Enter username:");
            String userName = scanner.nextLine();
            System.out.println("Enter password");
            String pw = scanner.nextLine();
            //check if these match a pair on "users and passwords"
            return new CustMenu();
        } else if (ans.equals("admin")){                  //admin's info is saved on the same list of names and passwords
            System.out.println("Enter password");
            String adminPw = scanner.nextLine();
            //verify password on "users and passwords"
            return new Menu();
        } else {
            System.out.println("Enter new username:");    //enter new info
            String newUserName = scanner.nextLine();
            System.out.println("Enter new password");
            String newPass = scanner.nextLine();
            System.out.println("You entered:\n"+newUserName+" and "+newPass);
            //add this pair to "users and passwords"
            return new CustMenu();
        }




    }


}
