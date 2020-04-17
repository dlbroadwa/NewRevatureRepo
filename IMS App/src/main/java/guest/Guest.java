package guest;
import admin.Admin;
import app.Application;
import database.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Guest extends Application
{
    public Guest() throws SQLException, IOException
    {
        Greetings();
    }
    private void Relocate() throws IOException, SQLException {
        System.out.println( "================================================================================\n"+
                            "               Would you like to continue browsing?\n" +
                            "           Password is needed to access Admin functions\n" +
                            "                       [browse, admin, exit]\n" +
                            "================================================================================");
        Scanner scanner = super.getScanner();
        String choice = scanner.next().toLowerCase();
        if ("browse".equals(choice))
        {
            Guest guest = new Guest();
        }
        else if("admin".equals(choice))
        {
            Admin admin = new Admin();
        }
        super.exitSystem();
    }

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
