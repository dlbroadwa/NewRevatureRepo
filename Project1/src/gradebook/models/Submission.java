package gradebook.models;

import java.time.LocalDateTime;

public class Submission {
	private int assignment_id;
	private String course_id;
	private String student_id;
	private String file;
	private LocalDateTime date_submitted;
	private double points;
	private String comments;
	
	public Submission (int assignment_id, String course_id, String student_id, String file) {
		this.assignment_id = assignment_id;
		this.course_id = course_id;
		this.student_id = student_id;
		this.file = file;
		this.points = -1;
		this.comments = null;
		this.date_submitted = LocalDateTime.now();
	}
	
	public Submission (int assignment_id, String course_id, String student_id, String file, double points, String comments, LocalDateTime date_submitted) {
		this(assignment_id, course_id, student_id, file);
		this.points = points;
		this.comments = comments;
		this.date_submitted = date_submitted;
	}

	public int getAssignmentId() {
		return assignment_id;
	}

	public void setAssignmentId(int assignment_id) {
		this.assignment_id = assignment_id;
	}

	public String getCourseId() {
		return course_id;
	}

	public void setCourseId(String course_id) {
		this.course_id = course_id;
	}

	public String getStudentId() {
		return student_id;
	}

	public void setStudentId(String student_id) {
		this.student_id = student_id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public LocalDateTime getDateSubmitted() {
		return date_submitted;
	}

	public void setDateSubmitted(LocalDateTime date_submitted) {
		this.date_submitted = date_submitted;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
