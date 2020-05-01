package test.java;

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

import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.models.Student_User;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.LoginService;

public class LoginServiceTest {
	LoginService ls;
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
	    ls = new LoginService(teacherDao, studentDao);
	}
	
	@Test
	public void shouldReturnValidTeacher() {
	    // ask the service to validate the username and password of a teacher
	    // assert that the correct user was returned
		Mockito.when(teacherDao.getUser("greenview34")).thenReturn(testTeacherUser0);
	    User actual = ls.validate(((Teacher_User)testTeacherUser0).getTeacher_id(), testTeacherUser0.getPassword());
	    Assert.assertEquals("Didn't return expected user", testTeacherUser0.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnValidStudent() {
	    // ask the service to validate the username and password of a teacher
	    // assert that the correct user was returned
		Mockito.when(teacherDao.getUser("xansam40")).thenReturn(null);
		Mockito.when(studentDao.getUser("xansam40")).thenReturn(testStudentUser0);
		User actual = ls.validate(((Student_User)testStudentUser0).getStudent_id(), testStudentUser0.getPassword());
	    Assert.assertEquals("Didn't return expected user", testStudentUser0.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNullInvalidUsername() {
	    // ask the service to validate an invalid username and password
	    // assert that a null value was returned
		Mockito.when(teacherDao.getUser("garbage")).thenReturn(null);
		Mockito.when(studentDao.getUser("garbage")).thenReturn(null);
		User actual = ls.validate("garbage", "value");
	    Assert.assertNull(actual);
	}
	
	@Test
	public void shouldReturnNullValidTeacherInvalidPassword() {
	    // ask the service to validate a valid teacher username and a invalid password
	    // assert that a null value was returned
		Mockito.when(teacherDao.getUser("bbryant00")).thenReturn(testTeacherUser1);
		User actual = ls.validate("bbryant00", "garbage_value00");
	    Assert.assertNull(actual);
	}
	
	@Test
	public void shouldReturnNullValidStudetnInvalidPassword() {
	    // ask the service to validate an invalid username and password
	    // assert that a null value was returned
		Mockito.when(teacherDao.getUser("asmith")).thenReturn(null);
		Mockito.when(studentDao.getUser("asmith")).thenReturn(testStudentUser1);
		User actual = ls.validate("asmith", "invalid_value11");
	    Assert.assertNull(actual);
	}
}
