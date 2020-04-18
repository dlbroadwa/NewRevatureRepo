package com.inventory.controller.services.connect;

import com.inventory.controller.system.ConsoleOut;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class PostgresSQLConnection {
    PostgresSQLConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (Exception e) {
            ConsoleOut.println("Unable to register the required database driver. Program will shut down.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    void connect() throws SQLException
    {
        for (int i = 0; i < 5; i++) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                return;
            } catch (Exception e) {
                ConsoleOut.println("Establishing a connection to \"" + url + "\" failed. " +
                        "Retrying. Connection attempts remaining: " + (4 - i) + ".");
            }
        }
        throw new SQLException();
    }

    Connection getConnection() {
        if(connection != null)
            return connection;
        throw new NullPointerException();
    }
}
