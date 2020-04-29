package gradebook.dao;

import java.util.List;

import gradebook.models.Enrollment;

/**
 * EnrollmentDAO --- Accesses data from an enrollment table.
 * @author Austin Kind
 */
public interface EnrollmentDAO {
	
	/**
	 * Retrieves all enrollments from the given course ID.
	 * @param course_id		The ID of the course.
	 * @return				A list of enrollments for given course.
	 */
	List<Enrollment> getEnrollmentByCourseId(String course_id);
	
	/**
	 * Retrieves all enrollments from the given student ID.
	 * @param student_id	The ID of the student.
	 * @return				A list of enrollments for given student.
	 */
	List<Enrollment> getEnrollmentByStudentId(String student_id);
}
