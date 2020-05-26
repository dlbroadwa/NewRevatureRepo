package service;

import java.io.InputStream;
import java.util.List;

import model.Location;
import model.Post;


/**
 * This is an Interface class  that will be implemented with the PostServiceImp
 * it is make the code easier to be re-use
 *
 *
 */
public interface PostService {
	List<Post> findByCityState(String city, String state);
	List<Post> findAll();
}

/**
 * End of this class
 */
