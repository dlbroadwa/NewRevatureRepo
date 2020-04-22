package main.java.com.ex.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgresConnection: the class that is used to create a connection to a postgres database
 * 
 * class variables
 * private String username: username used to log into database
 * private String password: password used to log into database
 * private String url: url used to connect to database
 * 
 * methods
 * public Connection connection(): this method creates the postgres database connection using the values of username,
 * password and url stored in the class
 * 
 * @author jtb12_000
 *
 */

public class PostgresConnection {
	private String username;
	private String password;
	private String url;
	
	static {
	   try {
	     DriverManager.registerDriver(new org.postgresql.Driver());
	   } catch (SQLException throwables) {
	     throwables.printStackTrace();
	   }
	 }
	
	public PostgresConnection(String url, String username, String password) {
		//this.schema = schema;
		this.url = url;
		this.username = username;
		this.password = password;

	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
