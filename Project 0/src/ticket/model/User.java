package ticket.model;

/**
 * User --- Represents a user of the helpdesk ticketing system.
 * @author Austin Kind
 */
public class User {
	
	private String user_id;
	private String password;
	private String first_name;
	private String last_name;
	private String email;
	private boolean admin_access;
	
	/**
	 * Constructs the object.
	 * @param user_id		The ID of the user.
	 * @param password		The password of the user.
	 * @param first_name	The user's first name.
	 * @param last_name		The user's last name.
	 * @param email			The user's email.
	 * @param admin_access	Does this user have admin access?
	 */
	public User(String user_id, String password, String first_name, String last_name, String email, boolean admin_access) {
		this.user_id = user_id;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.admin_access = admin_access;
	}

	public String getId() {
		return user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAdmin() {
		return admin_access;
	}
	
	public void setAdmin(boolean admin_access) {
		this.admin_access = admin_access;
	}
}
