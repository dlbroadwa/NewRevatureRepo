package com.proj.utils;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends ConnectionUtils {

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PostgresConnectionUtil() {
        this.defaultSchema = "public";
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
