package data;

import model.Location;
import model.Post;
import utils.ConnectionUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Object class for Post model, using a String as primary key index.
 */
public class PostRepository implements Repository<Post, Integer> {
    private final ConnectionUtils connectionUtil;

    /**
     * Constructor will be called in the Servlet initiation where a configured ConnectionUtil object will be provided.
     * @param connectionUtils - configured instance of ConnectionUtils
     */
    public PostRepository(ConnectionUtils connectionUtils){
        this.connectionUtil = connectionUtils;
    }

    /**
     * Queries the database for all the Posts with the foreign key userId
     * @param userId - String userId to use as foreign key
     * @return returns a List of Posts that correspond to the userId foreign key
     */
    public List<Post> findAllByUser(String userId){
        Connection connection = null;
        List<Post> list = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id) WHERE user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                InputStream is = new ByteArrayInputStream(resultSet.getBytes("image_file"));
                Location location = new Location(resultSet.getInt("location_id"), resultSet.getString("name"), resultSet.getString("street_number"), 
                							resultSet.getString("route"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("zipcode"), 
                							resultSet.getString("country"), resultSet.getFloat("latitude"), resultSet.getFloat("longitude"));
                Post tempPost = new Post(
                    resultSet.getInt("post_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("body"),
                    resultSet.getInt("rating"),
                    resultSet.getString("image_file_name"),
                    is,
                    resultSet.getTimestamp("created_at"),
                    location,
                    resultSet.getString("title"),
                	resultSet.getString("uri"));
                list.add(tempPost);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

        }
        return list;
    }

    /**
     * Queries the database for a Post by the primary key postId
     * @param postId - Integer postId to use as primary key
     * @return returns a Post object
     */
    @Override
    public Post findById(Integer postId) {
        Connection connection = null;
        Post post = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id) WHERE post_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                InputStream is = new ByteArrayInputStream(resultSet.getBytes("image_file"));
                Location location = new Location(resultSet.getInt("location_id"), resultSet.getString("name"), resultSet.getString("street_number"), 
						resultSet.getString("route"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("zipcode"), 
						resultSet.getString("country"), resultSet.getFloat("latitude"), resultSet.getFloat("longitude"));
                post = new Post(
            		resultSet.getInt("post_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("body"),
                    resultSet.getInt("rating"),
                    resultSet.getString("image_file_name"),
                    is,
                    resultSet.getTimestamp("created_at"),
                    location,
                    resultSet.getString("title"),
                	resultSet.getString("uri"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return post;
    }

    /**
     * Queries the database to fetch all entries in the Posts table
     * @return returns a List of Post objects
     */
    @Override
    public List<Post> findAll() {
    	Connection connection = null;
    	List<Post> posts = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InputStream is = new ByteArrayInputStream(resultSet.getBytes("image_file"));
                Location location = new Location(resultSet.getInt("location_id"), resultSet.getString("name"), resultSet.getString("street_number"), 
						resultSet.getString("route"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("zipcode"), 
						resultSet.getString("country"), resultSet.getFloat("latitude"), resultSet.getFloat("longitude"));
                Post post = new Post(
            		resultSet.getInt("post_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("body"),
                    resultSet.getInt("rating"),
                    resultSet.getString("image_file_name"),
                    is,
                    resultSet.getTimestamp("created_at"),
                    location,
                    resultSet.getString("title"),
                	resultSet.getString("uri"));
                posts.add(post);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    	return posts;
    }

    /**
     * Queries the database for the 10 most recent Post entries
     * @return returns a List of 10 Post objects
     */
    public List<Post> findRecent() {
    	Connection connection = null;
    	List<Post> posts = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id) ORDER BY created_at DESC LIMIT 9;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InputStream is = new ByteArrayInputStream(resultSet.getBytes("image_file"));
                Location location = new Location(resultSet.getInt("location_id"), resultSet.getString("name"), resultSet.getString("street_number"), 
						resultSet.getString("route"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("zipcode"), 
						resultSet.getString("country"), resultSet.getFloat("latitude"), resultSet.getFloat("longitude"));
                Post post = new Post(
                		resultSet.getInt("post_id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("body"),
                        resultSet.getInt("rating"),
                        resultSet.getString("image_file_name"),
                        is,
                        resultSet.getTimestamp("created_at"),
                        location,
                        resultSet.getString("title"),
                    	resultSet.getString("uri"));
                posts.add(post);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    	return posts;
    }

    /**
     * Writes to the database to create an entry in the Posts table
     * @param obj - Post object to be persisted in the database
     * @return returns the Post object that has been written, now with a postId generated by database
     */
    @Override
    public Post create(Post obj) {
    	Connection connection = null;
    	Post post = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT INTO blog.posts VALUES (DEFAULT,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getUserID());
            preparedStatement.setString(2, obj.getBody());
            preparedStatement.setInt(3, obj.getRating());
            preparedStatement.setString(4, obj.getImageFileName());
            preparedStatement.setBinaryStream(5, obj.getImage());
            preparedStatement.setTimestamp(6, obj.getCreateTime());
            preparedStatement.setInt(7, obj.getLocation().getLocationId());
            preparedStatement.setString(8, obj.getTitle());
            preparedStatement.setString(9, obj.getURI());
            if (preparedStatement.executeUpdate() == 1) {
                post = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return post;
    }

    /**
     * Writes to the database to update a Post entry with corresponding id
     * @param obj - Post object to be updated
     * @return returns the Post object that has been updated in the database
     */
    @Override
    public Post update(Post obj) {
    	Connection connection = null;
    	Post post = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "UPDATE blog.posts SET user_id=?, body=?, rating=?, image_file_name=?, image_file=?, created_at=?, location_id=?, title=?, uri=? "
            						+ "where post_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getUserID());
            preparedStatement.setString(2, obj.getBody());
            preparedStatement.setInt(3, obj.getRating());
            preparedStatement.setString(4, obj.getImageFileName());
            preparedStatement.setBinaryStream(5, obj.getImage());
            preparedStatement.setTimestamp(6, obj.getCreateTime());
            preparedStatement.setInt(7, obj.getLocation().getLocationId());
            preparedStatement.setString(8, obj.getTitle());
            preparedStatement.setString(9, obj.getURI());
            preparedStatement.setInt(10, obj.getPostID());
            if (preparedStatement.executeUpdate() == 1) {
                post = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return post;
    }

    /**
     * Deletes a given Post entry from the database using the Post object's postId
     * @param obj - Post object to be deleted
     */
    @Override
    public void delete(Post obj) {
    	Connection connection = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "DELETE FROM blog.posts WHERE post_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, obj.getPostID());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * Queries the database to find Post entries by their foreign keyed location
     * @param location_id - foreign key int for locationId
     * @return returns a List of Post objects
     */
    public List<Post> findAllByLocation(int location_id) {
        Connection connection = null;
        List<Post> list = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id) WHERE location_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, location_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                InputStream is = new ByteArrayInputStream(resultSet.getBytes("image_file"));
                Location location = new Location(resultSet.getInt("location_id"), resultSet.getString("name"), resultSet.getString("street_number"), 
						resultSet.getString("route"), resultSet.getString("city"), resultSet.getString("state"), resultSet.getString("zipcode"), 
						resultSet.getString("country"), resultSet.getFloat("latitude"), resultSet.getFloat("longitude"));
                Post tempPost = new Post(
            		resultSet.getInt("post_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("body"),
                    resultSet.getInt("rating"),
                    resultSet.getString("image_file_name"),
                    is,
                    resultSet.getTimestamp("created_at"),
                    location,
                    resultSet.getString("title"),
                	resultSet.getString("uri"));
                list.add(tempPost);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }

        }
        return list;
    }

    /**
     * Deletes all Post entries given a locationId foreign key
     * @param location_id - int locationId to delete Post entries by foreign key
     */
    public void deleteAllAtLocation(int location_id) {
    	Connection connection = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "DELETE FROM blog.posts WHERE location_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, location_id);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
