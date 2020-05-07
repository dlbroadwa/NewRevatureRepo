package gradebook.services;

import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.dao.UserDAO;
import gradebook.models.User;
import gradebook.models.Teacher_User;
import gradebook.models.Student_User;
import gradebook.util.Encryption;
import gradebook.util.Regex;

public class ChangePasswordService {
	
	private UserDAO<User,String> teacherDAO;
	private UserDAO<User,String> studentDAO;
	
	public ChangePasswordService() {
		this.teacherDAO = TeacherSQLDAO.getInstance();
		this.studentDAO = StudentSQLDAO.getInstance();
	}
	
	/*
	 * This constructor is used only for testing
	 */
	public ChangePasswordService(UserDAO<User,String> teacherDao, UserDAO<User,String> studentDao) {
		this.teacherDAO = teacherDao;
		this.studentDAO = studentDao;
	}
	
	public boolean changePassword(User user, String newPassword) {
		if (Regex.isValidPassword(newPassword)) {
			user.setPassword(newPassword);
			if (user instanceof Teacher_User) {
				teacherDAO.updateUser(user);
				return true;
			} else if (user instanceof Student_User) {
				studentDAO.updateUser(user);
				return true;
			}
		}
		return false;
	}
}
