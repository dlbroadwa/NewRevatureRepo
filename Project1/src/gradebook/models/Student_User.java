package gradebook.models;

public class Student_User extends User {
	
	private String student_id;
	
	public Student_User(String student_id, String first_name, String last_name, String email, String password) {
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "Student_User [student_id=" + student_id + "]";
	}
	
}
