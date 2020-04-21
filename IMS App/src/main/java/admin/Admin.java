/**
 * The Admin Class provides functions that allow a person
 * to manipulate the PostgreSQL Database.
 *
 * functions:
 *
 * idVerification(): This function prompts the user to enter in
 * a static final variable that holds a 9-digit id number. The user
 * has access only if the idVerification returns true. Anything else
 * exits the program automatically.
 *
 * Functions(): Creates and relocates to a new database object to perform admin functions. Can only be acesses if
 * 1. You are an admin
 * 2. You verify the id#
 *
 * Relocate(): This function is called only after the verification returns true and after Functions() has completed.
 * Depending on the input, will create a new Admin or Guest.
 *
 */
package admin;

// imports proper classes to be used for user input, accessing database, and I/O / SQL Exceptions
import app.Application;
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
    private Application app;

    // The Constructor calls its internal functions to provide relocation functionality.
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
            app.exitSystem();
        }
    }

    // This function allows you to access Admin / Guest functionality after you have already performed an action
    private void Relocate() throws IOException, SQLException {
        System.out.println("================================================================================");
        System.out.println("Would you like to use another Admin function, move to being a guest, or exit?\n" +
                "                       [admin, guest, exit]");
        System.out.println("================================================================================\n");
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

    // Verifies if you are an Admin or not. If not, the program automatically exits.
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

    // This function creates a new database object to be used.
    private void Functions() throws  SQLException
    {
       this.db = new Database();
    }
}
