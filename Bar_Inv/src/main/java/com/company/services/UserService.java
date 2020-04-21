package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private Repository<User, String, String> repo;

    public UserService(Repository<User,String, String> repo) {this.repo=repo;}

    public List<User> getAllUsers() throws SQLException {
        //get all users, return a list of User objects
        return this.repo.findAll();
    }

    public Integer userByName (String name, String pass){
        //check to see if the entered password matches the saved password for the entered username
        User tmp = this.repo.findByID(name);
        if (pass.equals(tmp.getPassword())){
            return 1;
        } else {
            return 0;
        }
    }

    public int addUser(String name, String pass){
        //take a username and a password
        //first check to see if that username is taken
        if((this.repo.findByID(name))==null){ //if it isn't taken, create an new User object and give it to the save function
            User newPerson = new User();
            newPerson.setPassword(pass);
            newPerson.setUserName(name);
            this.repo.save(newPerson);
            return 1;
        } else { //if it is taken, let the user know
            System.out.println("That username is taken, please try another");
            return 0;
        }
    }

   //not worried about these
    public void removeUser(String id){
        this.repo.deleteByID(id);
    }
    public void updateUser(User user){
        this.repo.updateByID(user);
    }
}
