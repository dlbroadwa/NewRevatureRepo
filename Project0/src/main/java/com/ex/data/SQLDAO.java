package main.java.com.ex.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.ex.connections.PostgresConnection;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;

/**
 * SQLDAO: a class that allow for CRUD operations from various SQL servers
 * 
 * class variables:
 * PostgresConnection pg_connect: a connection to a postgres sql database
 * 
 * methods:
 * public User findById(String id): for a given username a single User object will be returned
 * 
 * public void save(User obj): a given user object will be created in the database
 * 
 * public void update(User newUser, String id): a user will be updated in the database with information from the newUser
 * based on the username found in id
 * 
 * public void updateBalance(String username, double newBalance): a user's balance will be updated in the database
 * based on the username found in username
 * 
 * public void updatePassword(String username, String password): a user's password will be updated in the database
 * based on the username found in username
 * 
 * public void delete(String id): a user will be deleted from the database based on the username found in id
 * 
 * 
 * @author jtb12_000
 *
 */

public class SQLDAO implements DAO<User, String> {
	
	private PostgresConnection pg_connect;
	
	public SQLDAO(PostgresConnection pg_connect) {
		if(pg_connect != null) {
			this.pg_connect = pg_connect;
		}
	}

	@Override
	public User findById(String id) {
		Connection connection = null;
		User user = null;
		try {
			connection = pg_connect.getConnection();
			String sql_query = "Select * from public.credentials where username=?";		
			PreparedStatement ps = connection.prepareStatement(sql_query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				char access = rs.getString("access_level").charAt(0);
				if(access == 'c') {
					user = new BankCustomerUser(rs.getString("first_name"),rs.getString("last_name"),id,rs.getString("password"),
							access,rs.getString("address"),rs.getString("phone_number"),rs.getDouble("account_balance"));
				}else {
					user = new BankTellerUser(rs.getString("first_name"),rs.getString("last_name"),id,rs.getString("password"),
							access,rs.getInt("auth_code"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return user;
	}

	@Override
	public void save(User obj) {
		Connection connection = null;
		try {
			char access = obj.getAccess_level();
			String addr = ((access == 'c') ? ((BankCustomerUser)obj).getAddress() : null);
			String phone_num = ((access == 'c') ? ((BankCustomerUser)obj).getPhone_number() : null);
			Double bal = ((access == 'c') ? ((BankCustomerUser)obj).getBalance() : null);
			Integer val_code = ((access == 't') ? ((BankTellerUser)obj).getValidationCode() : null);
			
			connection = pg_connect.getConnection();
			String sql_update = "insert into public.credentials(first_name,last_name,username,password,access_level,"
					+ "address,phone_number,account_balance,auth_code) values (?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql_update);
			ps.setString(1, obj.getFirst_name());
			ps.setString(2, obj.getLast_name());
			ps.setString(3, obj.getUsername());
			ps.setString(4, obj.getPassword());
			ps.setString(5, Character.toString(access));
			ps.setString(6, addr);
			ps.setString(7, phone_num);
			ps.setObject(8, bal);
			ps.setObject(9, val_code);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void update(User newObj, String id) {
		Connection connection = null;
		try {
			char access = newObj.getAccess_level();
			String addr = ((access == 'c') ? ((BankCustomerUser)newObj).getAddress() : null);
			String phone_num = ((access == 'c') ? ((BankCustomerUser)newObj).getPhone_number() : null);
			Double bal = ((access == 'c') ? ((BankCustomerUser)newObj).getBalance() : null);
			Integer val_code = ((access == 't') ? ((BankTellerUser)newObj).getValidationCode() : null);
			
			connection = pg_connect.getConnection();
			String sql_update = "update public.credentials set first_name=?, last_name=?, username=?, password=?, access_level=?, "
					+ "address=?, phone_number=?, account_balance=?, auth_code=? where username=?";
			PreparedStatement ps = connection.prepareStatement(sql_update);
			ps.setString(1, newObj.getFirst_name());
			ps.setString(2, newObj.getLast_name());
			ps.setString(3, newObj.getUsername());
			ps.setString(4, newObj.getPassword());
			ps.setString(5, Character.toString(access));
			ps.setString(6, addr);
			ps.setString(7, phone_num);
			ps.setObject(8, bal);
			ps.setObject(9, val_code);
			ps.setString(10, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void updateBalance(String username, double newBalance) {
		Connection connection = null;
		try {
			connection = pg_connect.getConnection();
			String sql_update = "update public.credentials set account_balance=? where username=?";
			PreparedStatement ps = connection.prepareStatement(sql_update);
			ps.setDouble(1, newBalance);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void updatePassword(String username, String password) {
		Connection connection = null;
		try {
			connection = pg_connect.getConnection();
			String sql_update = "update public.credentials set password=? where username=?";
			PreparedStatement ps = connection.prepareStatement(sql_update);
			ps.setString(1, password);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}

	}

	@Override
	public void delete(String id) {
		Connection connection = null;
		try {
			connection = pg_connect.getConnection();
			String sql_update = "delete from public.credentials where username=?;";
			PreparedStatement ps = connection.prepareStatement(sql_update);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
