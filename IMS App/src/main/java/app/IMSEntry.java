package app;

import admin.Admin;
import guest.Guest;


import java.util.Scanner;

public class IMSEntry extends Application
{
    private String[] choices = {"guest", "admin", "exit"};
    private Scanner scanner = super.getScanner();
    private Guest guest;
    private Admin admin;

    public void run()
    {
        String greeting =
                "Welcome to the Instrument Management System\n" +
                        "       Are you a guest or admin?\n" +
                        "         [guest, admin, exit]";
        System.out.println(greeting);

        String choice = scanner.next().toLowerCase();

        if(choice.equals(choices[0]))
        {
            this.guest = new Guest();
        }
        else if(choice.equals(choices[1]))
        {
            this.admin = new Admin();
        }
        else
        {
            System.out.println("Exiting Instrument Management System.....");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            exitSystem();
        }
    }

//    public Scanner getScanner()
//    {
//        return this.scanner;
//    }

    public Guest getGuest()
    {
        return this.guest;
    }

    public Admin getAdmin()
    {
        return this.admin;
    }

    public void exitSystem()
    {
        try
        {
            System.out.println("Exiting the Instrument Management System.....");
            Thread.sleep(2000);
            System.out.println("You have exited IMS. Come back soon!");
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
