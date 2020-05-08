package dev.models.user;

/**
 *  Project 1:<br>
 * <br>
 *  The Admin class serves as a representation of a shelter admin/manager.
 *  	Admins have the ability to add users into, remove pets from and update the database containing User
 *  	information.
 *  Employee is a subclass of User.
 *
 *  <br> <br>
 *  Created: <br>
 *     01 May 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     01 May 2020, Barthelemy Martinon,    Created class.
 *     										Implemented constructor, getter, and userAuth methods.
 *     									    Ported all subclass getters and setters, attributes into User.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 01 May 2020
 */

public class Admin extends User {

	// Instance Variables
	private final String userType;

	// Constructor
	public Admin(String firstname, String lastname, int ID, String username, String password) {
		super(firstname,lastname,ID,username,password);
		this.userType = "admin";
	}

	// Getter Methods

	public String getUserType() { return this.userType; }

	// Methods

	/**
	 * Returns a boolean that shows whether the input credentials match the user's.
	 */
	public boolean userAuth(String userInput, String passInput) {
		if ((this.username).equals(userInput) && (this.password).equals(passInput)) {
			return true;
		}
		return false;
	}

}
