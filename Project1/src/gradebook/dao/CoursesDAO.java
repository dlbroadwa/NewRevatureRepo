package gradebook.dao;

import java.util.List;

import gradebook.models.Course;

/**
 * CoursesDAO --- Accesses data from a courses table.
 * @author Austin Kind
 */
public interface CoursesDAO {
	
	/**
	 * Retrieves a course matching the given course ID.
	 * @param course_id		The ID of the desired course.
	 * @return				The course desired.
	 */
	Course getCourseById(String course_id);
	
	/**
	 * Retrieves all courses taught be the given teacher.
	 * @param teacher_id	The ID of the given teacher.
	 * @return				A list of courses taught by the given teacher.
	 */
	List<Course> getCoursesByTeacher(String teacher_id);
}
