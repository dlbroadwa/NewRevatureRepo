package test.java.daotests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gradebook.dao.StudentSQLDAO;
import gradebook.models.Student_User;
import gradebook.models.User;
import gradebook.util.Encryption;

public class StudentDQLDAOTest {
	StudentSQLDAO studentDao;
	Student_User testStudent = new Student_User("jsmith","John","Smith","john.smith@school.edu",
			"b9e2970e4f635294550197ab8d17dac2015f07847c6e6594d67039745f33b55d0f40f2c5f7cea5fac4651ade0b14ee13fdf16b4d0c8a5c6124ed3bd42f15a348");
	Student_User updatedStudent = new Student_User("jsmith","John","Smith","john.smith1@school.edu",
			"b9e2970e4f635294550197ab8d17dac2015f07847c6e6594d67039745f33b55d0f40f2c5f7cea5fac4651ade0b14ee13fdf16b4d0c8a5c6124ed3bd42f15a348");
	Student_User invalidUpdate = new Student_User("invalid","Garbage","Value","Dont@care.edu",Encryption.encrypt("wrongne$$"));
	
	@Before
	public void init() {
	    studentDao = StudentSQLDAO.getInstance();
	}
	
	@Test
	public void shouldReturnAStudentUser() {
	    // ask the dao to find a student user from the SQL table based on the student id
	    // assert that the correct student was returned
		User actual = studentDao.getUser("jsmith");
	    Assert.assertEquals("Didn't return expected user", testStudent.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNullInvalidStudentUser() {
	    // ask the dao to find a student user from the SQL table based on an invalid student id
	    // assert that null was returned
		User actual = studentDao.getUser("InvalidId");
	    Assert.assertNull("Returned value not null", actual);
	}
	
	@Test
	public void shouldUpdateStudentUser() {
	    // ask the dao to update a student user from the SQL table based on the student id
	    // assert that the student was updated and true was returned
		boolean actual = studentDao.updateUser(updatedStudent);
	    Assert.assertTrue("Returned value not true", actual);
	}
	
	@Test
	public void shouldNotUpdateStudentUser() {
	    // ask the dao to update a student user from the SQL table based on an invalid student id
	    // assert that the student was not updated and false was returned
		boolean actual = studentDao.updateUser(invalidUpdate);
	    Assert.assertFalse("Returned value not false", actual);
	}
	
	@After
	public void cleanup() {
		studentDao.updateUser(testStudent);
	}
}
