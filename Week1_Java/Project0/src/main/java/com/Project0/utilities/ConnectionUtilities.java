package com.Project0.utilities;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtilities
{
    protected String url;
    protected String username;
    protected String password;
    protected String schema;

    public ConnectionUtilities()
    {
    }
    public abstract Connection getConnection() throws SQLException;

    public String getSchema()
    {
        //System.out.println("Got schema named: "+this.schema);
        return this.schema;
    }
}
