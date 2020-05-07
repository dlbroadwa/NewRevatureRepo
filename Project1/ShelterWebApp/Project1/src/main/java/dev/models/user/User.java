package dev.models.user;

/**
 *  Project 1:<br>
 * <br>
 *  The User interfaces serves as an interface for all User types, authentification and activity when interacting with
 *  	the shelter application.
 *  As of 4/24/2020, it is expected that there will be two User types: Employee and Customer.
 *  	Employees will have access to database manipulation, while Customers will only be able to view entries and
 *  	submit requests.
 *
 *  <br> <br>
 *  Created: <br>
 *     24 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     24 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented userAuth method signature.
 * <br>
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method signature.
 * <br>
 *     01 May 2020, Barthelemy Martinon,    Converted User into an abstract class.
 *     										Ported all subclass getters and setters, attributes into User.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 01 May 2020
 */

public abstract class User {

	//Instance Variables
	String firstname = null;
	String lastname = null;
	int ID = -1;
	String username = null;
	String password = null;

	public User(String firstname, String lastname, int ID, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.ID = ID;
		this.username = username;
		this.password = password;
	}

	// Getter Methods

	public String getFirstname() { return firstname; }

	public String getLastname() { return lastname; }

	public int getID() { return ID; }

	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	// Setter Methods

	public void setFirstname(String firstname) { this.firstname = firstname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public void setID(int ID) { this.ID = ID; }

	public void setUsername(String username) { this.username = username; }

	public void setPassword(String password) { this.password = password; }

	// Methods

	/*
	 * Prints the non-senstive information that is available on all Pets
	 */
	public String printBaseInfo() {
		String output = (" ID # " + this.getID() + " | Name: " + this.getFirstname() + " " + this.getLastname());
		return output;
	}

	public abstract boolean userAuth(String username, String password);
	public abstract String getUserType();
}
