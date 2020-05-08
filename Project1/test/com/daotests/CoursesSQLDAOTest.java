package com.daotests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gradebook.dao.CoursesSQLDAO;
import gradebook.models.Course;

public class CoursesSQLDAOTest {
	CoursesSQLDAO courseDao;
	Course testCourse = new Course("HISTORY 225", "Chinese Cultural Revolution", "dsterling");
	List<Course> courses = new ArrayList<>();
	
	@Before
	public void init() {
		courseDao = CoursesSQLDAO.getInstance();
		courses.add(testCourse);
	}
	
	@Test
	public void shouldReturnACourse() {
	    // ask the dao to find a course using a course id
	    // assert that the correct course was returned
		Course actual = courseDao.getCourseById("HISTORY 225");
	    Assert.assertEquals("Expected course not returned", testCourse.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnNull() {
	    // ask the dao to find a course using an invalid course id
	    // assert that null was returned
		Course actual = courseDao.getCourseById("Invalid Class");
	    Assert.assertNull("The value returned was not null", actual);
	}
	
	@Test
	public void shouldReturnACourseList() {
	    // ask the dao to find all courses with a matching teacher id
	    // assert that the correct courses were returned
		List<Course> actual = courseDao.getCoursesByTeacher("dsterling");
	    Assert.assertEquals("Expected course not returned", courses.toString(), actual.toString());
	}
	
	@Test
	public void shouldReturnEmptyList() {
	    // ask the dao to find all courses using an invalid teacher id
	    // assert that an empty list was returned
		List<Course> expected = new ArrayList<>();
		List<Course> actual = courseDao.getCoursesByTeacher("InvalidId");
	    Assert.assertArrayEquals("Empty list not returned", expected.toArray(), actual.toArray());
	}
}
