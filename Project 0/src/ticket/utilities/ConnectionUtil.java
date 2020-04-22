package ticket.utilities;

import java.sql.Connection;

/**
 * ConnectionUtil --- Allows for a connection to be established.
 * @author Austin Kind
 */
public abstract class ConnectionUtil {
	
	protected String url;
	protected String username;
	protected String password;
	
	/**
	 * Establishes a connection.
	 * @return 		Connection.
	 */
	public abstract Connection getConnection();
	
}
