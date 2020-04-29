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
 *     28 April 2020, Barthelemy Martinon,    Added new firstname, lastname and ID attributes, with appropriate
 *                                              additions made to getters and setters.
 *                                            Employees have more information than Customers for the sake of
 *                                              authentication.
 * <br>
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 29 April 2020
 */

public class Employee implements User {

	//Instance Variables
	String firstname = null;
	String lastname = null;
	int ID = -1;
	String username = null;
	String password = null;

	// Constructor
	public Employee(String firstname, String lastname, int ID, String username, String password) {
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

	public void setFirstname(String lastname) { this.firstname = firstname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public void setID(int ID) { this.ID = ID; }

	public void setUsername(String username) { this.username = username; }

	public void setPassword(String password) { this.password = password; }

	// Methods

	public boolean userAuth(String userInput, String passInput) {
		if ((this.username).equals(userInput) && (this.password).equals(passInput)) {
			return true;
		}
		return false;
	}

	public String getUserType() { return "Customer"; }

}
