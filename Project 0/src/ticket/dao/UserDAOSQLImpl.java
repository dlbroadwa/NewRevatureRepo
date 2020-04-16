package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ticket.model.User;
import ticket.utilities.ConnectionUtil;

public class UserDAOSQLImpl implements UserDAO {

	private ConnectionUtil connectionUtil;
	Connection connection = null;
	PreparedStatement statement = null;
	
	public UserDAOSQLImpl (ConnectionUtil connectionUtil) {
		if (connectionUtil != null) {
			this.connectionUtil = connectionUtil;
		}
	}

	@Override
	public User getUser(String user_id) {
		
		User user = null;
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT * FROM public.users WHERE user_id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String id = rs.getString("user_id");
				String pass = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				user = new User(id, pass, first_name, last_name, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return user;
	}

	@Override
	public boolean addUser(User user) {
		boolean result = false;
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "INSERT INTO public.users VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getId());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			if (statement.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		try {
			connection = connectionUtil.getConnection();
			String sql = "UPDATE public.users SET password=?, first_name=?, last_name=?, email=? WHERE user_id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getId());
			if (statement.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	@Override
	public boolean deleteUser(String user_id) {
		boolean result = false;
		try {
			connection = connectionUtil.getConnection();
			String sql = "DELETE public.users WHERE user_id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user_id);
			if (statement.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}
	
	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
