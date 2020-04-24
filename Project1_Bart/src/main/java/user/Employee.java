package user;

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
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 24 April 2020
 */

public class Employee implements User {

	//Instance Variables
	String username = null;
	String password = null;
	
	// Constructor
	public Employee(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// Getter Methods
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public boolean userAuth(String userInput, String passInput) {
		if ((this.username).equals(userInput) && (this.password).equals(passInput)) {
			return true;
		}
		return false;
	}

}
