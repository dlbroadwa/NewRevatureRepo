package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ticket.model.Admin;
import ticket.utilities.ConnectionUtil;

public class AdminDAOSQLImpl implements AdminDAO {

	private ConnectionUtil connectionUtil;
	Connection connection = null;
	PreparedStatement statement = null;
	
	public AdminDAOSQLImpl (ConnectionUtil connectionUtil) {
		if (connectionUtil != null) {
			this.connectionUtil = connectionUtil;
		}
	}
	
	@Override
	public Admin getAdmin(String admin_id) {
		
		Admin admin = null;

		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT * FROM public.admins WHERE admin_id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, admin_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String id = rs.getString("admin_id");
				String pass = rs.getString("password");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				admin = new Admin(id, pass, first_name, last_name, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return admin;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		boolean result = false;
		try {
			connection = connectionUtil.getConnection();
			String sql = "UPDATE public.admins SET password=?, first_name=?, last_name=?, email=? WHERE admin_id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, admin.getPassword());
			statement.setString(2, admin.getFirstName());
			statement.setString(3, admin.getLastName());
			statement.setString(4, admin.getEmail());
			statement.setString(5, admin.getId());
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
