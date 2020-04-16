package dbutility;

import java.sql.*;

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
