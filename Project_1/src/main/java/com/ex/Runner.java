package com.ex;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Runner Created By: Paityn Maynard on April 30, 2020
 */
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
