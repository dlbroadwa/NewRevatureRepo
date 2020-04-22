package com.Project0.screens;

import com.Project0.App.Application;
import com.Project0.App.StockMarketApp;
import com.Project0.Main;
import com.Project0.brokers.StockBrokerRepository;
import com.Project0.brokers.StockBrokers;
import com.Project0.companies.StockCompanies;
import com.Project0.companies.StockCompaniesRepository;
import com.Project0.market.TradesRepository;
import com.Project0.stocks.StockRepository;
import com.Project0.stocks.Stocks;
import com.Project0.users.UserRepository;
import com.Project0.users.Users;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminScreen implements Screen
{
    Boolean loggedin = true;
    Users user;
    String input;
    String command;
    Scanner scanner;
    ArrayList<String> args;
    String[] broker_user;
    String[] stocks_companies;

    @Override
    public Screen doScreen(Application app)
    {

        while (loggedin = true)
        {
            System.out.println("Welcome Admin, What will we be doing?\n" +
                    "0. View Trades\n" +
                    "1. View Stocks\n" +
                    "2. Add a new Company\n" +
                    "3. Add a Broker\n" +
                    "4. Revoke a Broker\n");
            user = MainScreen.getUser();
            args= new ArrayList<>(); //set up ArrayList for usage by programmer.
            scanner = ((StockMarketApp) app).getScanner();
            input = scanner.nextLine(); //take in admin inout
            if ((input.equals("exit"))&&(input!=null))
            {
                System.exit(-1);
            }
            if (input.equals("0"))
            {
                TradesRepository.printTrades();
            }
            else if (input.equals("1"))
            {
                StockRepository.printAllStocks(1);
            }
            else if (input.equals("3"))
            {
                StockBrokerRepository sbrepo = new StockBrokerRepository(new PostgresConnectionUtilities());
                UserRepository urepo = new UserRepository(new PostgresConnectionUtilities());
                System.out.println("Enter the new broker's ID");
                input = scanner.nextLine();
                try
                {
                    new Integer(input);
                    args.add(input);
                }
                catch(Exception e)
                {
                    System.out.println("Invalid entry, brokers must be a number\n");
                    e.printStackTrace();
                    input = null;
                    continue;
                }
                try
                {
                    if (sbrepo.findbyID(new Integer(input)) != null)
                    {
                        System.out.println("Broker with that number already exists");
                        continue;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    System.out.println("Enter the new broker's First Name");
                    args.add(scanner.nextLine());
                    System.out.println("Enter The Broker's last Name");
                    args.add(scanner.nextLine());
                    broker_user = new String[]{args.get(1), args.get(2), args.get(0)};
                    sbrepo = new StockBrokerRepository(new PostgresConnectionUtilities());
                    urepo = new UserRepository(new PostgresConnectionUtilities());
                    sbrepo.update(new StockBrokers(broker_user),new Integer(input));
                    urepo.update(new Users(args.get(0),"password","f"),args.get(0));
                    System.out.println(sbrepo.findbyID(new Integer(args.get(0))));
                    args.clear();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    continue;
                }
            }
            else if (input.equals("2")) //args = [id, name, totalshares, upshares, ppShare]
            {
                StockCompaniesRepository crepo = new StockCompaniesRepository(new PostgresConnectionUtilities());
                StockRepository srepo = new StockRepository(new PostgresConnectionUtilities());

                System.out.println("Adding a new company? what is their ID");
                input = scanner.nextLine();
                try
                {
                    new Integer(input);
                    args.add(input);
                }
                catch (Exception e)
                {
                    System.out.println("Company ID must be a number!");
                    input = null;
                    continue;
                }
                System.out.println("Now please carefully add Company name:");
                input = scanner.nextLine();
                args.add(input);
                System.out.println("How Many Shares does this company have?");
                command = scanner.nextLine();
                try
                {
                    new Integer(command);
                    args.add(command);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.println("Total Stocks must be a number!");
                    input = null;
                    args.clear();
                    continue;
                }
                System.out.println("How many shares will be available for trade?");
                input = scanner.nextLine();
                try
                {
                   new Integer(input);
                   args.add(input);
                }
                catch (Exception e)
                {
                    System.out.println("Available Stocks must be a number!");
                    input = null;
                    args.clear();
                    continue;
                }
                System.out.println("How much is the starting price for each stock?");
                input = scanner.nextLine();
                try
                {
                    new Float(input);
                    args.add(input);

                }
                catch(Exception e)
                {
                    System.out.println("Price for stocks much be a Floating-point Number (IE 155.0");
                    args.clear();
                    input = null;
                    continue;
                }
                try
                {

                    crepo .update(new StockCompanies(new String[] {args.get(1),args.get(0)}), new Integer(args.get(0)));
                    srepo.update( new Stocks(new String[] {args.get(0),args.get(2),args.get(3),args.get(4)}),new Integer(args.get(0)));
                    System.out.println(srepo.findbyID(new Integer(args.get(0))));
                    System.out.println();
                    System.out.println(crepo.findbyID(new Integer(args.get(0))));
                    args.clear();
                    input = null;
                    continue;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    continue;
                }
            }
            else if (input.equals("4"))
            {
                System.out.println("Implement later!");
            }
            else if (input.equals("shake"))
            {
                System.out.println("How much shaking shall we do?");
                input = scanner.nextLine();
                TradesRepository trade = new TradesRepository(new PostgresConnectionUtilities());
                try { trade.shakeTheMarket(new Integer(input));}
                catch (Exception e) {e.printStackTrace();}
            }
        }
        return null;
    }

}
