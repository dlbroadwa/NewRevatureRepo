package gradebook.services;

import java.time.LocalDateTime;
import java.util.List;

import gradebook.dao.AssignmentsDAO;
import gradebook.dao.AssignmentsSQLDAO;
import gradebook.dao.SubmissionsDAO;
import gradebook.dao.SubmissionsSQLDAO;
import gradebook.models.Assignment;
import gradebook.models.Submission;

/**
 * AssignmentService:
 * 
 * class variables
 * private SubmissionsDAO submitDao: the data repository access object that returns submission data
 * 
 * 
 * methods
 * public boolean createAssignment(String name, String body, int points, LocalDateTime dueDate):
 * 
 * public List<Assignment> getAssignments(String course_id):
 * 
 * public boolean submitAssignment(int assignment_id, String course_id, String student_id, String file):
 * 
 * public List<Submission> getSubmissions(String student_id, String course_id):
 * 
 * public List<Submission> getSubmissions(int assignment_id):
 * 
 * public double calculateGrade(Assignment assignment, Submission submission):
 * 
 * 
 * @author Austin Kind
 * @author Joshua Brewer
 *
 */
public class AssignmentService {
	
	private SubmissionsDAO submitDao;
	private AssignmentsDAO assignmentDAO;
	
	public AssignmentService() {
		this.submitDao = SubmissionsSQLDAO.getInstance();
		this.assignmentDAO = AssignmentsSQLDAO.getInstance();
	}
	
	/*
	 * This constructor is used only for testing
	 */
	public AssignmentService(SubmissionsDAO submitDao, AssignmentsDAO assignmentDao) {
		this.submitDao = submitDao;
		this.assignmentDAO = assignmentDao;
	}
	
	public boolean createAssignment(String courseId, String name, String body, int points, LocalDateTime dueDate) {
		int assignmentId = assignmentDAO.getNextId();
		Assignment assignment = new Assignment(assignmentId, courseId, name, body, points, dueDate);
		return assignmentDAO.addAssignment(assignment);
	}
	
	public List<Assignment> getAssignments(String course_id) {
		List<Assignment> assignments = assignmentDAO.getAssignmentsByCourse(course_id);
		return assignments;
	}
	
	public boolean submitAssignment(int assignment_id, String course_id, String student_id, String file) {
		Submission submission = new Submission(assignment_id, course_id, student_id, file);
		return submitDao.addSubmission(submission);
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
