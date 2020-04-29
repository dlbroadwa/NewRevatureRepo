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
 * <br>
 *     28 April 2020, Barthelemy Martinon,    Removed username and password attributes, replaced with new firstname,
 *                                              lastname and ID attributes, with appropriate changes made to getters and
 *                                              setters.
 *                                            Customers cannot log into the system, hench userAuth is always false.
 * <br>
 *     29 April 2020, Barthelemy Martinon,    Implemented getUserType method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 29 April 2020
 */
public class Customer implements User {

    //Instance Variables
    String firstname = null;
    String lastname = null;
    int ID = -1;

    // Constructor
    public Customer(String firstname, String lastname, int ID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.ID = ID;
    }

    // Getter Methods

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public int getID() { return ID; }

    // Setter Methods

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public void setID(int ID) { this.ID = ID; }

    // Methods

    public boolean userAuth(String userInput, String passInput) {
        return false;
    }

    public String getUserType() { return "Customer"; }
}
