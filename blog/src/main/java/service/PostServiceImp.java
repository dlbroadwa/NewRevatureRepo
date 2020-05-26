package service;

import data.PostRepository;
import model.Location;
import model.Post;

import java.io.InputStream;
import java.util.List;

/**
 * Implementation class of the PostService interface. Handles the logic between Servlets and the PostRepository DAO.
 */
public class PostServiceImp implements PostService {
    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    /**
     * Generates a new post with a new timestamp
     * @param username user that message is from
     * @param body content of the message
     * @param image image passed in
     * @param rating rating
     * @param location Location object related to the Post
     */
    @Override
    public void createNewPost(String username, String body, int rating, String imageFileName, InputStream image, Location location, String title) {
        Post temp = new Post(username, body, rating, imageFileName, image, location, title);
        postRepository.create(temp);
    }

    /**
     * Finds global messages
     * @return list of all posts
     */
    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }

    public List<Post> findRecent() {
    	return postRepository.findRecent();
    }
    
    /**
     * Find all posts at a given location
     * @param location_id int foreign key for related Location
     * @return all post about a given location
     */
    @Override
    public List<Post> findAllPostsAtLocation(int location_id){
        return postRepository.findAllByLocation(location_id);
    }

    /**
     *Retrieves a list of post by a user
     * @param username user id of posts to be retrieved
     * @return list of posts
     */
    @Override
    public List<Post> findAllByUser(String username){
        return postRepository.findAllByUser(username);
    }

    /**
     * Deletes post from repository
     * @param post post to be deleted
     */
    @Override
    public void delete(Post post){
        postRepository.delete(post);
    }

    /**
     * Deletes all post related to a given location
     * @param location_id int foreign key for related Location
     */
    @Override
    public void deleteAllAtLocation(int location_id){
        postRepository.deleteAllAtLocation(location_id);
    }
}
