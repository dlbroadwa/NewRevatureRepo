package com.Project0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionUtil {
    static {
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return defaultSchema;
    }
}
