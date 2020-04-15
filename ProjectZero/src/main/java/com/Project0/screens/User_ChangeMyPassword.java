package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.UserDAO;
import com.Project0.model.User;

import java.util.Scanner;

public class User_ChangeMyPassword implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        User user = app.getUser();
        UserDAO dao = app.getUserDAO();

        System.out.println("CHANGE PASSWORD UTILITY");
        System.out.println("ENTER CURRENT PASSWORD:");
        String myCurrentPassword = scanner.nextLine();
        String hashedCurrent = app.generateHash(myCurrentPassword);
        if(!hashedCurrent.equals(app.getPassword())) {
            System.out.println("INVALID PASSWORD ATTEMPT");
            return new GolferOptionsMain();
        }
        System.out.println("ENTER NEW PASSWORD:");
        String newPassword = scanner.nextLine();
        System.out.println("CONFIRM NEW PASSWORD:");
        String newConfirmed = scanner.nextLine();
        if (newPassword.equals(newConfirmed)) {
            String hashedNew = app.generateHash(newPassword);
            try {
                dao.changeUserPassword(user, hashedNew, app);
                System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
                return new GolferOptionsMain();
            } catch (Exception e) {
                System.out.println("PROBLEM CHANGING PASSWORD");
                return new GolferOptionsMain();
            }
        }
        System.out.println("ERROR WITH PASSWORD UTILITY");
        return new GolferOptionsMain();
    }
}
