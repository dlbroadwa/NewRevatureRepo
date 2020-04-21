package com.Project0.utilities;


import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtilities extends ConnectionUtilities
{
    String table;
    public PostgresConnectionUtilities()
    {
        this.url = System.getenv("location");
        this.username = System.getenv("username");
        this.password = System.getenv("password");
        //this.schema = System.getenv("schema");
        this.schema = "project0";
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(this.url,this.username,this.password);
    }
    static
    {
        try
        {
            DriverManager.registerDriver(new Driver());
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }

    }
}

