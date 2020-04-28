package gradebook.dao;

import gradebook.models.Assignment;

public interface AssignmentsDAO {
	Assignment getAssignmentsByCourse(String course_id);
	void addAssignment(Assignment assignment);
	void updateAssignment(Assignment assignment);
	void deleteAssignment(Assignment assignment);
}
