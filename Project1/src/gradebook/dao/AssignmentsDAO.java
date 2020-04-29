package gradebook.dao;

import java.util.List;

import gradebook.models.Assignment;

/**
 * AssignmentstDAO --- Accesses data from an assignments table.
 * @author Austin Kind
 */
public interface AssignmentsDAO {
	
	/**
	 * Retrieves all the assignments from a course by the course's ID.
	 * @param course_id		The course ID.
	 * @return				A list of Assignments from the given course.
	 */
	List<Assignment> getAssignmentsByCourse(String course_id);
	
	/**
	 * Retrieves an assignment given the assignment ID.
	 * @param assignment_id		The ID of the desired assignment.
	 * @return					The assignment requested.
	 */
	Assignment getAssignmentById(int assignment_id);
	
	/**
	 * Adds the given assignment to the table.
	 * @param assignment	The assignment to be added.
	 * @return				True if operation was successful.
	 */
	boolean addAssignment(Assignment assignment);
	
	/**
	 * Updates the given assignment to the table.
	 * @param assignment	The assignment to be updated.
	 * @return				True if operation was successful.
	 */
	boolean updateAssignment(Assignment assignment);
	
	/**
	 * Deletes the given assignment from the table.
	 * @param assignment	The assignment to be deleted.
	 * @return				True if operation was successful.
	 */
	boolean deleteAssignment(Assignment assignment);
}
