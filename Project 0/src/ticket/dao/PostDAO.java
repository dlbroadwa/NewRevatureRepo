package ticket.dao;

import java.util.List;

import ticket.model.Post;

/**
 * PostDAO --- Accesses data from a posts table.
 * @author Austin Kind
 */
public interface PostDAO {

	/**
	 * Retrieves all the posts from the given ticket ID and returns them in a list.
	 * @param ticket_id		The ticket ID that belongs to the posts.
	 * @return 				A list of Posts from the given ticket_id.
	 */
	List<Post> getPostsFromTicket(int ticket_id);
	
	/**
	 * Retrieves the highest post_order number plus one of the posts that belong to the given ticket_id
	 * @param ticket_id		The ticket ID that belongs to the posts.
	 * @return 				The next highest post_order number
	 */
	int getNextPostNumber(int ticket_id);
	
	/**
	 * Adds the given post to the posts table.
	 * @param post		The post to be added.
	 * @return 			True if post was added successfully.
	 */
	boolean addPost(Post post);
}
