package com.ex.main;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Runner {//Start of the Runner Class

//Instant Variables
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema;

//Methods
    public abstract void run();

//Getters
    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return this.defaultSchema;
    }

}//End of the Runner Class
