package dev.models.user;

/**
 *  Project 1:<br>
 * <br>
 *  The Customer class serves as a representation of a shelter customer.
 *  	Customers only have the ability to view information on the database containing Pet details and the abililty to
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
 * <br>
 *     28 April 2020, Barthelemy Martinon,    Removed username and password attributes, replaced with new firstname,
 *                                              lastname and ID attributes, with appropriate changes made to getters and
 *                                              setters.
 *                                            Customers cannot log into the system, hench userAuth is always false.
 * <br>
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method.
 * <br>
 *     01 May 2020,   Barthelemy Martinon,    Readded username and password attributes with appropriate getters and
 *                                              setters.
 *                                            Customers now have credentials for authentification.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 01 May 2020
 */
public class Customer extends User {

    // Instance Variables
    private final String userType;

    // Constructor
    public Customer(String firstname, String lastname, int ID, String username, String password) {
        super(firstname,lastname,ID,username,password);
        this.userType = "customer";
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
