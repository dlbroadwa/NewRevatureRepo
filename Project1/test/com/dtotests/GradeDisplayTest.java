package com.dtotests;

import org.junit.Test;

import gradebook.dto.GradeDisplay;
import org.junit.Assert;

public class GradeDisplayTest {
	GradeDisplay testGradeDisplay = new GradeDisplay("Test Assignment 1", "20", "30", "05-01-2020", "05-08-2020", 1);
	
	@Test
	public void verifyObjectCreationAndToString(){
		//ask the gradeDisplay to retrieve dueDate value
		//assert that the correct value is returned
		GradeDisplay newGrade = new GradeDisplay("Test Assignment 1", "20", "30", "05-01-2020", "05-08-2020", 1);
		Assert.assertEquals("Did not return expected value", testGradeDisplay.toString(), newGrade.toString());
	}

	@Test
	public void verifyAssignmentName(){
		//ask the gradeDisplay to retrieve an assignmentName
		//assert that the correct assignment name is returned
		String actual = testGradeDisplay.getAssignmentName();
		Assert.assertEquals("Did not return expected value", "Test Assignment 1", actual);
	}
	
	@Test
	public void verifyAssignmentNameChange(){
		//ask the gradeDisplay to change an assignmentName
		//assert that the assignmentName is changed
		testGradeDisplay.setAssignmentName("Changed Name");
		String actual = testGradeDisplay.getAssignmentName();
		Assert.assertEquals("Did not return expected value", "Changed Name", actual);
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
		testGradeDisplay.setGradePoints("400");
		String actual = testGradeDisplay.getGradePoints();
		Assert.assertEquals("Did not return expected value", "400", actual);
	}
	
	@Test
	public void verifyMaxPoints(){
		//ask the gradeDisplay to retrieve a maxPoints value
		//assert that the correct value is returned
		String actual = testGradeDisplay.getMaxPoints();
		Assert.assertEquals("Did not return expected value", "30", actual);
	}
	
	@Test
	public void verifyMaxPointsChange(){
		//ask the gradeDisplay to change a maxPoints value
		//assert that the value is changed
		testGradeDisplay.setMaxPoints("1000");
		String actual = testGradeDisplay.getMaxPoints();
		Assert.assertEquals("Did not return expected value", "1000", actual);
	}
	
	@Test
	public void verifySubmitDate(){
		//ask the gradeDisplay to retrieve submitDate value
		//assert that the correct value is returned
		String actual = testGradeDisplay.getSubmitDate();
		Assert.assertEquals("Did not return expected value", "05-01-2020", actual);
	}
	
	@Test
	public void verifySubmitDateChange(){
		//ask the gradeDisplay to change submitDate value
		//assert that the value is changed
		testGradeDisplay.setSubmitDate("05-02-2020");
		String actual = testGradeDisplay.getSubmitDate();
		Assert.assertEquals("Did not return expected value", "05-02-2020", actual);
	}
	
	@Test
	public void verifyDueDate(){
		//ask the gradeDisplay to retrieve dueDate value
		//assert that the correct value is returned
		String actual = testGradeDisplay.getDueDate();
		Assert.assertEquals("Did not return expected value", "05-08-2020", actual);
	}
	
	@Test
	public void verifyDueDateChange(){
		//ask the gradeDisplay to change dueDate value
		//assert that the correct value is changed
		testGradeDisplay.setDueDate("05-09-2020");
		String actual = testGradeDisplay.getDueDate();
		Assert.assertEquals("Did not return expected value", "05-09-2020", actual);
	}
	
	@Test
	public void verifyAssignmentID(){
		//ask the gradeDisplay to retrieve assignmentID value
		//assert that the correct value is returned
		int actual = testGradeDisplay.getAssignmentID();
		Assert.assertEquals("Did not return expected value", 1, actual);
	}
	
	@Test
	public void verifyAssignmentIDChange(){
		//ask the gradeDisplay to change assignmentID value
		//assert that the correct value is changed
		testGradeDisplay.setAssignmentID(2);
		int actual = testGradeDisplay.getAssignmentID();
		Assert.assertEquals("Did not return expected value", 2, actual);
	}
}
