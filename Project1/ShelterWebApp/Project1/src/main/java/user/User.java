package user;

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
 *
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method signature.
 *
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 29 April 2020
 */

public interface User {
	public boolean userAuth(String username, String password);
	public String getUserType();
}
