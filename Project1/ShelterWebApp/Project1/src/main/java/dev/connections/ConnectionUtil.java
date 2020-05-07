package dev.connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  Project 1:<br>
 * <br>
 *  The ConnectionUtil class serves as an abstract template for database dev.connections.
 *  Depending on the kind of ConnectionUtil with the appropriate Repository, instances of its subclasses will serve
 *    as a means to establish a real-time connection with an existing database.
 *
 *  <br> <br>
 *  Created: <br>
 *     25 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     25 April 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 25 April 2020
 */
public abstract class ConnectionUtil {
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema ;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return this.defaultSchema;
    }
}

