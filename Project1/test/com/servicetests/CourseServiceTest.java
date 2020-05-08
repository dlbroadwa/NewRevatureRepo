package com.servicetests;

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

import gradebook.dao.CoursesSQLDAO;
import gradebook.dao.EnrollmentSQLDAO;
import gradebook.dao.StudentSQLDAO;
import gradebook.dao.TeacherSQLDAO;
import gradebook.models.Course;
import gradebook.models.Enrollment;
import gradebook.models.Student_User;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.CourseService;



public class CourseServiceTest {
	CourseService cs;
	Enrollment testEnrollment0 = new Enrollment("jnewt20","MATH101");
	Enrollment testEnrollment1 = new Enrollment("mike33","MATH101");
	Enrollment testEnrollment2 = new Enrollment("smiff03","MATH101");
	Enrollment testEnrollment3 = new Enrollment("jnewt20","MATH201");
	Enrollment testEnrollment4 = new Enrollment("stan489","MATH201");
	List<Enrollment> enrollment = new ArrayList<>();
	Student_User testStudentUser0 = new Student_User("mike33", "Micheal", "Newton", "mike.newt@uni.edu", "AnG&&3v");
	Student_User testStudentUser1 = new Student_User("smiff03", "Shelly", "Mifton", "shells22@gmail.com", "hA99m$$");
	Student_User testStudentUser2 = new Student_User("jnewt20", "James", "Newton", "james22@uni.edu", "nao8*MM");
	Student_User testStudentUser3 = new Student_User("stan489", "Stanley", "Hermson", "stan22@smart.edu", "nds&09S");
	List<Student_User> students = new ArrayList<>();
	Course testCourse0 = new Course("MATH101", "Simple Algebra", "bbryant00");
	Course testCourse1 = new Course("MATH201", "Calculus 1", "bbryant00");
	Course testCourse2 = new Course("ENGL101", "Grammar", "greenview34");
	List<Course> courses = new ArrayList<>();
	User testTeacherUser0 = new Teacher_User("greenview34", "Penny", "Greenview", "greenview@school.edu", "vshoa!lA0");
	User testTeacherUser1 = new Teacher_User("bbryant00","Beth","Bryant","bbryant@uni.edu","ahdf2D!");
	
	@Mock
	CoursesSQLDAO courseDao;
	
	@Mock
	EnrollmentSQLDAO enrollDao;
	
	@Mock
	StudentSQLDAO studentDao;
	
	@Mock
	TeacherSQLDAO teacherDao;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void init() {
	    cs = new CourseService(courseDao,enrollDao,studentDao,teacherDao);
	    enrollment.add(testEnrollment0);
	    enrollment.add(testEnrollment1);
	    enrollment.add(testEnrollment2);
	    enrollment.add(testEnrollment3);
	    enrollment.add(testEnrollment4);
	    students.add(testStudentUser0);
	    students.add(testStudentUser1);
	    students.add(testStudentUser2);
	    students.add(testStudentUser3);
	    courses.add(testCourse0);
	    courses.add(testCourse1);
	    courses.add(testCourse2);
	}
	
	@Test
	public void shouldGetAllStudentsInACourse() {
	    // ask the service to retrieve all students from a specific class
	    // assert that the correct students were retrieved
		List<Enrollment> tempEnroll = enrollment.subList(0, 3);
		List<User> tempStudents = new ArrayList<>();
		tempStudents.add(testStudentUser2);
		tempStudents.addAll(students.subList(0, 2));
		Mockito.when(enrollDao.getEnrollmentByCourseId("MATH101")).thenReturn(tempEnroll);
		Mockito.when(studentDao.getUser("mike33")).thenReturn(testStudentUser0);
		Mockito.when(studentDao.getUser("smiff03")).thenReturn(testStudentUser1);
		Mockito.when(studentDao.getUser("jnewt20")).thenReturn(testStudentUser2);
	    List<User> actual = cs.getStudentsInCourse(testEnrollment0.getCourse_id());
	    Assert.assertEquals("Did not return expected students", tempStudents.toString(), actual.toString());
	}
	
	@Test
	public void shouldGetEmptyList() {
	    // ask the service to get students from an invalid class
	    // assert that the an empty list was returned
		List<User> emptyList = new ArrayList<>();
		Mockito.when(enrollDao.getEnrollmentByCourseId("Invalid Class")).thenReturn(null);
	    List<User> actual = cs.getStudentsInCourse("Invalid Class");
	    Assert.assertArrayEquals("Did not return empty list", emptyList.toArray(), actual.toArray());
	}
	
	@Test
	public void shouldGetAllCoursesByStudent() {
	    // ask the service to retrieve all the courses in which a student is enrolled
	    // assert that the correct courses were retrieved
		List<Enrollment> tempEnroll = new ArrayList<>();
		tempEnroll.add(testEnrollment0);
		tempEnroll.add(testEnrollment3);
		Mockito.when(enrollDao.getEnrollmentByStudentId("jnewt20")).thenReturn(tempEnroll);
		Mockito.when(courseDao.getCourseById("MATH101")).thenReturn(testCourse0);
		Mockito.when(courseDao.getCourseById("MATH201")).thenReturn(testCourse1);
		Mockito.when(studentDao.getUser("jnewt20")).thenReturn(testStudentUser2);
	    List<Course> actual = cs.getCourses(testEnrollment0.getStudent_id());
	    Assert.assertEquals("Did not return expected students", courses.subList(0, 2).toString(), actual.toString());
	}
	
	@Test
	public void shouldGetAllCoursesByTeacher() {
	    // ask the service to retrieve all the courses in which a teacher teaches
	    // assert that the correct courses were retrieved
		List<Course> tempCourses = courses.subList(0, 2);
		Mockito.when(courseDao.getCoursesByTeacher("bbryant00")).thenReturn(tempCourses);
		Mockito.when(teacherDao.getUser("bbryant00")).thenReturn(testTeacherUser1);
		Mockito.when(studentDao.getUser("bbryant00")).thenReturn(null);
	    List<Course> actual = cs.getCourses(testCourse0.getTeacherId());
	    Assert.assertEquals("Did not return expected students", courses.subList(0, 2).toString(), actual.toString());
	}
	
	@Test
	public void shouldGetANullListInvalidUser() {
	    // ask the service to retrieve all the courses for an invalid user
	    // assert that null was returned
		Mockito.when(studentDao.getUser("InvalidStudent")).thenReturn(null);
		Mockito.when(teacherDao.getUser("InvalidStudent")).thenReturn(null);
	    List<Course> actual = cs.getCourses("InvalidStudent");
	    Assert.assertNull("Returned value was not null",actual);
	}
	
	@Test
	public void shouldGetANullListValidStudentInvalidEnrollment() {
	    // ask the service to retrieve all the courses for a valid student user and an invalid enrollment
	    // assert that null was returned
		Mockito.when(enrollDao.getEnrollmentByStudentId("mike33")).thenReturn(null);
		Mockito.when(studentDao.getUser("mike33")).thenReturn(testStudentUser0);
	    List<Course> actual = cs.getCourses("mike33");
	    Assert.assertNull("Returned value was not null",actual);
	}
	
	@Test
	public void shouldGetANullListValidTeacherInvalidEnrollment() {
	    // ask the service to retrieve all the courses for a valid teacher user and an invalid enrollment
	    // assert that null was returned
		Mockito.when(enrollDao.getEnrollmentByStudentId("bbryant00")).thenReturn(null);
		Mockito.when(studentDao.getUser("bbryant00")).thenReturn(testTeacherUser1);
	    List<Course> actual = cs.getCourses("bbryant00");
	    Assert.assertNull("Returned value was not null",actual);
	}

	@Test
	public void shouldGetACourse() {
	    // ask the service to retrieve a course with a course id
	    // assert that correct course was returned
		Mockito.when(courseDao.getCourseById("ENGL101")).thenReturn(testCourse2);
	    Course actual = cs.getCourse(testCourse2.getCourseId());
	    Assert.assertEquals("Did not return expected course", testCourse2.toString(), actual.toString());
	}

}
