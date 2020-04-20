/**
 * The Guest Class provides a guest user the option to look through
 * the stock. Unlike the Admin class, the guest can only browse the instruments
 * while Admins can remove, add, and view the instruments.
 *
 * functions:
 *
 * Greetings(): This function prompts the user to either browse the instruments by
 * connecting to a database object or exit the program.
 *
 * Relocation(): This function is called after the guest has browsed through the stock,
 * it then prompts the user to either continue browsing, switch to Admin (providing the know the admin id#),
 * or exit the program.
 */


package guest;

// Imports appropriate Classes that deal with user input, the database and it's appropriate Exceptions
import admin.Admin;
import app.Application;
import database.Database;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Guest extends Application
{
    // Constructor calling the Greetings Function
    public Guest() throws SQLException, IOException
    {
        Greetings();
    }

    // Relocate() is performed after the user has browsed, prompting
    // the user new options
    private void Relocate() throws IOException, SQLException {
        System.out.println( "================================================================================\n"+
                            "               Would you like to continue browsing?\n" +
                            "           Password is needed to access Admin functions\n" +
                            "                       [yes, admin, exit]\n" +
                            "================================================================================");
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if ("yes".equals(choice))
        {
            Guest guest = new Guest();
        }
        else if("admin".equals(choice))
        {
            Admin admin = new Admin();
        }
        super.exitSystem();
    }

    // Greetings() prompts the user to browse the stock or exit the program.
    private void Greetings() throws SQLException, IOException {
        System.out.println("================================================================================\n"+
                "           Welcome guest! Feel free to look around.\n" +
                "                       [browse, exit]\n" +
                "================================================================================");
        Scanner scanner = super.getScanner();
        String browse = "browse";
        String choice = scanner.next();
        if(choice.equals(browse))
        {
            Database db = new Database(this);
            Relocate();
        }
        else
        {
            super.exitSystem();
        }
    }
}
