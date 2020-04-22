package dbutility;

import java.sql.*;

/**
 * Abstract class for connecting to SQL databases. Can be utilized for various SQL extensions
 */
public abstract class ConnectionDBUtility {
    protected String url;
    protected String username;
    protected String password;
    protected String defaultSchema;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return defaultSchema;
    }
}
