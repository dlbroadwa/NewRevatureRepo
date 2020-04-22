package dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgreSQL specific connection utility. Extends the Abstract Class for connecting to a SQL database.
 */

public class PostgresConnectionUtility extends ConnectionDBUtility {
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

/*    public PostgresConnectionUtility() {
        this.defaultSchema = "public";
    }*/

    public PostgresConnectionUtility(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
