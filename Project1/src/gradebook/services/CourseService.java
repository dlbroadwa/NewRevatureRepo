package gradebook.services;

import java.util.ArrayList;
import java.util.List;

import gradebook.dao.CoursesDAO;
import gradebook.dao.CoursesSQLDAO;
import gradebook.dao.EnrollmentDAO;
import gradebook.dao.EnrollmentSQLDAO;
import gradebook.models.Course;
import gradebook.models.Enrollment;
import gradebook.models.Student_User;

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
	
	public CourseService(CoursesDAO courseDao, EnrollmentDAO enrollDao) {
		this.courseDao = CoursesSQLDAO.getInstance();
		this.enrollDao = EnrollmentSQLDAO.getInstance();
	}
	
	public List<Student_User> getStudentsInCourse(int course_id) {
		//TODO
		return null;
	}
	
	public List<Course> getCourses(String student_id) {
		List<Course> courses = null;
		List<Enrollment> enrollment;
		
		enrollment = enrollDao.getEnrollmentByStudentId(student_id);
		if(enrollment != null) {
			courses = new ArrayList<>();
			for(Enrollment e : enrollment) {
				courses.add(courseDao.getCourseById(e.getCourse_id()));
			}
		}
		return courses;
	}
}
