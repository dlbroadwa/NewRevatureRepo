package com.Project0.screens;

import com.Project0.application.App;

import java.util.Scanner;

public class Login implements Screen{
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();

        while(true){
            System.out.println("GOLF COURSE APPLICATION LOGIN");
            System.out.println("Please enter username: ");
            String username = scanner.nextLine();

            if(username.length() == 0 || username.trim().equals(""))
                continue;

            System.out.println("Please enter password: ");
            String pass = scanner.nextLine();
            if(pass.length() == 0 || pass.trim().equals(""))
                continue;

            //SET USERNAME/PASSWORD IN APP CLASS - PASSWORD = HASHED VALUE OF USER INPUT
            app.setUsername(username);
            String hashedPass = app.generateHash(pass);
            app.setPassword(hashedPass);

            return new LoginSuccess();
        }
    }
}
