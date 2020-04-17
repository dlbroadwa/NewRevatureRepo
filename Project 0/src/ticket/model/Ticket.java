package ticket.model;

import java.time.ZonedDateTime;

public class Ticket {
	
	private int ticket_id;
	private String user_id;
	private String title;
	private String status;
	private String priority;
	private ZonedDateTime creation_date;
	private ZonedDateTime last_updated;
	
	public static final String STATUS_OPEN = "OPEN";
	public static final String STATUS_HOLD = "HOLD";
	public static final String STATUS_CLOSED = "CLOSED";
	public static final String PRIORITY_HIGH = "HIGH";
	public static final String PRIORITY_MEDIUM = "MEDIUM";
	public static final String PRIORITY_LOW = "LOW";
	
	public Ticket(int ticket_id, String user_id, String title) {
		this.ticket_id = ticket_id;
		this.user_id = user_id;
		this.title = title;
		this.status = STATUS_OPEN;
		this.priority = PRIORITY_LOW;
		this.creation_date = ZonedDateTime.now();
		this.last_updated = creation_date;
	}
	
	public Ticket(int ticket_id, String user_id, String title, String status, String priority, ZonedDateTime creation_date, ZonedDateTime last_updated) {
		this.ticket_id = ticket_id;
		this.user_id = user_id;
		this.title = title;
		this.status = status;
		this.priority = priority;
		this.creation_date = creation_date;
		this.last_updated = last_updated;
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

	public ZonedDateTime getCreationDate() {
		return creation_date;
	}
	
	public ZonedDateTime getLastUpdated() {
		return last_updated;
	}
	
	public void setLastUpdated(ZonedDateTime last_updated) {
		this.last_updated = last_updated;
	}
}
