package com.company.DAO.utils.servletsConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSQLConnection {
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
            System.out.println("Unable to register the required database driver. Program will shut down.");
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
                System.out.println("Establishing a connection to \"" + url + "\" failed. " +
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
