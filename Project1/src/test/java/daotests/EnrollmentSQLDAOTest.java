package test.java.daotests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gradebook.dao.EnrollmentSQLDAO;
import gradebook.models.Enrollment;

public class EnrollmentSQLDAOTest {
	EnrollmentSQLDAO enrollDao;
	Enrollment testEnroll0 = new Enrollment("jsmith","COMP SCI 520");
	Enrollment testEnroll1 = new Enrollment("jsmith", "HISTORY 225");
	Enrollment testEnroll2 = new Enrollment("ejohnson", "COMP SCI 520");
	List<Enrollment> studentEnrollment = new ArrayList<>();
	List<Enrollment> courseEnrollment = new ArrayList<>();
	
	@Before
	public void init() {
		enrollDao = EnrollmentSQLDAO.getInstance();
		studentEnrollment = Arrays.asList(testEnroll0,testEnroll1);
		courseEnrollment = Arrays.asList(testEnroll0,testEnroll2);
	}
	
	@Test
	public void shouldReturnStudentEnrollment() {
	    // ask the dao to find all enrollments with a matching student id
	    // assert that the correct enrollments were returned
		List<Enrollment> actual = enrollDao.getEnrollmentByStudentId("jsmith");
	    Assert.assertEquals("Expected enrollments not returned", studentEnrollment.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnEmptyListInvalidStudentId() {
	    // ask the dao to find all enrollments for an invalid student id
	    // assert that an empty list was returned
		List<Enrollment> expected = new ArrayList<>();
		List<Enrollment> actual = enrollDao.getEnrollmentByStudentId("InvalidID");
	    Assert.assertArrayEquals("Empty array not returned", expected.toArray(), actual.toArray());
	}
	
	@Test
	public void shouldReturnCourseEnrollment() {
	    // ask the dao to find all enrollments with a matching course id
	    // assert that the correct enrollments were returned
		List<Enrollment> actual = enrollDao.getEnrollmentByCourseId("COMP SCI 520");
	    Assert.assertEquals("Expected enrollments not returned", courseEnrollment.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnEmptyListInvalidCourseId() {
	    // ask the dao to find all enrollments for an invalid course id
	    // assert that an empty list was returned
		List<Enrollment> expected = new ArrayList<>();
		List<Enrollment> actual = enrollDao.getEnrollmentByCourseId("InvalidID");
	    Assert.assertArrayEquals("Empty array not returned", expected.toArray(), actual.toArray());
	}
}
