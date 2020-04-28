package gradebook.dao;

import java.util.List;

import gradebook.models.SubmittedAssignment;

public interface SubmissionsDAO {
	List<SubmittedAssignment> getAllSubmissions(String assignment_id);
	List<SubmittedAssignment> getAllStudentSubmissions(String student_id, String course_id);
	SubmittedAssignment getSubmission(String student_id, String assignment_id);
	void addSubmission(SubmittedAssignment submission);
	void updateSubmission(SubmittedAssignment submission);
}
