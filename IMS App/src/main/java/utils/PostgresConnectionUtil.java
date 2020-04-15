package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends ConnectionUtils
{
    static
    {
        try
        {
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public PostgresConnectionUtil(String url, String username, String password, String schema, String instrumentTable)
    {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;
        this.instrumentTable = instrumentTable;
    }

    @Override
    public Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }
}
