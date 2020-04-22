package main.java.com.services;

import main.java.com.ex.data.DAO;
import main.java.com.ex.data.SQLDAO;
import main.java.com.ex.users.User;

/**
 * AccountsService: service that controls the CRUD operations controlled on the data repository for the screens of the application
 * 
 * class variables
 * private DAO<User, String> dao: the data repository access object that will allow for data persistance
 * 
 * methods
 * public void createNewUser(User user): this method creates a new user in the database
 * 
 * public User updateUserInfo(User user, String username): this method updates a user in the database and returns the new User object
 * 
 * public User updatePassword(User user, String password): this method updates a password in the database and returns the
 * new User object
 * 
 * public void deleteUser(String username): this method deletes a user from the database
 * 
 * @author jtb12_000
 *
 */

public class AccountsService {
	private DAO<User, String> dao;
	
	public AccountsService(DAO<User,String> dao) {
		this.dao = dao;
	}
	
	public void createNewUser(User user){
		dao.save(user);
	}
	
	public User updateUserInfo(User user, String username) {
		dao.update(user, username);
		return dao.findById(user.getUsername());
	}

	public User updatePassword(User user, String password) {
		((SQLDAO)dao).updatePassword(user.getUsername(), password);
		return dao.findById(user.getUsername());
	}
	
	public void deleteUser(String username) {
		dao.delete(username);
	}
}
