package gradebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gradebook.models.Student_User;
import gradebook.models.User;
import gradebook.util.ConnectionProvider;

/**
 * StudentSQLDAO: a class that allows CRUD operations on a student table for various SQL database types
 * 
 * class variables
 * private Connection connection: a connection to a SQL database
 * 
 * private PreparedStatement ps: a prepared SQL statement used to query a SQL database
 * 
 * methods
 * public User getUser(String user_id): this method accepts the user_id and returns a User object if the id is valid and
 * null if the id is invalid
 * 
 * public boolean updateUser(User user): this method accepts a User object an updates the values in the database for
 * a student with the matching student_id. It returns true if the update was successfully completed and false if the update did
 * not complete.
 * 
 * private void closeResources(void): this method closes the connection and prepared statement if they are not null
 * 
 * 
 * @author Joshua Brewer
 *
 */
public class StudentSQLDAO implements UserDAO<User,String> {
	
	private Connection connection;
	private PreparedStatement ps;

	@Override
	public User getUser(String user_id) {
		User user = null;
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "select * from public.students where student_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setString(1, user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new Student_User(rs.getString("student_id"), rs.getString("first_name"), rs.getString("last_name"),
										rs.getString("email"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean success = false;
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "update public.students set first_name=?, last_name=?, email=?, password=? where student_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, ((Student_User)user).getStudent_id());
			if(ps.executeUpdate() == 1) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return success;
	}
	
	private void closeResorces() {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
