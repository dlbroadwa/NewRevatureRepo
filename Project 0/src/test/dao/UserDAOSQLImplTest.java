package test.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import ticket.dao.UserDAO;
import ticket.dao.UserDAOSQLImpl;
import ticket.model.User;
import ticket.utilities.ConnectionUtil;

public class UserDAOSQLImplTest {

	UserDAO userDAO;
	
	@Mock
	ConnectionUtil mockConnectionUtil;
	@Mock
	Connection mockConnection;
	@Mock
	PreparedStatement mockPreparedStatement;
	@Mock
	ResultSet mockResultSet;
	
	@Before
	public void setup() {
		try {
			when(mockConnectionUtil.getConnection()).thenReturn(mockConnection);
			when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
			doNothing().when(mockPreparedStatement).setString(anyInt(), anyString());
			doNothing().when(mockPreparedStatement).setInt(anyInt(), anyInt());
			when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
			when(mockResultSet.next()).thenReturn(true, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userDAO = new UserDAOSQLImpl(mockConnectionUtil);
	}
	
	@Test
	public void testAddUser() {
		User user = new User("johnny123", "41234", "Johnny", "Smith", "email@email.com", false);
		userDAO.addUser(user);
		//assertEntityCreatedInDB(user);
	}

}
