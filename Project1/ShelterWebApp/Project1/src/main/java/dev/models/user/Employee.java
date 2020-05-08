package dev.models.user;

/**
 *  Project 1:<br>
 * <br>
 *  The Employee class serves as a representation of a shelter worker or service agent.
 *  	Employees have the ability to add pets into, remove pets from and update the database containing Pet
 *  	information.
 *  Employee is a subclass of User.
 *
 *  <br> <br>
 *  Created: <br>
 *     24 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     24 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented constructor, getter, and userAuth methods.
 *                                            
 * <br>
 *     28 April 2020, Barthelemy Martinon,    Added new firstname, lastname and ID attributes, with appropriate
 *                                              additions made to getters and setters.
 *                                            Employees have more information than Customers for the sake of
 *                                              authentication.
 * <br>
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method.
 * <br>
 *     01 May 2020, Barthelemy Martinon,    Ported all subclass getters and setters, attributes into User.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 01 May 2020
 */

public class Employee extends User {

	// Instance Variables
	private final String userType;

	// Constructor
	public Employee(String firstname, String lastname, int ID, String username, String password) {
		super(firstname,lastname,ID,username,password);
		this.userType = "employee";
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
