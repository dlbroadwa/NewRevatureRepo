package com.ex.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection extends DatabaseConnection {
    static private boolean driverRegisterStatus = false;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            driverRegisterStatus = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PostgreSQLConnection() {}
    public PostgreSQLConnection(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema = schema;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public boolean isDriverInitialized() {
        return driverRegisterStatus;
    }
}
