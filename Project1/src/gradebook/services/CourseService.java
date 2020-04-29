package gradebook.services;

import java.util.ArrayList;
import java.util.List;

import gradebook.dao.CoursesDAO;
import gradebook.dao.EnrollmentDAO;
import gradebook.models.Course;
import gradebook.models.Enrollment;
import gradebook.models.Student_User;

public class CourseService {
	
	private CoursesDAO courseDao;
	private EnrollmentDAO enrollDao;
	
	public CourseService(CoursesDAO courseDao, EnrollmentDAO enrollDao) {
		this.courseDao = courseDao;
		this.enrollDao = enrollDao;
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
