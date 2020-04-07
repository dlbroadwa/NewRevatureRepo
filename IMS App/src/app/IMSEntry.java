package app;

import admin.Admin;
import guest.Guest;

import java.util.List;
import java.util.Scanner;

public class IMSEntry extends Application
{
    private String[] choices = {"guest", "admin", "exit"};
    private String greeting =
            "Welcome to the Instrument Management System\n" +
                    "   Are you a guest or admin?\n" +
                    "       [ guest, admin, exit]"
            ;
    @Override
    public void run()
    {
        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if(choice.equals(choices[0]))
        {
            Guest guest = new Guest();
        }
        else if(choice.equals(choices[1]))
        {
            Admin admin = new Admin();
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
        scanner.close();
    }

    private void exitSystem()
    {
        System.exit(0);
    }


}
