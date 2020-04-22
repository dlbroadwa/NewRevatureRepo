package com.company.menus;
/**
 * Menu user sees when they want to login
 */

import com.company.Banking.BankCustomer;
import com.company.DataAccess.DAOI;
import com.company.app.Application;
import com.company.app.BankApplication;
import com.company.login.LoginService;

import java.util.Scanner;

public class LoginMenu implements Menu {
    BankCustomer currentCustomer = new BankCustomer();

    @Override
    public Menu doMenu(Application app) {
        Scanner sc = ((BankApplication) app).getScanner();
        DAOI<BankCustomer, Integer> dao = ((BankApplication) app).getDAO();
        LoginService loginService = new LoginService(dao);
        // Login Menu
        System.out.println("Welcome to the login screen\n");
        System.out.println("Please Enter Username: ");
        String userName = sc.nextLine();
        System.out.println("Please Enter Password: ");
        String passWord = sc.nextLine();

        //Go to login service to verify user login
        BankCustomer customer;
        currentCustomer = loginService.login(userName, passWord);
        if (currentCustomer.getId() == 0) {
            System.out.println("That is not a valid username or password\n");
            return new WelcomeMenu();
        } else {
            System.out.println(currentCustomer.getFirstName() + " Successfully Logged in!\n");
            return new BankMenu(currentCustomer);
        }
    }
}
