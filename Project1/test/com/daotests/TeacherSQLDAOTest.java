package com.daotests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gradebook.dao.TeacherSQLDAO;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.util.Encryption;

public class TeacherSQLDAOTest {
	TeacherSQLDAO teacherDao;
	Teacher_User testTeacher = new Teacher_User("dsterling","Don","Sterling","don.sterling@school.edu",
			"3dc6da2c3780ec0af3712ae3cb330087c214c5ad511da078c57dfa97411bbd755750bf68dc3161ae466570808648d7cc8bd9ed6f83e6c501827a771b813b776a");
	Teacher_User updatedTeacher = new Teacher_User("dsterling","Don","Sterling","don.sterling01@school.edu",
			"3dc6da2c3780ec0af3712ae3cb330087c214c5ad511da078c57dfa97411bbd755750bf68dc3161ae466570808648d7cc8bd9ed6f83e6c501827a771b813b776a");
	Teacher_User invalidUpdate = new Teacher_User("invalid","Garbage","Value","Dont@care.edu",Encryption.encrypt("wrongne$$"));
	
	@Before
	public void init() {
	    teacherDao = TeacherSQLDAO.getInstance();
	}
	
	@Test
	public void shouldReturnATeacherUser() {
	    // ask the dao to find a teacher user from the SQL table based on the teacher id
	    // assert that the correct teacher was returned
		User actual = teacherDao.getUser("dsterling");
	    Assert.assertEquals("Didn't return expected user", testTeacher.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNullInvalidTeacherUser() {
	    // ask the dao to find a teacher user from the SQL table based on an invalid teacher id
	    // assert that null was returned
		User actual = teacherDao.getUser("InvalidId");
	    Assert.assertNull("Returned value not null", actual);
	}
	
	@Test
	public void shouldUpdateTeacherUser() {
	    // ask the dao to update a teacher user from the SQL table based on the teacher id
	    // assert that the teacher was updated and true was returned
		boolean actual = teacherDao.updateUser(updatedTeacher);
	    Assert.assertTrue("Returned value not true", actual);
	}
	
	@Test
	public void shouldNotUpdateTeacherUser() {
	    // ask the dao to update a teacher user from the SQL table based on an invalid teacher id
	    // assert that the teacher was not updated and false was returned
		boolean actual = teacherDao.updateUser(invalidUpdate);
	    Assert.assertFalse("Returned value not false", actual);
	}
	
	@After
	public void cleanup() {
		teacherDao.updateUser(testTeacher);
	}
}
