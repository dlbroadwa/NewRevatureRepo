package gradebook.dto;

public class UpdateGradeRequest {
	private int grade;
	private String comments;
	
	public UpdateGradeRequest() {}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
