package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;

public class LoginScreen implements Screen  {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((SchedulingApplication)app).getScanner();

        System.out.println("Welcome to the scheduling portal:");

        System.out.println("Enter Username: if you are an administrator type username.admin: ");
        String user = scanner.nextLine();

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        if (user.endsWith(".admin")) {
            return (Screen) new AdminScreen();
        } else {
            return (Screen) new UserScreen();
        }
    }
}
