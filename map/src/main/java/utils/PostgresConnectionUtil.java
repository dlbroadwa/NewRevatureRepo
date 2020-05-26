package utils;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgresConnectionUtil is a child class of the ConnectionUtils
 * it actually create the form for its parents
 * for the class connectivity
 * using Url, username, password,
 * also get the the defaultSchema
 */
public class PostgresConnectionUtil extends ConnectionUtils {
    private final Logger logger = Logger.getLogger(PostgresConnectionUtil.class);

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public PostgresConnectionUtil(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = "public";
    }

    public PostgresConnectionUtil(String url, String username, String password, String schema) {
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

/**
 * End of this class
 */
