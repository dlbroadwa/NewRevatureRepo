package ticket.model;

import java.time.LocalDateTime;

public class Post {

	private int ticket_id;
	private String poster_id;
	private LocalDateTime creation_date;
	private int post_order;
	private String body;
	
	public Post(int ticket_id, String poster_id, int post_order, String body) {
		this.ticket_id = ticket_id;
		this.poster_id = poster_id;
		this.creation_date = LocalDateTime.now();
		this.post_order = post_order;
		this.body = body;
	}
	
	public Post(int ticket_id, String poster_id, int post_order, String body, LocalDateTime creation_date) {
		this(ticket_id, poster_id, post_order, body);
		this.creation_date = creation_date;
	}

	public int getTicketId() {
		return ticket_id;
	}

	public String getPosterId() {
		return poster_id;
	}

	public LocalDateTime getCreationDate() {
		return creation_date;
	}

	public int getPostOrder() {
		return post_order;
	}

	public String getBody() {
		return body;
	}
}
