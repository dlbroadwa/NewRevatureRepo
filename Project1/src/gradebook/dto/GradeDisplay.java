package gradebook.dto;

public class GradeDisplay {
	private String assignmentName;
	private String gradePoints;
	private String maxPoints;
	private String submitDate;
	private String dueDate;
	private int assignmentID;
	
	public GradeDisplay(String assignmentName, String gradePoints, String maxPoints, String submitDate, String dueDate, int assignmentID) {
		this.assignmentName = assignmentName;
		this.dueDate = dueDate;
		this.gradePoints = gradePoints;
		this.maxPoints = maxPoints;
		this.submitDate = submitDate;
		this.dueDate = dueDate;
		this.assignmentID = assignmentID;
	}

	@Override
	public String toString() {
		return "GradeDisplay [assignmentName=" + assignmentName + ", gradePoints=" + gradePoints + ", maxPoints="
				+ maxPoints + ", submitDate=" + submitDate + ", dueDate=" + dueDate + "]";
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getGradePoints() {
		return gradePoints;
	}

	public void setGradePoints(String gradePoints) {
		this.gradePoints = gradePoints;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getAssignmentID() {
		return assignmentID;
	}

	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}	
}
