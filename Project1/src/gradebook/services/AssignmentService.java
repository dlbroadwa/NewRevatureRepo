package gradebook.services;

import java.time.LocalDateTime;
import java.util.List;

import gradebook.models.Assignment;
import gradebook.models.Submission;

public class AssignmentService {
	
	public AssignmentService() {
		//TODO
	}
	
	public Assignment createAssignment(String name, String body, int points, LocalDateTime dueDate) {
		//TODO
		return null;
	}
	
	public List<Assignment> getAssignments(int course_id) {
		//TODO
		return null;
	}
	
	public boolean submitAssignment(int course_id, String student_id, String file) {
		//TODO
		return false;
	}
	
	public List<Submission> getSubmissions(String student_id) {
		//TODO
		return null;
	}
	
	public List<Submission> getSubmissions(int assignment_id) {
		//TODO
		return null;
	}
	
	public double calculateGrade(Assignment assignment, Submission submission) {
		//TODO
		return -1;
	}
}
