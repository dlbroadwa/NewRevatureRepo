package com.proj.models;

public class User {
    private int userID;
    private String userName;
    private String password;
    private String newUsername;
    private String newPassword;


    public User() {}

    public int getUserID() { return userID; }

    public void setUserID(int userID) {
        this.userID = getUserID();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNewUsername() { return newUsername; }

    public void setNewUsername(String newUsername) { this.newUsername = newUsername; }

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
