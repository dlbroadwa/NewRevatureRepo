package main.java.com.model;


import java.util.List;
/**
 * @author that-team
 * This class defines a user.
 * The user has:
 * @param id - User ID, generated in database
 * @param firstName - user's first name
 * @param lastName - user's last name
 * @param phoneNumber - used with carrier to generate push notifications
 * @param carrier - used with phoneNumber to generate push notifications
 * @param email - used for login and email notifications
 * @param password - used for logging in
 * @param address - address, used for finding items near user's home
 * @param experiencePoints - amount of experience the user has gained from placing and retrieving items, used for earning badges
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String  phoneNumber;
    private PhoneCarrier carrier;
    private String email;
    private String password;
    private Address address;
    private int experiencePoints;

    public User(){}

    public User(String firstName, String lastName, String phoneNumber, PhoneCarrier carrier, String email, String password, Address address, List<Item> itemsHistory, int experiencePoints) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.carrier = carrier;
        this.email = email;
        this.password = password;
        this.address = address;
        this.experiencePoints = experiencePoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneCarrier getCarrier() {
        return carrier;
    }

    public void setCarrier(PhoneCarrier carrier) {
        this.carrier = carrier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
