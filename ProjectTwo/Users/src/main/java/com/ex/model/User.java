package com.ex.model;


import javax.persistence.*;
import java.util.List;
/**
 * @author that-team
 * This class defines a user.
 * The user has:
 * @param firstName - user's first name
 * @param lastName - user's last name
 * @param phoneNumber - used with carrier to generate push notifications
 * @param carrier - used with phoneNumber to generate push notifications
 * @param email - used for login and email notifications
 * @param password - used for logging in
 * @param address - address, used for finding items near user's home
 * @param experiencePoints - amount of experience the user has gained from placing and retrieving items, used for earning badges
 */

@Entity
@Table(name="\"Users\"", schema = "\"that-team_schema\"")
public class User {
    private String firstname ;
    private String lastname ;
    private String phone ;
//    private PhoneCarrier carrier;

//    @ManyToOne
    @ManyToOne
    @JoinColumn(name = "phoneCarrierID")
    private PhoneCarrier phoneCarrierID ;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email ;
    private String password ;

    @ManyToOne
    @JoinColumn(name = "addressid")
    private Address addressID ;

    private int experiencepoints;

    public User(){}

    public User(String firstName, String lastName, String phoneNumber, PhoneCarrier carrier, String email, String password, Address address, int experiencePoints) {
//public User(int id, String firstName, String lastName, String phoneNumber, int carrier, String email, String password, Address address, List<Item> itemsHistory, int experiencePoints) {

        this.firstname = firstName;
        this.lastname = lastName;
        this.phone = phoneNumber;
        this.phoneCarrierID  = carrier;
        this.email = email;
        this.password = password;
        this.addressID  = address;
        this.experiencepoints = experiencePoints;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PhoneCarrier getPhonecarrierid() {
        return phoneCarrierID ;
    }

    public void setPhonecarrierid(PhoneCarrier phonecarrierid) {
        this.phoneCarrierID  = phonecarrierid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddressid() {
        return addressID ;
    }

    public void setAddressid(Address addressid) {
        this.addressID = addressid;
    }

    public int getExperiencepoints() {
        return experiencepoints;
    }

    public void setExperiencepoints(int experiencepoints) {
        this.experiencepoints = experiencepoints;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
