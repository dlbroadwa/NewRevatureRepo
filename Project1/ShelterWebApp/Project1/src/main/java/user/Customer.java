package user;

/**
 *  Project 1:<br>
 * <br>
 *  The Customer class serves as a representation of a shelter customer.
 *  	Customers only have the ability to view information on the database containing Pet details and th abililty to
 *      submit requests for Employees to fulfill.
 *  Customer is a subclass of User.
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
public class Customer implements User {

    //Instance Variables
    String username = null;
    String password = null;

    // Constructor
    public Customer() { }

    // Getter Methods

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean userAuth(String userInput, String passInput) {
        return false;
    }
}
