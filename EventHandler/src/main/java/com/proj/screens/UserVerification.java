package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.File;
import java.util.Scanner;

public class UserVerification implements Screen{
    @Override
    public Screen doScreen(EventHandler app) {


//*******************************USERNAME AND PASSWORD VERIFICATION WITH FILE IO****************************************//
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";
        try {
            Scanner scan = new Scanner(new File("C:\\Users\\johnn\\Desktop\\GitJump\\EventHandler\\resources\\login"));
            scan.useDelimiter("[,\n]");

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
        if (app.getUsername().endsWith(".admin") && found == true)
        {
            return (Screen) new AdminScreen();
        } else if (!app.getUsername().endsWith(".admin") && found == true)
        {
            return (Screen) new UserScreen();
        } else {
            System.out.println("Wrong username or password : Please try again");
        }
        return (Screen) new LoginScreen();
    }
}
