package gradebook.services;

import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.dao.UserDAO;
import gradebook.models.User;
import gradebook.models.Teacher_User;
import gradebook.models.Student_User;
import gradebook.util.Regex;

public class ChangePasswordService {
	
	private UserDAO<User,String> teacherDAO;
	private UserDAO<User,String> studentDAO;
	
	public ChangePasswordService() {
		this.teacherDAO = TeacherSQLDAO.getInstance();
		this.studentDAO = StudentSQLDAO.getInstance();
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
