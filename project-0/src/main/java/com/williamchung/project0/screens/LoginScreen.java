/**
 * First screen to be seen by the user.
 * First given option redirects to the Register screen if desired, or continues with login.
 * Username and Password passed to Service to validate user
 * Validation redirects to HomeScreen.
 * 3 failed validations terminates the program.
 */
package com.williamchung.project0.screens;

import com.williamchung.project0.application.Application;
import com.williamchung.project0.application.BankAccountApplication;
import com.williamchung.project0.services.UserService;

import java.util.Scanner;

public class LoginScreen implements Screen {

    @Override
    public Screen doScreen(Application app) {
        //Inject Scanner and Service in every Screen
        Scanner scanner = ((BankAccountApplication) app).getScanner();
        UserService userService = ((BankAccountApplication) app).getUserService();
        int threeLoginAttempts = 0;
        System.out.println("Welcome to Bank of William.");

        while(threeLoginAttempts < 3){
            //First option to Login or Register
            System.out.println(
                "[ 1 ] Login with Existing account" +
                "\n[ 2 ] Register new Account"
            );
            String input = scanner.nextLine();

            if (input.equals("2")) {
                return new RegisterScreen();
            } else if (input.equals("1")) {
                //Take username and password
                System.out.println("Please enter your Username");
                String username = scanner.nextLine();
                if(username.equals("\\q")){
                    break;
                }
                System.out.println("Please enter your Password");
                String password = scanner.nextLine();
                if(password.equals("\\q")) {
                    break;
                }
                //Authenticate user with LoginService
                ((BankAccountApplication) app).setUser(userService.authenticateUser(username, password));
                if (((BankAccountApplication) app).getUser() != null){
                    return new HomeScreen();
                } else {
                    threeLoginAttempts++;
                    System.out.println("Login Invalid. Please try again. Attempt #" + threeLoginAttempts);
                    continue;
                    //Does not display whether Username or Password was invalid
                }
            } else {
                System.out.println("Input invalid, please try again.");
                continue;
            }
        }
        System.out.println("Too many unsuccessful attempts. Please try again later.");
        return null;
    }
}