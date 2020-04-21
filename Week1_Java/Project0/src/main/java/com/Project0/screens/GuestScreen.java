package com.Project0.screens;

import com.Project0.App.Application;
import com.Project0.App.StockMarketApp;
import com.Project0.market.TradesRepository;

import java.util.Scanner;

public class GuestScreen implements Screen {
    private Boolean exited = false;
    Boolean passed = false;
    private MainScreen newMain;
    String input;
    Scanner scanner;

    @Override
    public Screen doScreen(Application app) {
        while (exited != true)
        {
            if (!passed) {
                System.out.println("Welcome To the Carolina Stock Exchange our esteemed guest\n" +
                        "We look forward to sharing in the wonderful trades here at our exchange.\n" +
                        "Below, you will find a list of all our most recent trades.\n" +
                        "type \"exit\" to leave the program, or \"up\" to go back to login. \n" +
                        "Pressing enter with any other input will reload the current trades\n" +
                        "To earn a license or join our market with your business, please \n" +
                        "visit out offices during the hours of 5am-6am EST");
            }
            passed = true;
            //Display all trade, then wait for input
            TradesRepository.printTrades();
            scanner = ((StockMarketApp) app).getScanner();
            input = scanner.nextLine();
            if ((input == "exit") || (input == "Exit") || (input == "EXIT")) {
                System.exit(-1);
            }
            else if (input == "up")
            {
                MainScreen newMain = new MainScreen();
                newMain.setBeenRun(1);
                return newMain;
            }
        }
        return null;
    }
}


