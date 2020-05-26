package service;

import data.PostRepository;
import model.Location;
import model.Post;

import java.io.InputStream;
import java.util.List;

/**
 * This class implement the PostService class
 *
 * it creates method that will store data from the Database
 *
 * You will be search for all the List or Find All the Post
 */

public class PostServiceImp implements PostService {
    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findByCityState(String city, String state) {
    	List<Post> posts = postRepository.findByCityState(city, state);
    	return posts;
    }

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

}

/**
 * End of this class
 */
