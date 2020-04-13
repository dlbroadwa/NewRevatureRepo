package ticket.model;

import java.time.LocalDateTime;

public class Ticket {
	
	private int ticket_id;
	private String user_id;
	private String title;
	private String status;
	private String priority;
	private LocalDateTime creation_date;
	private String body;
	
	public static final String STATUS_OPEN = "Open";
	public static final String STATUS_HOLD = "Hold";
	public static final String STATUS_CLOSED = "Closed";
	public static final String PRIORITY_HIGH = "High";
	public static final String PRIORITY_MEDIUM = "Medium";
	public static final String PRIORITY_LOW = "Low";
	
	public Ticket(int ticket_id, String user_id, String title, String body) {
		this.ticket_id = ticket_id;
		this.user_id = user_id;
		this.title = title;
		this.status = "Open";
		this.priority = "Low";
		this.creation_date = LocalDateTime.now();
		this.body = body;
	}
	
	public Ticket(int ticket_id, String user_id, String title, String body, String status, String priority, LocalDateTime creation_date) {
		this(ticket_id, user_id, title, body);
		this.status = status;
		this.priority = priority;
		this.creation_date = creation_date;
	}

	public int getTicketId() {
		return ticket_id;
	}

	public String getUserId() {
		return user_id;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public LocalDateTime getCreationDate() {
		return creation_date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
