package gradebook.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gradebook.models.Submission;
import gradebook.util.ConnectionProvider;

/**
 * SubmissionsSQLDAO: a class that allows CRUD operations on a assignment submission table for various SQL database types
 * 
 * class variables
 * private Connection connection: a connection to a SQL database
 * 
 * private PreparedStatement ps: a prepared SQL statement used to query a SQL database
 * 
 * methods
 * public List<Submission> getAllSubmissions(int assignment_id): this method accepts an integer assignment_id value and
 * returns a list of all submissions with that matching value. The list will be empty if no submissions have an assignment_id
 * with that value.
 * 
 * public List<Submission> getAllStudentSubmissions(String student_id, String course_id): this method accepts a String student_id
 * value and a String course_id value and returns a list of all submissions that match both values.
 * The list will be empty if no submissions match both values.
 * 
 * public Submission getSubmission(String student_id, String assignment_id): this method accepts a String student_id value and
 * a String assignment_id value and returns a single Submission object that matches both values. If no submission 
 * matches both values the object will be null.
 * 
 * public boolean addSubmission(Submission submission): this method accepts a Submission object and adds the object to the SQL
 * database. It returns true if the object was successfully added and false if it was not.
 * 
 * public boolean updateSubmission(Submission submission): this method accepts a Submission object and updates the values in the
 * SQL database according to the ID values found in the object. It returns true if the object was successfully updated and false
 * if it was not.
 * 
 * private void closeResources(void): this method closes the connection and prepared statement if they are not null
 * 
 * @author Joshua Brewer
 *
 */
public class SubmissionsSQLDAO implements SubmissionsDAO {
	
	private Connection connection;
	private PreparedStatement ps;
	private static SubmissionsSQLDAO instance;
	
	private SubmissionsSQLDAO() {}
	
	public static SubmissionsSQLDAO getInstance() {
		if (instance == null)
			instance = new SubmissionsSQLDAO();
		return instance;
	}

	@Override
	public List<Submission> getAllSubmissions(int assignment_id) {
		List<Submission> submissions = new ArrayList<>();
		
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "select * from gradebook.submissions where assignment_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setInt(1, assignment_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocalDateTime date_submitted = LocalDateTime.parse(rs.getString("date_submitted"), DateTimeFormatter.ISO_DATE_TIME);
				InputStream is = new ByteArrayInputStream(rs.getBytes("file"));
				submissions.add(new Submission(rs.getInt("assignment_id"), rs.getString("course_id"), rs.getString("student_id"),
										is, rs.getDouble("points"), rs.getString("reply"), date_submitted, rs.getString("file_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return submissions;
	}

	@Override
	public boolean containsSubmission(Submission submission) {
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "select * from gradebook.submissions where student_id=? and assignment_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setString(1, submission.getStudentId());
			ps.setInt(2, submission.getAssignmentId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		return false;
	}

	@Override
	public List<Submission> getAllStudentSubmissions(String student_id, String course_id) {
		List<Submission> submissions = new ArrayList<>();
		
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "select * from gradebook.submissions where student_id=? and course_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setString(1, student_id);
			ps.setString(2, course_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LocalDateTime date_submitted = LocalDateTime.parse(rs.getString("date_submitted"), DateTimeFormatter.ISO_DATE_TIME);
				InputStream is = new ByteArrayInputStream(rs.getBytes("file"));
				submissions.add(new Submission(rs.getInt("assignment_id"), rs.getString("course_id"), rs.getString("student_id"),
										is, rs.getDouble("points"), rs.getString("reply"), date_submitted, rs.getString("file_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return submissions;
	}

	@Override
	public Submission getSubmission(String student_id, int assignment_id) {
		Submission submission = null;
		
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "select * from gradebook.submissions where student_id=? and assignment_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setString(1, student_id);
			ps.setInt(2, assignment_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				LocalDateTime date_submitted = LocalDateTime.parse(rs.getString("date_submitted"), DateTimeFormatter.ISO_DATE_TIME);
				InputStream is = new ByteArrayInputStream(rs.getBytes("file"));
				submission = new Submission(rs.getInt("assignment_id"), rs.getString("course_id"), rs.getString("student_id"),
										is, rs.getDouble("points"), rs.getString("reply"), date_submitted, rs.getString("file_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return submission;
	}

	@Override
	public boolean addSubmission(Submission submission) {
		boolean addition_success = false;
		
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "insert into gradebook.submissions(assignment_id, course_id, student_id, file, date_submitted, points, reply, file_name) "
							+ "values(?,?,?,?,?,?,?,?)";
			ps = connection.prepareStatement(sql_query);
			ps.setInt(1, submission.getAssignmentId());
			ps.setString(2, submission.getCourseId());
			ps.setString(3, submission.getStudentId());
			ps.setBinaryStream(4, submission.getFile());
			LocalDateTime universal_time_zone = submission.getDateSubmitted().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
			String date = universal_time_zone.format(DateTimeFormatter.ISO_DATE_TIME);
			ps.setString(5, date);
			ps.setDouble(6, submission.getPoints());
			ps.setString(7, submission.getComments());	
			ps.setString(8, submission.getFileName());
			if(ps.executeUpdate() == 1) {
				addition_success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return addition_success;
	}

	@Override
	public boolean updateSubmission(Submission submission) {
		boolean update_success = false;
		
		try {
			connection = ConnectionProvider.getConnection();
			String sql_query = "update gradebook.submissions set file=?, points=?, reply=?, date_submitted=?, file_name=? "
							+ "where assignment_id=? and course_id=? and student_id=?";
			ps = connection.prepareStatement(sql_query);
			ps.setBinaryStream(1, submission.getFile());
			ps.setDouble(2, submission.getPoints());
			ps.setString(3, submission.getComments());
			LocalDateTime universal_time_zone = submission.getDateSubmitted().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
			String date = universal_time_zone.format(DateTimeFormatter.ISO_DATE_TIME);
			ps.setString(4, date);
			ps.setString(5, submission.getFileName());
			ps.setInt(6, submission.getAssignmentId());
			ps.setString(7, submission.getCourseId());
			ps.setString(8, submission.getStudentId());
			if(ps.executeUpdate() == 1) {
				update_success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResorces();
		}
		
		return update_success;
	}
	
	private void closeResorces() {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
