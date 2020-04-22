package com.proj;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.sql.SQLException;

/**
 * ******This is an event handling application for event holders or festival holders.******
 * The admins: can add or delete events, they can then look at the people who have enrolled into those events.
 * The user: can then see all of the available events and sign up for them. they can also see all of the events they
 *           have signed up for
 * author: John Sheerin
 */

public class Main {
    /**
     * The main class calls the EventHandler app to run the application
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
       EventHandler app = new EventHandler();
       app.run();
    }
}
