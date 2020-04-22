package com.proj.utils;

import java.sql.Connection;
import java.sql.SQLException;
//************************Connection Utility for Connecting to AWS RDB*************************//
/**
 * This is a constructor for connecting to the AWS RDB the variables listed are all nessesary to
 * connect to the database
 */
public abstract class ConnectionUtils {
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema ;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return this.defaultSchema;
    }
}
