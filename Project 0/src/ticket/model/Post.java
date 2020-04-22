package ticket.model;

import java.time.LocalDateTime;

/**
 * Post --- Represents a post to a ticket.
 * @author Austin Kind
 */
public class Post {

	private int ticket_id;
	private String poster_id;
	private LocalDateTime creation_date;
	private int post_order;
	private String body;
	
	/**
	 * Constructs the object.
	 * @param ticket_id		The ID of the ticket the post is replying to.
	 * @param poster_id		The user ID of the user making the post.
	 * @param post_order	The post's number on the reply chain to the ticket.
	 * @param body			The contents of the post.
	 */
	public Post(int ticket_id, String poster_id, int post_order, String body) {
		this.ticket_id = ticket_id;
		this.poster_id = poster_id;
		this.creation_date = LocalDateTime.now();
		this.post_order = post_order;
		this.body = body;
	}
	
	/**
	 * Constructs the object.
	 * @param ticket_id		The ID of the ticket the post is replying to.
	 * @param poster_id		The user ID of the user making the post.
	 * @param post_order	The post's number on the reply chain to the ticket.
	 * @param body			The contents of the post.
	 * @param creation_date	The date the post was created.
	 */
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
