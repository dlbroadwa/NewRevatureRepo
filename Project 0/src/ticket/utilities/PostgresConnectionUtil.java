package ticket.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgresConnectionUtil --- Establishes a connection to the Postgres database.
 * @author Austin Kind
 */
public class PostgresConnectionUtil extends ConnectionUtil {
	
	private static final String CONNECTION_USERNAME = System.getenv("CONNECTION_USERNAME");
	private static final String CONNECTION_PASSWORD = System.getenv("CONNECTION_PASSWORD");
	private static final String URL = System.getenv("CONNECTION_URL");
	private static Connection connection;
	
	static {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Establishes a connection to the Postgres database.
	 * @return 		Connection to the Postgres database.
	 */
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
