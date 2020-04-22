package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This is the UserVerification class it will take in the userinput from the WelcomeScreen
 * It will then compare the username and password to a saved username and password file.
 */

public class UserVerification implements Screen{
    @Override
    public Screen doScreen(EventHandler app) throws SQLException {


//*******************************USERNAME AND PASSWORD VERIFICATION WITH FILE IO****************************************//
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

/**
 * this method is used to check the username and password inputted by the user
 * it will then check the username and password against a file with presaved user/password
 */
        try {
            Scanner scan = new Scanner(new File("C:\\Users\\johnn\\Desktop\\GitJump\\EventHandler\\resources\\login"));
            scan.useDelimiter("[,\n]"); //this is the dilimeter method for reading saved (username, passwords)

            while (scan.hasNext() && !found) {
                tempUsername = scan.next();
                tempPassword = scan.next();

                if (tempUsername.trim().equals(app.getUsername().trim()) && tempPassword.trim().equals(app.getPassword().trim())) {
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong username or password: Please try again.");
        }


//*****************************************Checking for admin***********************************************************//
/**
 * this will check if the user is an administrator or not.
 * if user is an administrator then it will return an adminScreen
 * if user is not admin then it will return an UserScreen
 */
        if (app.getUsername().endsWith(".admin") && found == true) {
            return (Screen) new AdminScreen();
        } else if (!app.getUsername().endsWith(".admin") && found == true) {
            return (Screen) new UserScreen();
        } else {
            System.out.println("Wrong username or password : Please try again");
        }
        return (Screen) new LoginScreen();
    }
}
