package gradebook.models;

import java.time.LocalDateTime;

public class Assignment {
	private int assignmentID;
	private String course_id;
	private String name;
	private String body;
	private int points;
	private LocalDateTime dueDate;
	
	public Assignment(int assignmentID, String course_id, String name, String body, int points, LocalDateTime dueDate) {
		this.assignmentID = assignmentID;
		this.course_id = course_id;
		this.name = name;
		this.body = body;
		this.points = points;
		this.dueDate = dueDate;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
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

	public int getPoints() {
		return points;
	}

	public void setPointValue(int points) {
		this.points = points;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}	
}
