package com.revature;

import java.util.LinkedList;

public abstract class BankUserDAO {
    abstract LinkedList<User> getAllUser();
    abstract void insertUser(String newFirstName, String newLastName, String newPhoneNumber, String newUserPin, String newUser_id, String new_is_active);
    abstract void updateUser();

}
