package com.game.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalInfo {
    private String username;
    private String password;
    private String email;
    private List<String> friends;
    private String bankAccount;

    public PersonalInfo(String username, String password, String email, String friends) {
        this.friends = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.email = email;
        if (friends.equals("")){
            return;
        }
        String[] temp = friends.split(",");
        this.friends.addAll(Arrays.asList(temp));
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
