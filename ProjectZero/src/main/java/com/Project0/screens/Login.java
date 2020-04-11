package com.Project0.screens;

import com.Project0.application.App;

import java.util.Scanner;

public class Login implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();

        while(true){
            System.out.println("GOLF COURSE APPLICATION LOGIN \n");
            System.out.println("Please enter username: ");
            String username = scanner.nextLine();

            if(username.length() == 0 || username.trim().equals(""))
                continue;

            System.out.println("Please enter password: ");
            String pass = scanner.nextLine();
            if(pass.length() == 0 || pass.trim().equals(""))
                continue;

            app.setUsername(username);
            app.setPassword(pass);

            return new LoginSuccess();
        }
    }
}
