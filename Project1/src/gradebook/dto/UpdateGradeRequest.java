package gradebook.dto;

public class UpdateGradeRequest {
	private double grade;
	private String comments;
	
	public UpdateGradeRequest() {}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
