package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ticket.model.Post;
import ticket.utilities.ConnectionUtil;

public class PostDAOSQLImpl implements PostDAO {

	private ConnectionUtil connectionUtil;
	Connection connection = null;
	PreparedStatement statement = null;
	
	public PostDAOSQLImpl(ConnectionUtil connectionUtil) {
		if (connectionUtil != null) {
			this.connectionUtil = connectionUtil;
		}
	}
	
	@Override
	public List<Post> getPostsFromTicket(int ticket_id) {
		List<Post> list = new ArrayList<>();
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT * FROM public.posts WHERE ticket_id=? ORDER BY post_order ASC;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, ticket_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int ticketId = rs.getInt("ticket_id");
				String posterId = rs.getString("user_id");
				int post_order = rs.getInt("post_order");
				String body = rs.getString("body");
				String creationDate = rs.getString("creation_date");
				LocalDateTime creation_date = LocalDateTime.parse(creationDate, DateTimeFormatter.ISO_DATE_TIME);
				list.add(new Post(ticketId, posterId, post_order, body, creation_date));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return list;
	}

	@Override
	public boolean addPost(Post post) {
		boolean result = false;
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "INSERT INTO public.posts VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, post.getTicketId());
			statement.setString(2, post.getPosterId());
			String creationDate = post.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(3, creationDate);
			statement.setInt(4, post.getPostOrder());
			statement.setString(5, post.getBody());
			if (statement.executeUpdate() != 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	public int getNextPostNumber(int ticket_id) {
		int num = 0;
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT MAX(post_order) FROM public.posts WHERE ticket_id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, ticket_id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return ++num;
	}
	
	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
