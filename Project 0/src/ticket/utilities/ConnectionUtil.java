package ticket.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionUtil {
	
	protected String url;
	protected String username;
	protected String password;
	protected String defaultSchema;
	
	public abstract Connection getConnection();
	
	public String getDefaultSchema() {
		return this.defaultSchema;
	}
}
