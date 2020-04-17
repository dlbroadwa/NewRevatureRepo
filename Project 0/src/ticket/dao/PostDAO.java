package ticket.dao;

import java.util.List;

import ticket.model.Post;

public interface PostDAO {

	List<Post> getPostsFromTicket(int ticket_id);
	
	int getNextPostNumber(int ticket_id);
	
	boolean addPost(Post post);
}
