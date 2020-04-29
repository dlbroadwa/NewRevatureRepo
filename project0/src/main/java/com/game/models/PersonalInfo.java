package com.game.models;

public class PersonalInfo {
    private String username;
    private String password;
    private String email;
    private String bankAccount;

    public PersonalInfo(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    private void setBankAccount(String bankAccount){
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }
}
