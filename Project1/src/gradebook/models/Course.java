package gradebook.models;

public class Course {
	
	private String course_id;
	private String name;
	private String teacher_id;
	
	public Course(String course_id, String name, String teacher_id) {
		this.course_id = course_id;
		this.name = name;
		this.teacher_id = teacher_id;
	}

	public String getCourseId() {
		return course_id;
	}

	public void setCourseId(String course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeacherId() {
		return teacher_id;
	}

	public void setTeacherId(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + "]";
	}
	
	
}
