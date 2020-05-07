package test.java.servicetests;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import gradebook.dao.AssignmentsSQLDAO;
import gradebook.dao.SubmissionsSQLDAO;
import gradebook.models.Assignment;
import gradebook.models.Submission;
import gradebook.services.AssignmentService;

public class AssignmentServiceTest {
	AssignmentService as;
	InputStream stream = null;
	LocalDateTime dueDate0 = LocalDateTime.of(2020, Month.MAY, 8, 23, 59);
	LocalDateTime dueDate1 = LocalDateTime.of(2020, Month.JUNE, 4, 23, 59);
	Assignment testAssignment0 = new Assignment(1, "Test Class 1", "test assignment 1", "write how this test works", 100, dueDate0);
	Assignment testAssignment1 = new Assignment(2, "Test Class 1", "test assignment 2", "write how this test works", 300, dueDate1);
	Assignment testAssignment2 = new Assignment(3, "Test Class 2", "test assignment 1", "write how this test works", 50, dueDate0);
	Submission testSubmission0 = new Submission(1, "Test Class 1", "asmith", stream, "filename.txt");
	Submission testSubmission1 = new Submission(2, "Test Class 1", "asmith", stream, 89, "Nice work!", dueDate0, "filename0.txt");
	Submission testSubmission2 = new Submission(2, "Test Class 1", "bsmith", stream, "filename2.txt");
	Submission testSubmission3 = new Submission(3, "Test Class 2", "asmith", stream, "filename2.txt");
	List<Assignment> assignments = new ArrayList<>();
	List<Submission> submissions = new ArrayList<>();
	
	@Mock
	AssignmentsSQLDAO assignmentDao;
	
	@Mock
	SubmissionsSQLDAO submitDao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	    as = new AssignmentService(submitDao, assignmentDao);
	    assignments.add(testAssignment0);
	    assignments.add(testAssignment1);
	    assignments.add(testAssignment2);
	    submissions.add(testSubmission0);
	    submissions.add(testSubmission1);
	    submissions.add(testSubmission2);
	}
	
	@Test
	public void shouldCreateNewAssignment() {
	    // ask the service to create a new assignment
	    // assert that the assignment was created
		Mockito.when(assignmentDao.getNextId()).thenReturn(1);
		Mockito.when(assignmentDao.addAssignment(Mockito.any(Assignment.class))).thenReturn(true);
	    boolean actual = as.createAssignment(testAssignment0.getCourse_id(), testAssignment0.getName(), testAssignment0.getBody(),
	    										testAssignment0.getPoints(), testAssignment0.getDueDate());
	    Assert.assertTrue(actual);
	}
	
	@Test
	public void shouldReturnAListOfAssignments() {
	    // ask the service find all assignments with a valid courseId
	    // assert that correct assignments were returned
		List<Assignment> matchedVals = assignments.subList(0, 2);
		Mockito.when(assignmentDao.getAssignmentsByCourse("Test Class 1")).thenReturn(matchedVals);
		List<Assignment> actual = as.getAssignments("Test Class 1");
	    Assert.assertArrayEquals("Didn't return expected assignments", assignments.subList(0, 2).toArray(), actual.toArray());
	}
	
	@Test
	public void shouldReturnAnEmptyListOfAssignments() {
	    // ask the service to find all assignments with an invalid corseId
	    // assert that an empty list was returned
		List<Assignment> emptyList = new ArrayList<>();
		Mockito.when(assignmentDao.getAssignmentsByCourse("invalid id")).thenReturn(emptyList);
		List<Assignment> actual = as.getAssignments("invalid id");
	    Assert.assertArrayEquals("Didn't return expected assignments", assignments.subList(0, 0).toArray(), actual.toArray());
	}
	
	@Test
	public void shouldSubmitAssignment() {
	    // ask the service to submit an assignment
	    // assert that the assignment was submitted
		Mockito.when(submitDao.addSubmission(Mockito.any(Submission.class))).thenReturn(true);
		boolean actual = as.submitAssignment(testSubmission0.getAssignmentId(), testSubmission0.getCourseId(),
												testSubmission0.getStudentId(), testSubmission0.getFile(),
												testSubmission0.getFileName());
	    Assert.assertTrue(actual);
	}
	
	@Test
	public void shouldReturnAListOfSubmissionsStudentAndCourse() {
	    // ask the service find all submissions with a matching courseId and studentId
	    // assert that correct submissions were returned
		List<Submission> matchedVals = submissions.subList(0, 2);
		Mockito.when(submitDao.getAllStudentSubmissions("asmith","Test Class 1")).thenReturn(matchedVals);
		List<Submission> actual = as.getSubmissions("asmith",  "Test Class 1");
	    Assert.assertArrayEquals("Didn't return expected submissions", submissions.subList(0, 2).toArray(), actual.toArray());
	}
	
	@Test
	public void shouldReturnAListOfSubmissionsAssignmentID() {
	    // ask the service find all submissions with a matching assignment ID
	    // assert that correct submissions were returned
		List<Submission> matchedVals = submissions.subList(1, 3);
		Mockito.when(submitDao.getAllSubmissions(2)).thenReturn(matchedVals);
		List<Submission> actual = as.getSubmissions(2);
	    Assert.assertArrayEquals("Didn't return expected submissions", submissions.subList(1, 3).toArray(), actual.toArray());
	}
	
	@Test
	public void shouldReturnGradePercentage() {
	    // ask the service find the grade pecentage for a submission
	    // assert that the correct value was returned
		double actual = as.calculateGrade(testAssignment0, testSubmission1);
	    Assert.assertEquals(89, actual, 0.0);
	}
	
	@Test
	public void shouldReturnInvalidGradePercentage() {
	    // ask the service find the grade pecentage for a ungraded submission
	    // assert that an invalid value was returned
		double actual = as.calculateGrade(testAssignment0, testSubmission0);
	    Assert.assertEquals(-1, actual, 0.0);
	}
}
