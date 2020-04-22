package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.UserServices;

import java.io.IOException;

import java.sql.SQLException;

import java.util.Scanner;

//*******************************NOT YET UTILIZED**********************************//

public class NewUserScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException, SQLException {

        UserServices userService = app.getUService();
        Scanner scanner = app.getScanner();

        System.out.println("Please create a new username:");
        String newUsername = scanner.nextLine();

        System.out.println("Please create a new password:");
        String newPassword = scanner.nextLine();

        System.out.println("Please confirm password:");
        String confirmPassword = scanner.nextLine();

            while (true) {
                try {
                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Passwords do not match: please try again");
                        return new NewUserScreen();
                    } else {
                        userService.addUser(newUsername, newPassword);
                    }
                } catch (SQLException e) {
                    System.out.println("Username already taken: Try again!!!!!!!.");
                    return new NewUserScreen();
                }
            }
    }
}





