package main.java.com.ex.users;

/**
 * User: abstract model of a user account
 * 
 * class variables
 * protected String first_name: a user's first name
 * protected String last_name: a user's last name
 * protected String username: a user's username used during login
 * protected String password: a user's password used during login
 * protected char access_level: a user's access level 'c' for customer and 't' for teller
 * 
 * @author jtb12_000
 *
 */

public abstract class User {

	protected String first_name;
	protected String last_name;
	protected String username;
	protected String password;
	protected char access_level;
	
	/*
	 * Getters and Setters for class variables
	 */
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getAccess_level() {
		return access_level;
	}
	public void setAccess_level(char access_level) {
		this.access_level = access_level;
	}
}
