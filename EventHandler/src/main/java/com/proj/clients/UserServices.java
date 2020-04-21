package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.User;

import java.sql.SQLException;
import java.util.List;

//**********************Methods for updating the users table in AWS RDB*********************//
/**
 * the event services holds methods to be called to change the data
 * inside the users table of my AWS RDB
 */
public class UserServices {
    private Repository<User, String> userRepo;

    public UserServices(Repository<User, String> userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() throws SQLException {
        return this.userRepo.findAll();
    }


    public void addUser(String name, String password) throws SQLException {
        User newUser = new User();
        newUser.setNewUsername(name);
        newUser.setNewPassword(password);
        this.userRepo.save(newUser);
    }
}
