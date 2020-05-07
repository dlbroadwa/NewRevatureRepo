package gradebook.models;

public class Teacher_User extends User{
	
	private String teacher_id;
	
	public Teacher_User(String teacher_id, String first_name, String last_name, String email, String password) {
		this.teacher_id = teacher_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	@Override
	public String toString() {
		return "Teacher_User [teacher_id=" + teacher_id + "]";
	}
	
	
	

}
