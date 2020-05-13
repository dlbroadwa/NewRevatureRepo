package Controler;
/**
 * this is a class that will generate the getters and setters that will manage the all project
 * we have firstname lastname street Address city state zipcode Social Security account number phone number email password also
 * balance of the user account holder from database
 *
 * it is kind of all the input variables that locate in one single class
 */

import java.sql.Timestamp;
import java.util.Date;

public class AccountHolderPerSonalInfo {

    private String firstName, Lastname, StreetAddress,City, State,ZipCode,
                    SSN, gender, AccountNumber, phoneNumber, email,password;

    private double balance =0.00;
    private Timestamp dateOpenningAccount;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int registerID, accountID,LoginID;


    public int getRegisterID() {
        return registerID;
    }

    public void setRegisterID(int registerID) {
        this.registerID = registerID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getLoginID() {
        return LoginID;
    }

    public void setLoginID(int loginID) {
        LoginID = loginID;
    }


    public Timestamp getDateOpenningAccount() {
        return dateOpenningAccount;
    }

    public void setDateOpenningAccount(Timestamp dateOpenningAccount) {
        this.dateOpenningAccount = dateOpenningAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getStreetAddress() {
        return StreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        StreetAddress = streetAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
