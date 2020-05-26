package model;

/**
 * The User model class, corresponding with the User table in the database.
 */
public class User {
    String username;
    String password;
    String firstName;
    String lastName;

    /**
     * Constructor
     * @param username - Username of the user
     * @param password - Password of the user
     * @param firstName - First name of the user
     * @param lastName - Last name of the user
     */
    public User(String username, String password, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getter for firstName property
     * @return returns the firstName String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the firstName property
     * @param firstName takes a String for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName property
     * @return returns the lastName String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the lastName property
     * @param lastName takes a String for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for username property
     * @return returns the username String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username property
     * @param username takes a String for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for password property
     * @return returns the password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password property
     * @param password takes a String for password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
