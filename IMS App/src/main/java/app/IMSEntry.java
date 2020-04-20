/**
 * The IMSEntry class acts as an entry point for deciding whether the person.
 * accessing the database is a Guest or an Admin.
 * Admins have more choices regarding manipulating the database (adding and removing instruments).
 * Guests only have the ability to view what is in stock.
 *
 * functions:
 * run(): Takes in a user input to properly navigate Guest or Admin functionalities.
 * @exceptions May throw IOException, SQLException due to the general implementation of a database.
 *
 */
package app;

// Imports necessary Classes for input, relocation, and exceptions.
import admin.Admin;
import guest.Guest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class IMSEntry extends Application
{
    private String[] choices = {"guest", "admin", "exit"};
    private Scanner scanner = super.getScanner();
    private Guest guest;
    private Admin admin;

    // Relocates to either Guest or Admin functionality
    public void run() throws IOException, SQLException {
        String greeting =
                "=================================================================================\n" +
                "             Welcome to the Instrument Management System\n" +
                        "                   Are you a guest or admin?\n" +
                        "                       [guest, admin, exit]\n" +
                "=================================================================================";
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
            exitSystem();
        }
    }
}
