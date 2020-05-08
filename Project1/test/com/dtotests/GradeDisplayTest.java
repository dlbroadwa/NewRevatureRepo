package com.dtotests;

import org.junit.Test;

import gradebook.dto.GradeDisplay;
import org.junit.Assert;

public class GradeDisplayTest {
	GradeDisplay testGradeDisplay = new GradeDisplay("Test Assignment 1", "20", "30", "05-01-2020", "05-08-2020", 1);
	
	@Test
	public void verifyAssignmentName(){
		//ask the gradeDisplay to retrieve an assignmentname
		//assert that the correct assignment name is returned
		String actual = testGradeDisplay.getAssignmentName();
		Assert.assertEquals("Did not return expected value", "Test Assignment 1", actual);
	}
	
	@Test
	public void verifyAssignmentNameChange(){
		//ask the gradeDisplay to change an assignmentname
		//assert that the assignmentName is changed
		testGradeDisplay.setAssignmentName("Changed Name");
		String actual = testGradeDisplay.getAssignmentName();
		Assert.assertEquals("Did not return expected value", "Changed Name", testGradeDisplay.getAssignmentName());
	}
	
	@Test
	public void verifyGradePoints(){
		//ask the gradeDisplay to retrieve gradePoints
		//assert that the correct value is returned
		String actual = testGradeDisplay.getGradePoints();
		Assert.assertEquals("Did not return expected value", "20", actual);
	}
	
	@Test
	public void verifyGradePointsChange(){
		//ask the gradeDisplay to change gradePoints
		//assert that the gradePoints value is changed
		testGradeDisplay.setAssignmentName("400");
		String actual = testGradeDisplay.getAssignmentName();
		Assert.assertEquals("Did not return expected value", "400", testGradeDisplay.getAssignmentName());
	}
}
