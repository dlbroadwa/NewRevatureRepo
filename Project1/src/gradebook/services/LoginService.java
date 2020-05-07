package gradebook.services;

import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.dao.UserDAO;
import gradebook.models.User;
import gradebook.util.Encryption;

/**
 * LoginService: service that validates user provided username and password values to the list of values stored in the repository
 * 
 * class variables
 * private UserDAO teacherDao: the data repository access object that returns data on teacher users
 * private UserDAO studentDao: the data repository access object that returns data on student users
 * 
 * methods
 * public User validate(String username, String password): this method accepts a username and password and returns
 * a User object if the fields are valid and null if invalid
 * 
 * @author Joshua Brewer
 *
 */
public class LoginService {
	
	private UserDAO<User,String> teacherDao;
	private UserDAO<User,String> studentDao;
	
	public LoginService() {
		this.teacherDao = TeacherSQLDAO.getInstance();
		this.studentDao = StudentSQLDAO.getInstance();
	}
	
	
	/*
	 * Constructor used for testing purposes.
	 */
	public LoginService(UserDAO<User,String> teacherDao, UserDAO<User,String> studentDao) {
		this.teacherDao = teacherDao;
		this.studentDao = studentDao;
	}
	
	public User validate(String username, String password) {
		User user = null;
		user = teacherDao.getUser(username);
		if(user != null) {
			if(!user.getPassword().equals(Encryption.encrypt(password))) {
				user = null;
			}
		}else {
			user = studentDao.getUser(username);
			if(user != null && !user.getPassword().equals(Encryption.encrypt(password))) {
				user = null;
			}
		}
		return user;
	}
	
	public User getUser(String username) {
		User user = null;
		user = teacherDao.getUser(username);
		if (user != null)
			return user;
		else {
			user = studentDao.getUser(username);
		}
		return user;
	}
}
