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
    private Users user;

    @Override
    public Screen doScreen(Application app) {
        user = MainScreen.getUser();
        while (exited != true) {
            if (started == false) {

                System.out.println("\nWelcome To the Carolina Stock Exchange " + user.getUsername() + '!' +
                        "\nWe look forward to doing business with you!\n" +
                        "We will now run by number inputs\n" +
                        "Press the number corresponding to and action then PRESS ENTER,\n" +
                        "(requires The company ID)\n");
            }
            System.out.println("\n0. Make a trade! \n"+
                    "1. View the Stocks available here on this exchange\n" +
                    "2. View the most recent Trades made here\n" +
                    "3. View the companies on our exchange\n");
            if (started == false)
            {
                System.out.println("Now just type in the number you see beside what you'd like to do\n" +
                    "don't forget you can always type \"exit\" to leave\n" +
                    "or \"up\" to go back to the login menu. Enjoy!");
                started = true;
            }
            scanner = ((StockMarketApp) app).getScanner();
            input = scanner.nextLine();
            StockCompaniesRepository repo = new StockCompaniesRepository(new PostgresConnectionUtilities());
            if (input != null)
            {
                if (input.equals("up"))
                {
                    return null;
                }
                if (input.equals("1"))
                {
                    System.out.println("Sort by\n 1. Company ID\n2: Company Name \n3: Total Shares \n4. Up Shares" +
                            "\n5: Share value");
                    command = scanner.nextLine();
                    if ((command.equals("1")) || (command.equals("2")) || (command.equals("3")) || (command.equals("4")) || (command.equals("5")))
                        StockRepository.printAllStocks(new Integer(command));
                    else StockRepository.printAllStocks(1);
                }
                else if (input.equals("2"))
                {
                    TradesRepository.printTrades();
                    input = null;
                    continue;
                }

                else if (input.equals("3"))
                {
                    List<StockCompanies> list = repo.findAll();
                    for (StockCompanies i : list) System.out.println(i);
                    input = null;
                    continue;
                }
                else
                {
                    if (input.equals("0"))
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
                                        if((input == "UP")||(input == "up")||(input == "Up"))
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
        }

        return null;
    }
}

