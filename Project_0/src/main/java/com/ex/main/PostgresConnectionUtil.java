package com.ex.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
*The PostgresConnectionUtil is used to establish the DriverManager is registered to the Postgresql Driver
* and set up the connection
*/

public class PostgresConnectionUtil extends Runner {

//Must be established first to ensure connection can be made to the database
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//Constructor
    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;
    }

    public PostgresConnectionUtil() {
        this.defaultSchema = "public";
    }

//Getters
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

//Methods
    public void run() {

    }

}