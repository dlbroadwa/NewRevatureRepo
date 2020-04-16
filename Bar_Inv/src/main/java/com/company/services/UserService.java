package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private Repository<User, String> repo;

    public UserService(Repository<User,String> repo) {this.repo=repo;}

    public List<User> getAllUsers() throws SQLException {
        return this.repo.findAll();
    }
    public Boolean userByName (String name, String pass){
        User tmp = this.repo.findByID(name);
        if (pass.equals(tmp.getPassword())){
            return true;
        } else {
            return false;
        }
    }
    public void addUser(String name, String pass){
        User newPerson = new User();
        newPerson.setPassword(pass);
        newPerson.setUserName(name);
        this.repo.save(newPerson);
    }

   //not worried about these
    public void removeUser(String id){
        this.repo.deleteByID(id);
    }
    public void updateUser(String id){
        this.repo.updateByID(id);
    }
}
