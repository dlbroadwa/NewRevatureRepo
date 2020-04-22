package ticket.model;

import java.time.LocalDateTime;

/**
 * Ticket --- Represents a helpdesk ticket.
 * @author Austin Kind
 */
public class Ticket {
	
	private int ticket_id;
	private String user_id;
	private String title;
	private String status;
	private String priority;
	private LocalDateTime creation_date;
	private LocalDateTime last_updated;
	
	public static final String STATUS_OPEN = "OPEN";
	public static final String STATUS_HOLD = "HOLD";
	public static final String STATUS_CLOSED = "CLOSED";
	public static final String PRIORITY_HIGH = "HIGH";
	public static final String PRIORITY_MEDIUM = "MEDIUM";
	public static final String PRIORITY_LOW = "LOW";
	
	/**
	 * Constructs the object.
	 * @param ticket_id		The ID of the ticket.
	 * @param user_id		The user ID of the user who created the ticket.
	 * @param title			The title of the ticket.
	 */
	public Ticket(int ticket_id, String user_id, String title) {
		this.ticket_id = ticket_id;
		this.user_id = user_id;
		this.title = title;
		this.status = STATUS_OPEN;
		this.priority = PRIORITY_LOW;
		this.creation_date = LocalDateTime.now();
		this.last_updated = creation_date;
	}
	
	/**
	 * Constructs the object.
	 * @param ticket_id		The ID of the ticket.
	 * @param user_id		The user ID of the user who created the ticket.
	 * @param title			The title of the ticket.
	 * @param status		The current status of the ticket.
	 * @param priority		The current priority of the ticket.
	 * @param creation_date	The creation date of the ticket.
	 * @param last_updated	The last time someone replied to the ticket.
	 */
	public Ticket(int ticket_id, String user_id, String title, String status, String priority, LocalDateTime creation_date, LocalDateTime last_updated) {
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

	public LocalDateTime getCreationDate() {
		return creation_date;
	}
	
	public LocalDateTime getLastUpdated() {
		return last_updated;
	}
	
	public void setLastUpdated(LocalDateTime last_updated) {
		this.last_updated = last_updated;
	}
}
