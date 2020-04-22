package main.java.com.services;

import main.java.com.ex.data.DAO;
import main.java.com.ex.users.User;

/**
 * ValidationService: a class that validates user input with the database
 * 
 * class variables
 * private DAO<User, String> dao: the data repository access object that will allow for data persistance
 * 
 * methods
 * public User validate_username_password(String username, String password): a method that compares user inputs for
 * username and password to the data repository and returns a User object if valid and null if invalid
 * 
 * public User validate_username(String username): a method that compares user input for username to the data repository
 * and returns a User object and null if invalid
 * 
 * @author jtb12_000
 *
 */

public class ValidationService {
	
	private DAO<User, String> dao;
	
	public ValidationService(DAO<User,String> dao) {
		this.dao = dao;
	}
	
	public User validate_username_password(String username, String password) {
		User user = dao.findById(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	public User validate_username(String username) {
		return dao.findById(username);
	}
}
