package com.ex.clients;

import com.ex.data.Repository;

import java.util.List;
import com.ex.models.User;

public class UserService {
    private Repository<User, Integer> repo;

    public UserService(Repository<User, Integer> repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        // this method may do some validation and authorization before handling
        // the request directly
        return this.repo.findAll();
    }
}
