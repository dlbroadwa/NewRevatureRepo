package test.java;

import java.time.LocalDateTime;
import java.time.Month;

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
import gradebook.services.AssignmentService;

public class AssignmentServiceTest {
	AssignmentService as;
	LocalDateTime dueDate0 = LocalDateTime.of(2020, Month.MAY, 8, 23, 59);
	Assignment testAssignment0 = new Assignment(1, "Test Class", "test assignment", "write how this test works", 100, dueDate0);
	
	@Mock
	AssignmentsSQLDAO assignmentDao;
	
	@Mock
	SubmissionsSQLDAO submitDao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	    as = new AssignmentService(submitDao, assignmentDao);
	}
	
	@Test
	public void shouldCreateNewAssignment() {
	    // ask the service to validate the username and password of a teacher
	    // assert that the correct user was returned
		Mockito.when(assignmentDao.getNextId()).thenReturn(1);
		Mockito.when(assignmentDao.addAssignment(Mockito.any(Assignment.class))).thenReturn(true);
	    boolean actual = as.createAssignment(testAssignment0.getCourse_id(), testAssignment0.getName(), testAssignment0.getBody(),
	    										testAssignment0.getPoints(), testAssignment0.getDueDate());
	    Assert.assertTrue(actual);
	}
	
	/*@Test
	public void shouldReturnValidStudent() {
	    // ask the service to validate the username and password of a teacher
	    // assert that the correct user was returned
		Mockito.when(teacherDao.getUser("xansam40")).thenReturn(null);
		Mockito.when(studentDao.getUser("xansam40")).thenReturn(testStudentUser0);
		User actual = ls.validate(((Student_User)testStudentUser0).getStudent_id(), testStudentUser0.getPassword());
	    Assert.assertEquals("Didn't return expected user", testStudentUser0.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNull() {
	    // ask the service to validate an invalid username and password
	    // assert that a null value was returned
		Mockito.when(teacherDao.getUser("garbage")).thenReturn(null);
		Mockito.when(studentDao.getUser("garbage")).thenReturn(null);
		User actual = ls.validate("garbage", "value");
	    Assert.assertNull(actual);
	}*/
}
