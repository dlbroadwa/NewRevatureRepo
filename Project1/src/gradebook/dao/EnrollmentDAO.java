package gradebook.dao;

import java.util.List;

import gradebook.models.Enrollment;

public interface EnrollmentDAO {
	List<Enrollment> getEnrollmentByCourseId(String course_id);
	List<Enrollment> getEnrollmentByStudentId(String student_id);
}
