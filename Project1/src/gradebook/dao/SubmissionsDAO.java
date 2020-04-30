package gradebook.dao;

import java.util.List;

import gradebook.models.Submission;

public interface SubmissionsDAO {
	List<Submission> getAllSubmissions(int assignment_id);
	List<Submission> getAllStudentSubmissions(String student_id, String course_id);
	Submission getSubmission(String student_id, String assignment_id);
	boolean addSubmission(Submission submission);
	boolean updateSubmission(Submission submission);
}
