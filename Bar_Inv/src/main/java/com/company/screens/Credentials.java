package com.company.screens;

import com.company.DAO.data.UserRepository;
import com.company.*;
import com.company.DAO.models.User;
import com.company.app.Application;
import com.company.app.BarInventoryApplication;
import com.company.screens.admin.Menu;
import com.company.screens.customer.CustMenu;
import com.company.services.UserService;
import java.util.Scanner;

/***
 * The credentials screen is the login screen. Returning users can login with their username and password.
 * The admin can access the admin features through this screen by answering "admin" to the first question.
 *
 */
public class Credentials implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((BarInventoryApplication)app).getScanner();
        UserService userService = ((BarInventoryApplication)app).getUserService();
        //check to see who is using it
        System.out.println("Returning user? [y/n]");      //admin must enter "admin" to get access to admin menu
        String ans = scanner.nextLine();

        if (ans.equals("y")){                              //Returning user enters username and password
            System.out.println("Enter username:");
            String userName = scanner.nextLine();
            System.out.println("Enter password");
            String pw = scanner.nextLine();
            //check if these match a pair on "users and passwords"
            if(userService.userByName(userName, pw) == 1){
                System.out.println("Login successful, welcome back "+userName);
                ((BarInventoryApplication)app).setCurrentUser(userName);
                return new CustMenu();
            } else{
                System.out.println("Login unsuccessful, please try again");
                return new Credentials();
            }

        } else if (ans.equals("admin")){                  //admin's info is saved in the same table of names and passwords
            System.out.println("Enter password");
            String adminPw = scanner.nextLine();
            //verify password on "users and passwords"
            if (userService.userByName("admin",adminPw) == 1) {
                System.out.println("Welcome admin");
                ((BarInventoryApplication)app).setCurrentUser("admin");
                return new Menu();
            }else{
                System.out.println("Login unsuccessful, please try again");
                return new Credentials();
            }

        } else {
            System.out.println("Enter new username:");    //enter new info
            String newUserName = scanner.nextLine();
            System.out.println("Enter new password");
            String newPass = scanner.nextLine();
            System.out.println("You entered:\n"+newUserName+" and "+newPass);
            //add this pair to "users and passwords"
            int y = userService.addUser(newUserName,newPass);
            if(y==1){
                ((BarInventoryApplication)app).setCurrentUser(newUserName);
                return new CustMenu();
            } else{
                return new Credentials();
            }
        }


    }


}