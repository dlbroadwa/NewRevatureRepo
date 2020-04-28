package gradebook.dao;

import gradebook.models.Enrollment;

public interface EnrollmentDAO {
	Enrollment getEnrollmentByCourseId(String course_id);
	Enrollment getEnrollmentStudentId(String student_id);
}
