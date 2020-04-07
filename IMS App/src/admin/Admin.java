package admin;

import app.AvailableFunctions;

import java.util.HashMap;
import java.util.Scanner;

public class Admin
{
    private int memberId = 224456789;

    public Admin()
    {
        if(IdVerification())
        {
            System.out.println("Connected to Admin.....");
            System.out.println("Exiting System");
            System.exit(0);
        }
        else
        {
            System.exit(0);
        }

    }

    private boolean IdVerification()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your Admin ID: ");
        boolean verify = scanner.next().equals(Integer.toString(this.memberId));
        if(verify)
        {
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
