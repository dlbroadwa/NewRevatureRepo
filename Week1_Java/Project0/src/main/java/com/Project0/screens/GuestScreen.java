package com.Project0.screens;

import com.Project0.App.Application;
import com.Project0.App.StockMarketApp;
import com.Project0.market.TradesRepository;

import java.util.Scanner;

public class GuestScreen implements Screen {
    private Boolean exited = false;
    Boolean passed = false;
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
                        "type \"exit\" to leave the program. Pressing enter with any other input\n"+
                        "will reload the current trades To earn a license or join our market with your business,\n " +
                        "please Visit out offices during the hours of 5am-6am EST");
            }
            passed = true;
            //Wait for input then display all trades.
            scanner = ((StockMarketApp) app).getScanner();
            input = scanner.nextLine();
            TradesRepository.printTrades();
//            if (input.equals("up"))
//            {
//                new StockMarketApp().run(); //Non// Functional
//            }
            if ((input.equals("exit")) || (input.equals("Exit")) || (input.equals("EXIT")))
            {
                System.exit(-1);
            }

        }
        return null;
    }
}


