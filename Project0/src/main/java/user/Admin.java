package user;

/**
 *  Project 0:<br>
 * <br>
 *  The Admin class serves as a representation of a library system administrator.
 *  	Admins have the ability to add books into, remove books from and update the library Catalog.
 *  	They can also check books in and out of the system.
 *  Admin is a subclass of User.
 *
 *  <br> <br>
 *  Created: <br>
 *     09 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     09 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented userAuth method signature.
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: Joshua Ragasa, Rayan Vakil
 *  @version 09 April 2020
 */

public class Admin implements User {

	//Instance Variables
	String username = null;
	String password = null;
	
	// Constructor
	public Admin(String username, String password) {
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
	
	@Override
	public boolean userAuth(String userInput, String passInput) {
		if ((this.username).equals(userInput) && (this.password).equals(passInput)) {
			return true;
		}
		return false;
	}

}
