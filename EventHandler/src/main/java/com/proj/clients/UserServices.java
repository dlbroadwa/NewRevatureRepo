package com.proj.clients;

import com.proj.data.Repository;
import com.proj.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserServices {
    private Repository<User, String> userRepo;

    public UserServices(Repository<User, String> userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() throws SQLException {
        return this.userRepo.findAll();
    }


    public void addUser(String name, String Pass) throws SQLException {
        User newUser = new User();
        newUser.getNewUsername();
        newUser.getNewPassword();
        this.userRepo.save(newUser);
    }
}
