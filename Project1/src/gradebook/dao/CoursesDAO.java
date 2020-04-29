package gradebook.dao;

import java.util.List;

import gradebook.models.Course;

public interface CoursesDAO {
	Course getCourseById(String course_id);
	List<Course> getCoursesByTeacher(String teacher_id);
}
