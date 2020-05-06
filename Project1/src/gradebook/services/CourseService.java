package gradebook.services;

import java.util.ArrayList;
import java.util.List;

import gradebook.dao.CoursesDAO;
import gradebook.dao.CoursesSQLDAO;
import gradebook.dao.EnrollmentDAO;
import gradebook.dao.EnrollmentSQLDAO;
import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.dao.UserDAO;
import gradebook.models.Course;
import gradebook.models.Enrollment;
import gradebook.models.Student_User;
import gradebook.models.User;

/**
 * CourseService: a service that allows access to data on the courses provided by a school
 * 
 * class variables
 * private CoursesDAO courseDao: the data repository access object that returns course data
 * 
 * private EnrollmentDAO enrollDao: the data repository access object that returns enrollment data
 * 
 * methods
 * public List<Student_User> getStudentsInCourse(int course_id):
 * 
 * public List<Course> getCourses(String student_id): this method accepts a studet_id and returns every course in which
 * that student is enrolled
 * 
 * 
 * @author Austin Kind
 * @author Joshua Brewer
 *
 */
public class CourseService {
	
	private CoursesDAO courseDao;
	private EnrollmentDAO enrollDao;
	private UserDAO<User,String> teacherDao;
	private UserDAO<User,String> studentDao;
	
	public CourseService() {
		this.courseDao = CoursesSQLDAO.getInstance();
		this.enrollDao = EnrollmentSQLDAO.getInstance();
		this.studentDao = StudentSQLDAO.getInstance();
		this.teacherDao = TeacherSQLDAO.getInstance();
	}
	
	/*
	 * This constructor is used only for testing
	 */
	public CourseService(CoursesDAO courseDao, EnrollmentDAO enrollmentDao, UserDAO<User,String> studentDao, UserDAO<User,String> teacherDao) {
		this.courseDao = courseDao;
		this.enrollDao = enrollmentDao;
		this.studentDao = studentDao;
		this.teacherDao = teacherDao;
	}
	
	public List<User> getStudentsInCourse(String course_id) {
		List<User> students = new ArrayList<>();
		List<Enrollment> enrollment = enrollDao.getEnrollmentByCourseId(course_id);
		if(enrollment != null) {
			for(Enrollment e : enrollment) {
				students.add(studentDao.getUser(e.getStudent_id()));
			}
		}
		return students;
	}
	
	public List<Course> getCourses(String user_id) {
		List<Course> courses = null;
		List<Enrollment> enrollment;
		
		User user = studentDao.getUser(user_id);
		if (user != null) {
			enrollment = enrollDao.getEnrollmentByStudentId(user_id);
			if(enrollment != null) {
				courses = new ArrayList<>();
				for(Enrollment e : enrollment) {
					courses.add(courseDao.getCourseById(e.getCourse_id()));
				}
			}
		} else {
			user = teacherDao.getUser(user_id);
			if (user != null)
				courses = courseDao.getCoursesByTeacher(user_id);
		}
		return courses;
	}
	
	public Course getCourse(String course_id) {
		Course course = null;
		course = courseDao.getCourseById(course_id);
		return course;
	}
}
