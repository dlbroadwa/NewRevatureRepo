package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;

import javax.imageio.ImageIO;

/**
 * The Post model class, corresponding with the Posts table in the database.
 */
public class Post {
    private int postID;
    private String userID;
    private String body;
    private int rating;
    private String imageFileName;
    private InputStream image;
    private Timestamp createTime;
    private Location location;
    private String title;
    private String uri;

    /**
     * Constructor taking in the various parameters of a Post object
     * @param postID int postID
     * @param userID String userID
     * @param body String body
     * @param rating int rating
     * @param imageFileName String imageFileName
     * @param image InputStream image
     * @param createTime Timestamp createTime
     * @param location Location location
     * @param title  String title
     * @param uri String uri
     */
    public Post(int postID, String userID, String body, int rating, String imageFileName, InputStream image, Timestamp createTime, Location location, String title, String uri) {
        this.postID = postID;
        this.userID = userID;
        this.body = body;
        this.rating = rating;
        this.imageFileName = imageFileName;
        this.image = image;
        this.createTime = createTime;
        this.location = location;
        this.title = title;
        this.uri = uri;
    }

    /**
     * Another constructor for Post object.
     * Image passed in as argument will be read, converted to ByteArray, and then encoded to String.
     * @param userID String userID
     * @param body String body
     * @param rating int rating
     * @param imageFileName String imageFileName
     * @param image InputStream image
     * @param location Location location
     * @param title String title
     */
    public Post(String userID, String body, int rating, String imageFileName, InputStream image, Location location, String title) {
        this.postID = -1;
        this.userID = userID;
        this.body = body;
        this.rating = rating;
        this.imageFileName = imageFileName;
        this.image = image;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.location = location;
        this.title = title;
        // Convert image to URI
        BufferedImage bi = null;
		try {
			bi = ImageIO.read(image);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(bi, "PNG", out);
			byte[] bytes = out.toByteArray();
			String base64bytes = Base64.getEncoder().encodeToString(bytes);
			uri = "data:image/png;base64," + base64bytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public String getURI() {
		return uri;
	}

	public void setURI(String uri) {
		this.uri = uri;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public void setBody(String body) {
        this.body = body;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setImageFileName(String imageFileName) {
    	this.imageFileName = imageFileName;
    }
    
    public void setImageName(InputStream image) {
        this.image = image;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getPostID() {
        return postID;
    }

    public String getUserID() {
        return userID;
    }

    public String getBody() {
        return body;
    }

    public int getRating() {
        return rating;
    }

    public String getImageFileName() {
    	return imageFileName;
    }
    
    public InputStream getImage() {
        return image;
    }
    
    public Timestamp getCreateTime() {
    	return createTime;
    }
}
