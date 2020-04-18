package com.proj.screens;

import com.proj.app.EventHandler;
import com.proj.clients.UserServices;
import com.proj.data.Repository;
import com.proj.data.UserSQLRepository;
import com.proj.models.User;
import com.proj.utils.ConnectionUtils;
import com.proj.utils.PostgresConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NewUserScreen implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException, SQLException {

        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://revatureproject-0.cxeo5zs5fqav.us-east-2.rds.amazonaws.com:5432/postgres",
                "johnproj0", "JellyBean13", "revproj0");

        Repository<User, String> userRepo = new UserSQLRepository(connectionUtils);

        UserServices uService = new UserServices(userRepo);
        List<User> allUser = uService.getAllUsers();

        Scanner scanner = app.getScanner();

        System.out.println("Please create a new username:");
        String newUsername = scanner.nextLine();

        System.out.println("Please create a new password:");
        String newPassword = scanner.nextLine();

        System.out.println("Please confirm password:");
        String confirmPassword = scanner.nextLine();


        if (newPassword.equals(confirmPassword)) {
            uService.addUser(newUsername, newPassword);
            return new LoginScreen();
        } else {
            System.out.println("Passwords do not match: please try again");
            return new NewUserScreen();
        }
    }
}




