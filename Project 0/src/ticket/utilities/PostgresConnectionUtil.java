package ticket.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class PostgresConnectionUtil extends ConnectionUtil {
	
	private static final String CONNECTION_USERNAME = "master_admin";
	private static final String CONNECTION_PASSWORD = "this_is_the_password";
	private static final String URL = "jdbc:postgresql://ticketing-system.cby99r2xyn8t.us-east-2.rds.amazonaws.com:5432/postgres";
	private static Connection connection;
	
	static {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Connection getConnection() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			if (connection.isClosed()) {
				connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
