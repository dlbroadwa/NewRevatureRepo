package test.java.servicetests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.models.Student_User;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.ChangePasswordService;


public class ChangePasswordServiceTest {
	ChangePasswordService cps;
	User testTeacherUser0 = new Teacher_User("greenview34", "Penny", "Greenview", "greenview@school.edu", "vshoa!lA0");
	User testTeacherUser1 = new Teacher_User("bbryant00","Beth","Bryant","bbryant@uni.edu","ahdf2D!");
	User testStudentUser0 = new Student_User("xansam40","Xander","Samuson","samuson20@gmail.com","n&mC771");
	User testStudentUser1 = new Student_User("asmith","Alexandra","Smith","smithie@learn.edu","caok3n$8");
	
	@Mock
	TeacherSQLDAO teacherDao;
	
	@Mock
	StudentSQLDAO studentDao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	    cps = new ChangePasswordService(teacherDao,studentDao);
	}
	
	@Test
	public void shouldChangeATeacherUserPassword() {
	    // ask the service to change a teacher's password
	    // assert that the password has been changed
		Mockito.when(teacherDao.updateUser(testTeacherUser0)).thenReturn(true);
		boolean actual = cps.changePassword(testTeacherUser0, "ml4!mdA");
	    Assert.assertTrue("Value returned was false", actual);
	}
	
	@Test
	public void shouldChangeAStudentUserPassword() {
	    // ask the service to change a student's password
	    // assert that the password has been changed
		Mockito.when(studentDao.updateUser(testStudentUser0)).thenReturn(true);
		boolean actual = cps.changePassword(testStudentUser0, "ml4!mdA");
	    Assert.assertTrue("Value returned was false", actual);
	}
	
	@Test
	public void shouldFailToChangePasswordInvalidPassword() {
	    // ask the service to change a password with an invalid password
	    // assert that it returns false
		boolean actual = cps.changePassword(testStudentUser0, "Invalid Password");
	    Assert.assertFalse("Value returned was true", actual);
	}
	
	@Test
	public void shouldFailToChangePasswordNullPassword() {
	    // ask the service to change a password with an null password
	    // assert that it returns false
		boolean actual = cps.changePassword(testStudentUser0, null);
	    Assert.assertFalse("Value returned was true", actual);
	}
}
