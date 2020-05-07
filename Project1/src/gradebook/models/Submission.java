package gradebook.models;

import java.io.InputStream;
import java.time.LocalDateTime;

public class Submission {
	private int assignment_id;
	private String course_id;
	private String student_id;
	private InputStream file;
	private LocalDateTime date_submitted;
	private int points;
	private String comments;
	private String fileName;
	
	public Submission (int assignment_id, String course_id, String student_id, InputStream file, String fileName) {
		this.assignment_id = assignment_id;
		this.course_id = course_id;
		this.student_id = student_id;
		this.file = file;
		this.fileName = fileName;
		this.points = -1;
		this.comments = null;
		this.date_submitted = LocalDateTime.now();
	}
	
	public Submission (int assignment_id, String course_id, String student_id, InputStream file, int points, String comments, LocalDateTime date_submitted, String fileName) {
		this(assignment_id, course_id, student_id, file, fileName);
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

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

	public LocalDateTime getDateSubmitted() {
		return date_submitted;
	}

	public void setDateSubmitted(LocalDateTime date_submitted) {
		this.date_submitted = date_submitted;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
