package gradebook.app;

import gradebook.dao.AssignmentsDAO;
import gradebook.dao.CoursesDAO;
import gradebook.dao.EnrollmentDAO;
import gradebook.dao.SubmissionsDAO;
import gradebook.dao.UserDAO;
import gradebook.util.ConnectionUtil;
import gradebook.util.PostgresConnectionUtil;

public class GradeBookApplication extends Application {
	
	AssignmentsDAO assignmentsDAO;
	CoursesDAO coursesDAO;
	EnrollmentDAO enrollmentDAO;
	UserDAO studentsDAO;
	SubmissionsDAO submissionsDAO;
	UserDAO teacherDAO;
	ConnectionUtil connectionUtil;
	
	public GradeBookApplication() {
		connectionUtil = new PostgresConnectionUtil();
	}
	
	public void run() {
		
	}
	
	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}
}
