package gradebook.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				conn = new PostgresConnectionUtil().getConnection();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
