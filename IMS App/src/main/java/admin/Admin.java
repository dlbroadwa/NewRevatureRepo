package admin;

import app.IMSEntry;
import database.Database;
import guest.Guest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends IMSEntry
{
    private static final int MEMBER_ID = 224456789;
    private Database db;
    public Admin() throws IOException, SQLException
    {
        if(IdVerification())
        {
            System.out.println("\nConnected to Admin.....\n");
            Functions();
            Relocate();
        }
        else
        {
            System.out.println("Exiting System.....");
            System.exit(0);
        }
    }

    private void Relocate() throws IOException, SQLException {
        System.out.println("================================================================================");
        System.out.println("Would you like to use another Admin function, move to being a guest, or exit?\n" +
                "                       [admin, guest, exit]");
        System.out.println("================================================================================");
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if ("admin".equals(choice))
        {
            Admin admin = new Admin();
            //Guest guest = new Guest();
        }
        else if ("guest".equals(choice))
        {
            Guest guest = new Guest();
        }
        super.exitSystem();
    }

    private boolean IdVerification()
    {
        Scanner scanner = super.getScanner();
        System.out.print("Please enter your Admin ID: ");
        boolean verify = scanner.next().equals(Integer.toString(MEMBER_ID));
        if(verify)
        {
            scanner.reset();
            return true;
        }
        else
        {
            System.out.println("Invalid entry or password, exiting the Instrument Management System...");
            try
            {
                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            super.exitSystem();
        }
        return false;
    }

    private void Functions() throws  SQLException
    {
       this.db = new Database();
    }

    protected int getId()
    {
        return this.MEMBER_ID;
    }
}
