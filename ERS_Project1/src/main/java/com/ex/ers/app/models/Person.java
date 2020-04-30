package com.ex.ers.app.models;

public abstract class Person {
    private int id;
    private String fname;
    private String lname;
    private String address;
    private String jobTitle;
    private String username;
    private String pw;

    public Person(int id, String fname, String lname, String address, String jobTitle, String username, String pw) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.jobTitle = jobTitle;
        this.username = username;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
