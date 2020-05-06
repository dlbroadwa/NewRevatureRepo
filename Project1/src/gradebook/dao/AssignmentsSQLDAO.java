package gradebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gradebook.models.Assignment;
import gradebook.util.ConnectionProvider;


/**
 * AssignmentsSQLDAO --- Accesses data from the assignments table in a Postgres database.
 * @author Austin Kind
 */
public class AssignmentsSQLDAO implements AssignmentsDAO {

	private Connection conn;
	private PreparedStatement statement;
	private static AssignmentsSQLDAO instance;
	
	private AssignmentsSQLDAO() {}
	
	public static AssignmentsSQLDAO getInstance() {
		if (instance == null)
			instance = new AssignmentsSQLDAO();
		return instance;
	}
	
	@Override
	public List<Assignment> getAssignmentsByCourse(String course_id) {
		List<Assignment> assignments = new ArrayList<>();
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM gradebook.assignments WHERE course_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, course_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int assignmentId = rs.getInt("assignment_id");
				String courseId = rs.getString("course_id");
				String name = rs.getString("title");
				String body = rs.getString("body");
				int points = rs.getInt("max_points");
				String due_date = rs.getString("due_date");
				LocalDateTime dueDate = LocalDateTime.parse(due_date, DateTimeFormatter.ISO_DATE_TIME);
				dueDate = dueDate.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
				assignments.add(new Assignment(assignmentId, courseId, name, body, points, dueDate));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return assignments;
	}
	
	@Override
	public Assignment getAssignmentById(int assignment_id) {
		Assignment assignment = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM gradebook.assignments WHERE assignment_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, assignment_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int assignmentId = rs.getInt("assignment_id");
				String courseId = rs.getString("course_id");
				String name = rs.getString("title");
				String body = rs.getString("body");
				int points = rs.getInt("max_points");
				String due_date = rs.getString("due_date");
				LocalDateTime dueDate = LocalDateTime.parse(due_date, DateTimeFormatter.ISO_DATE_TIME);
				dueDate = dueDate.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
				assignment = new Assignment(assignmentId, courseId, name, body, points, dueDate);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return assignment;
	}
	
	@Override
	public int getNextId() {
		int id = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT MAX(assignment_id) FROM gradebook.assignments;";
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return ++id;
	}

	@Override
	public boolean addAssignment(Assignment assignment) {
		boolean result = false;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "INSERT INTO gradebook.assignments VALUES (?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, assignment.getAssignmentID());
			statement.setString(2, assignment.getCourse_id());
			statement.setString(3, assignment.getName());
			statement.setInt(4, assignment.getPoints());
			statement.setString(5, assignment.getBody());
			LocalDateTime due_date = assignment.getDueDate().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
			String dueDate = due_date.format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(6, dueDate);
			if (statement.executeUpdate() != 0)
				result = true;	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	@Override
	public boolean updateAssignment(Assignment assignment) {
		boolean result = false;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "UPDATE gradebook.assignments SET title=?, body=?, max_points=?, due_date=? WHERE assignment_id=? and course_id=?";
			statement = conn.prepareStatement(sql);
			statement.setString(1, assignment.getName());
			statement.setString(2, assignment.getBody());
			statement.setInt(3, assignment.getPoints());
			LocalDateTime due_date = assignment.getDueDate().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
			String dueDate = due_date.format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(4, dueDate);
			statement.setInt(5, assignment.getAssignmentID());
			statement.setString(6, assignment.getCourse_id());
			if (statement.executeUpdate() != 0)
				result = true;	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	@Override
	public boolean deleteAssignment(Assignment assignment) {
		boolean result = false;
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "DELETE FROM gradebook.assignments WHERE assignment_id=? and course_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setInt(1, assignment.getAssignmentID());
			statement.setString(2, assignment.getCourse_id());
			if (statement.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;

	}

	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
