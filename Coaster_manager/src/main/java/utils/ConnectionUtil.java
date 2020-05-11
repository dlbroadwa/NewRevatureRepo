package utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionUtil
 * Created by:Ray Vakil on May 11,2020
 */
public abstract class ConnectionUtil {
//Instance Variables
    protected static String url;
    protected static String username;
    protected static String password;
    protected static String defaultSchema;

//Constructors
    public abstract Connection getConnection() throws SQLException;

//Getters
    public String getDefaultSchema() {
        return defaultSchema;
    }
}
