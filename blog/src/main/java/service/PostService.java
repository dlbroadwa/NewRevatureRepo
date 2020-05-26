package service;

import java.io.InputStream;
import java.util.List;

import model.Location;
import model.Post;

/**
 * PostService interface to be implemented
 */
public interface PostService {
	void createNewPost(String username, String body, int rating, String imageFileName, InputStream image, Location location, String title);
	List<Post> findAllPostsAtLocation(int location_id);
	List<Post> findAllByUser(String username);
	void delete(Post post);
	void deleteAllAtLocation(int location_id);
}
