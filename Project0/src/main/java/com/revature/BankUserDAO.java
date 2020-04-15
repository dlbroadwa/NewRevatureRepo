package com.revature;

import java.util.LinkedList;

public abstract class BankUserDAO {
    public abstract LinkedList<User> getAllUser();
    public abstract boolean insertUser(String newFirstName, String newLastName, String newPhoneNumber, String newUserPin, String newUser_id, String new_is_active);
    public abstract boolean updateUser(String userId, String newValue, int fieldToUpdate);

    abstract void printAllUsers();
}
