package admin;

//TODO implement functions from the AvailableFunctions abstract class
import app.AvailableFunctions;

import guest.Guest;

import java.util.Scanner;

public class Admin
{
    private static final int MEMBER_ID = 224456789;

    public Admin()
    {
        if(IdVerification())
        {
            System.out.println("Connected to Admin.....\n");
            //TODO Add Admin Function Statements Here
            Relocate();
        }
        else
        {
            System.out.println("Exiting System.....");
            System.exit(0);
        }
    }

    private void Relocate()
    {
        System.out.println("Would you like to use another Admin function, move to being a guest, or exit?\n" +
                "                       [admin,guest,exit]");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next().toLowerCase();
        switch (choice)
        {
            case "admin":
                Admin admin = new Admin();
            case "guest":
                Guest guest = new Guest();
            default:
                System.exit(0);
        }
        scanner.reset();
        scanner.close();
        System.exit(0);
    }

    private boolean IdVerification()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your Admin ID: ");
        boolean verify = scanner.next().equals(Integer.toString(this.MEMBER_ID));
        if(verify)
        {
            scanner.reset();
            return true;
        }
        else
        {
            System.out.println("Invalid entry or password, Exiting the Instrument Management System...");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("You have exited IMS. Come back soon!");
            System.exit(0);
        }
        return false;
    }
}
