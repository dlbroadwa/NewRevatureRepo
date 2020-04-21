package com.Project0.screens;

import com.Project0.App.Application;
import com.Project0.App.StockMarketApp;
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.market.MarketTrades;
import com.Project0.market.TradesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.stocks.Stocks;
import com.Project0.users.Users;
import com.Project0.utilities.PostgresConnectionUtilities;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Scanner;


public class BrokerTradeScreen implements Screen
{
    private Boolean exited = false;
    private Boolean started = false;
    private String input;
    private String command;
    private Scanner scanner;
    private Users user = MainScreen.getUser();

    @Override
    public Screen doScreen(Application app) {
        while (exited != true) {
            if (started == false) {
                System.out.println("Welcome To the Carolina Stock Exchange " + user.getUsername() + '!' +
                        "\nWe look forward to doing business with you!\n" +
                        "We will now run by number inputs\n" +
                        "Press the number corresponding to and action, or PRESS ENTER TO BUY STOCKS,\n" +
                        "(requires The company ID)\n");
                started = true;
            }
            System.out.println("1. View the Stocks available here on this exchange\n" +
                    "2. View the most recent Trades made here\n" +
                    "3. View the companies on our exchange\n" +
                    "Now just type in the number you see beside what you'd like to do\n" +
                    "don't forget you can always type \"exit\" to leave\n" +
                    "or \"up\" to go back to the login menu. Enjoy!");
            scanner = ((StockMarketApp) app).getScanner();
            command = scanner.nextLine();
            StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
            switch (input) {
                case "1":
                    System.out.println("Sort by\n 1. Trade ID\n2: Company Name \n3: Total Shares \n4. Up Shares" +
                            "\n5: Share value");
                    input = scanner.nextLine();
                    if ((command == "1") || (command == "2") || (command == "3") || (command == "4") || (command == "5"))
                        StockRepository.printAllStocks(new Integer(command));
                    else StockRepository.printAllStocks(1);

                case "2":
                    TradesRepository.printTrades();
                    input = null;
                    continue;

                case "3":
                    List<StockCompanies> list = repo.findAll();
                    for (StockCompanies i : list) System.out.println(i);
                    input = null;
                    continue;

                default:
                    if (input == null)
                    {
                        while (true)
                        {
                            input = null;
                            command = null;
                            System.out.println("Lets make this purchase happen! Which company would you like to buy stock from?");
                            command = scanner.nextLine();
                            try
                            {
                                StockCompanies comp = repo.findbyID(new Integer(command));
                                if (comp != null)
                                {
                                    while (true)
                                    {
                                        System.out.println("Now please enter the amount of stocks you wish to purchase from "
                                                +comp.getName());
                                        input = scanner.nextLine();
                                        if(input != null)
                                        {
                                            try
                                            {
                                                MarketTrades trade = new MarketTrades(user.getUsername(),comp.getId().toString(),input);
                                                if (trade != null) new TradesRepository(new PostgresConnectionUtilities()).update(trade, comp.getId());
                                                System.out.println(trade + "\nHas been Completed");
                                                break;
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println("Cannot complete this trade, please enter a number, for the stock you wish to buy");
                                                continue;
                                            }
                                        }
                                        else if((input == "UP")||(input == "up")||(input == "Up"))
                                        {
                                            MainScreen mainScreen = new MainScreen();
                                            mainScreen.setBeenRun(1);
                                            return mainScreen;
                                        }

                                        else if ((input == "exit")||(input =="EXIT")||(input == "exit()")||(input == "Exit")) System.exit(-1);
                                        else System.out.println("Please enter an amount you wish to trade, or if this is the wrong company, type up");
                                    }
                                }
                                else continue;
                            }
                            catch (Exception e) {
                                System.out.println("Invalid entry: "+ command +" Enter the Company ID you wish to purchase from");
                                command = null;
                                input = null;
                                continue;
                            }
                            break;
                        }
                    }
            }
        }

        MainScreen newMain = new MainScreen();
        newMain.setBeenRun(1);
        return newMain;
    }
}

