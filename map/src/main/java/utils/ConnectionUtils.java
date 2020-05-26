package utils;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * This interface create the blue print
 * for the class connectivity
 * using Url, username, password,
 * also get the the defaultSchema
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

/**
 * End of this class
 */
