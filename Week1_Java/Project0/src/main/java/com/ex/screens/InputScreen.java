package com.ex.screens;

import com.ex.app.Application;
import com.ex.app.MarketplaceApplication;

import java.util.Scanner;

public class InputScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((MarketplaceApplication)app).getScanner();

        while(true) {
            System.out.println("Enter a username");
            String username = scanner.nextLine();

            if(username.length() == 0 || username.trim().equals(""))
                continue;
            else if (username.equals("quit")){
                break;
            }
            else {
                ((MarketplaceApplication) app).setCurrentQuestion(username);
                return new OutputScreen();
            }
        }
        return null;
    }
}