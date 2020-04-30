package gradebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradebook.models.Enrollment;
import gradebook.util.ConnectionProvider;

/**
 * EnrollmentSQLDAO --- Accesses data from the enrollment table in a Postgres database.
 * @author Austin
 */
public class EnrollmentSQLDAO implements EnrollmentDAO {

	private Connection conn;
	private PreparedStatement statement;
	private static EnrollmentSQLDAO instance;
	
	private EnrollmentSQLDAO() {}
	
	public static EnrollmentSQLDAO getInstance() {
		if (instance == null)
			instance = new EnrollmentSQLDAO();
		return instance;
	}
	
	@Override
	public List<Enrollment> getEnrollmentByCourseId(String course_id) {
		List<Enrollment> enrollment = new ArrayList<>();
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM public.enrollment WHERE course_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, course_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String studentId = rs.getString("student_id");
				String courseId = rs.getString("course_id");
				enrollment.add(new Enrollment(studentId, courseId));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return enrollment;
	}

	@Override
	public List<Enrollment> getEnrollmentByStudentId(String student_id) {
		List<Enrollment> enrollment = new ArrayList<>();
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM public.enrollment WHERE student_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, student_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String studentId = rs.getString("student_id");
				String courseId = rs.getString("course_id");
				enrollment.add(new Enrollment(studentId, courseId));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return enrollment;
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
