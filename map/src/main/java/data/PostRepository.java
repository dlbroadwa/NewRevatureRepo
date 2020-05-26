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
 *
 * this class is implement the Repo class
 * it is make sure that there is connection
 * It Provides all method that related to database
 *
 */

public class PostRepository implements Repository<Post, Integer> {
	
    private final ConnectionUtils connectionUtil;
    
    public PostRepository(ConnectionUtils connectionUtils){
        this.connectionUtil = connectionUtils;
    }

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
    
    public List<Post> findByCityState(String city, String state) {
        Connection connection = null;
        List<Post> list = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.posts INNER JOIN blog.locations ON (blog.posts.location_id = blog.locations.location_id) WHERE state=? AND city=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, city);
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

}

/**
 * End of this class
 */
