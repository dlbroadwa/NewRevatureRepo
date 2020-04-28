package com.ex.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract class representing a connection to a SQL database.
 * Created by Perry Lee on April 28,2020
 */
public abstract class DatabaseConnection {
    protected String url;
    protected String username;
    protected String password;
    protected String schema;

    public abstract Connection getConnection() throws SQLException;

    public String getSchema() {
        return schema;
    }

    public abstract boolean isDriverInitialized();
}
