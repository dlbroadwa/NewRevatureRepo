package unused.user;

/**
 *  Project 0:<br>
 * <br>
 *  The User interfaces serves as an interface for unused.user choices, auth. and activity when interacting with app.Menu.
 *  	As of 4/9/2020, it is expected that only Admins (librarians) should be able to access this system.
 *  	However, this might change with the addition of Members that can only check books in and out.
 *  This interface is implemented by Admin.
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

public interface User {
	public boolean userAuth(String username, String password);
}
