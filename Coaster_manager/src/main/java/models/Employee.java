package models;

/**
 * Employee Class
 * Created by: Paityn Maynard on May 11,2020
 */
public class Employee {//Start of Employees Class
//Instance Variables
    String fname, lname, phoneNum, email;
    int id, bossid;
    boolean isAdmin;
//Constructors
    public Employee(){}

    public Employee(String fname, String lname, String phoneNum, String email, int id, int bossid, boolean isAdmin){//This is a constructors for Employees
        this.fname=fname;
        this.lname=lname;
        this.phoneNum=phoneNum;
        this.email=email;
        this.id=id;
        this.bossid=bossid;
        this.isAdmin=isAdmin;
    }

    public Employee(String fname, String lname, String phoneNum, String email, int id, boolean isAdmin){//This is a constructors for Admin
        this.fname=fname;
        this.lname=lname;
        this.phoneNum=phoneNum;
        this.email=email;
        this.id=id;
        this.isAdmin=isAdmin;
    }
//Getters
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getBossid() {
        return bossid;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

//Setters
    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBossid(int bossid) {
        this.bossid = bossid;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}//End of Employees Class
