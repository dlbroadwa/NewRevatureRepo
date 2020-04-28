package gradebook.dao;

import gradebook.models.Course;

public interface CoursesDAO {
	Course getCourseById(String course_id);
	Course getCoursesByTeacher(String teacher_id);
}
