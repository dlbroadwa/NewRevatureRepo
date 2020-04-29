package gradebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradebook.models.Course;
import gradebook.util.ConnectionProvider;

/**
 * CoursesSQLDAO --- Accesses data from the courses table in a Postgres database.
 * @author Austin
 */
public class CoursesSQLDAO implements CoursesDAO {

	private Connection conn;
	PreparedStatement statement;
	
	@Override
	public Course getCourseById(String course_id) {
		Course course = null;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM public.courses WHERE course_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, course_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String courseId = rs.getString("course_id");
				String name = rs.getString("name");
				String teacher_id = rs.getString("teacher_id");
				course = new Course(courseId, name, teacher_id);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return course;
	}

	@Override
	public List<Course> getCoursesByTeacher(String teacher_id) {
		List<Course> courses = new ArrayList<>();
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			String sql = "SELECT * FROM public.courses WHERE teacher_id=?;";
			statement = conn.prepareStatement(sql);
			statement.setString(1, teacher_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String courseId = rs.getString("course_id");
				String name = rs.getString("name");
				String teacherId = rs.getString("teacher_id");
				courses.add(new Course(courseId, name, teacherId));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return courses;
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
