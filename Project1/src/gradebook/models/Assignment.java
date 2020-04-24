package gradebook.models;

import java.time.LocalDateTime;

public class Assignment {
	private int assignmentID;
	private String name;
	private String body;
	private double pointValue;
	private LocalDateTime dueDate;
	
	public Assignment(int assignmentID, String name, String body, double pointValue, LocalDateTime dueDate) {
		this.assignmentID = assignmentID;
		this.name = name;
		this.body = body;
		this.pointValue = pointValue;
		this.dueDate = dueDate;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getPointValue() {
		return pointValue;
	}

	public void setPointValue(double pointValue) {
		this.pointValue = pointValue;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}	
}
