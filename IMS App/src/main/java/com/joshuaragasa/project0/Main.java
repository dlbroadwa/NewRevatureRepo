/**
 * This is the main class for initializing the IMS (Instrument Management System) Application
 * Since this class is the initializing class and eventually ends up accessing a database,
 * It has the possibility of throwing an IOException / SQL Exception
 * File I/O is NOT incorporated to the release of this app, but it includes the scripts/functions
 * that COULD be accessed if need be.
 *
 * The Main Method initializes the application by creating a new IMS Object
 * followed by the calling of the IMS Object's run method.
 */
package com.joshuaragasa.project0;

// Retrieves the Application Entry Object
import app.IMSEntry;

// Retrieves possible Exceptions
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    // IMS Initialization
    public static void main(String[] args) throws IOException, SQLException
    {
        IMSEntry app = new IMSEntry();
        app.run();
    }
}
