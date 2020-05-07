package dev.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Project 1:<br>
 * <br>
 *  The PostgresConnectionUtil class serves as an means to contact an existing postgresql database instance in order to
 *    retrieve data for the Shelter application.
 *
 *  <br> <br>
 *  Created: <br>
 *     25 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     25 April 2020, Barthelemy Martinon,    Created class.
 *                                            Prototyped Constructors and getter.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 25 April 2020
 */
public class PostgresConnectionUtil extends ConnectionUtil {

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PostgresConnectionUtil() {
        this.defaultSchema = "project1schema";
    }

    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

