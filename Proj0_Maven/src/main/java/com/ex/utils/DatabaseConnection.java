package com.ex.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract class representing a connection to a SQL database.
 */
public abstract class DatabaseConnection {
    protected String url;
    protected String username;
    protected String password;
    protected String schema = "project0";

    public abstract Connection getConnection() throws SQLException;

    public String getSchema() {
        return schema;
    }

    public abstract boolean isDriverInitialized();
}
