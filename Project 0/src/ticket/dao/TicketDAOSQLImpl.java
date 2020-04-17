package ticket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ticket.model.Ticket;
import ticket.utilities.ConnectionUtil;

public class TicketDAOSQLImpl implements TicketDAO {
	
	private ConnectionUtil connectionUtil;
	Connection connection = null;
	PreparedStatement statement = null;

	public TicketDAOSQLImpl (ConnectionUtil connectionUtil) {
		if (connectionUtil != null) {
			this.connectionUtil = connectionUtil;
		}
	}
	
	@Override
	public List<Ticket> getAllTickets() {
		
		List<Ticket> list = new ArrayList<>();
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT * FROM public.tickets ORDER BY status DESC, priority ASC, creation_date DESC;";
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int ticketId = rs.getInt("ticket_id");
				String user_id = rs.getString("user_id");
				String title = rs.getString("title");
				String status = rs.getString("status");
				String priority = rs.getString("priority");
				String creationDate = rs.getString("creation_date");
				String lastUpdated = rs.getString("last_updated");
				ZonedDateTime creation_date = ZonedDateTime.parse(creationDate, DateTimeFormatter.ISO_DATE_TIME);
				ZonedDateTime last_updated = ZonedDateTime.parse(lastUpdated, DateTimeFormatter.ISO_DATE_TIME);
				list.add(new Ticket(ticketId, user_id, title, status, priority, creation_date, last_updated));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return list;
	}

	@Override
	public List<Ticket> getTicketsByUser(String user_id) {
		
		List<Ticket> list = new ArrayList<>();
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT * FROM public.tickets WHERE user_id=? ORDER BY status DESC, priority ASC, creation_date DESC;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, user_id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int ticketId = rs.getInt("ticket_id");
				String userId = rs.getString("user_id");
				String title = rs.getString("title");
				String status = rs.getString("status");
				String priority = rs.getString("priority");
				String creationDate = rs.getString("creation_date");
				String lastUpdated = rs.getString("last_updated");
				ZonedDateTime creation_date = ZonedDateTime.parse(creationDate, DateTimeFormatter.ISO_DATE_TIME);
				ZonedDateTime last_updated = ZonedDateTime.parse(lastUpdated, DateTimeFormatter.ISO_DATE_TIME);
				list.add(new Ticket(ticketId, userId, title, status, priority, creation_date, last_updated));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return list;
	}

	@Override
	public int getNextTicketId() {
		int ticketId = 0;
		try {
			connection = connectionUtil.getConnection();
			String sql = "SELECT MAX(ticket_id) FROM public.tickets;";
			statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				ticketId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return ++ticketId;
	}
	
	@Override
	public boolean addTicket(Ticket ticket) {
		boolean result = false;
		
		try {
			connection = connectionUtil.getConnection();
			String sql = "INSERT INTO public.tickets VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ticket.getTicketId());
			statement.setString(2, ticket.getUserId());
			statement.setString(3, ticket.getTitle());
			statement.setString(4, ticket.getStatus());
			statement.setString(5, ticket.getPriority());
			String creationDate = ticket.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(6, creationDate);
			String lastUpdated = ticket.getLastUpdated().format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(7, lastUpdated);
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
	public boolean updateTicket(Ticket ticket) {
		boolean result = false;
		try {
			connection = connectionUtil.getConnection();
			String sql = "UPDATE public.tickets SET status=?, priority=?, creation_date=?, last_updated=? WHERE ticket_id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, ticket.getStatus());
			statement.setString(2, ticket.getPriority());
			String creationDate = ticket.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(3, creationDate);
			String lastUpdated = ticket.getLastUpdated().format(DateTimeFormatter.ISO_DATE_TIME);
			statement.setString(4, lastUpdated);
			statement.setInt(5, ticket.getTicketId());
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
	public boolean deleteTicket(Ticket ticket) {
		boolean result = false;
		try {
			connection = connectionUtil.getConnection();
			String sql = "DELETE FROM public.tickets WHERE ticket_id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, ticket.getTicketId());
			if (statement.executeUpdate() != 0) {
				result = true;
			}
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
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
