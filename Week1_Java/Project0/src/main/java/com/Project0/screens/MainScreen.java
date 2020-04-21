package com.Project0.screens;

import com.Project0.App.Application;
import com.Project0.App.StockMarketApp;
import com.Project0.users.UserRepository;
import com.Project0.users.Users;
import com.Project0.utilities.PostgresConnectionUtilities;

import java.util.Scanner;

public class MainScreen implements Screen

{
    private UserRepository usersrepo = new UserRepository(new PostgresConnectionUtilities());
    public static Boolean loggedIn = false;
    Scanner scanner = null;
    Integer beenRun = 0;
    String username;
    String password;
    private static Users user;



    public Screen doScreen(Application app)
    {
        while (loggedIn != true)
        {
            if (beenRun == 0)
            {
                System.out.println("Welcome to the Carolina Stock Exchange!" + "\n" +
                "Type: HELP if you don't know how to navigate this here console " + "\n" +
                "Otherwise enter your credentials, and we'll get y'all rollin, ya here?\n"+
                "enter a valid user name, or type guest, to just view our available stocks\n"+
                "you can also view any public domain information ya hear?\n\n");
                beenRun++;
            }
            System.out.println("Enter a Valid user name, or \"press enter to access as a guest\". ");
            scanner = ((StockMarketApp)app).getScanner();
            String input = scanner.nextLine();
            if (input == "exit()" || input == "exit")
            {
                System.exit(-1);
            }
            else if (input.equals("help")||(input.equals("Help")))
            {
                System.out.println("Main Menu's Help screen:\n "+
                "First we prompt you for a valid user name, then we check you password\n"+
                "-If you are a registered broker you may feel free to login with your id and password\n"+
                "-Admins, use their own credentials to log in. to go to a previous section of"+
                "the menu, type 'up' at any time except for right here y'all, type 'exit()' to leave this program");
            }
            else if ((input == "guest")||(input == "Guest")||(input == "GUEST"))
            {
                loggedIn = true;
                return new GuestScreen();
            }
            else
            {
                username = input;
                Users user = usersrepo.findbyID(input);
                System.out.println("Welcome! "+ user.getUsername() + " now enter your password, " +
                        "or type enter if this was a mistake");
                input = scanner.nextLine();
                try
                {
                    password = input;
                    //here is where we will test user credentials and determine if the should be able to log in
                    //if (valid credentials.
                    user = usersrepo.login(username, password);
                }
                catch (Exception e) {
                    System.out.println("Invalid password, lets start over");
                }

                if (user != null)
                {
                    if (user.getPrivy() == "true")
                    {
                        //scanner.close();
                        loggedIn = true;
                        return (new AdminScreen());
                    }
                    else
                     {
                         //scanner.close();
                        loggedIn = true;
                        return (new BrokerTradeScreen());
                    }
                }
                else continue;
            }

        }
        return this;
    }
    public static Users getUser() { return user;}

    public void setBeenRun(Integer beenRun) {
        this.beenRun = beenRun;
    }
}
