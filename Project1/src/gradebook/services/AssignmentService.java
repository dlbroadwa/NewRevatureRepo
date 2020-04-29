package gradebook.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gradebook.dao.SubmissionsDAO;
import gradebook.models.Assignment;
import gradebook.models.Submission;

public class AssignmentService {
	
	private SubmissionsDAO submitDao;
	
	public AssignmentService(SubmissionsDAO submitDao) {
		this.submitDao = submitDao;
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
	
	public List<Submission> getSubmissions(String student_id, String course_id) {
		List<Submission> submissions;
		submissions = submitDao.getAllStudentSubmissions(student_id, course_id);
		return submissions;
	}
	
	public List<Submission> getSubmissions(int assignment_id) {
		List<Submission> submissions;
		submissions = submitDao.getAllSubmissions(assignment_id);
		return submissions;
	}
	
	public double calculateGrade(Assignment assignment, Submission submission) {
		if(submission.getPoints() >= 0) {//Checks to see if the submission has a valid grade
			return (submission.getPoints() / assignment.getPoints()) * 100;
		}
		return -1; //If submission has not yet been graded then the grade is returned as a negative number
	}
}
