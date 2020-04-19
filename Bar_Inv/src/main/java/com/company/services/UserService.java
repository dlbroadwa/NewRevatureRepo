package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private Repository<User, String, String> repo;

    public UserService(Repository<User,String, String> repo) {this.repo=repo;}

    public List<User> getAllUsers() throws SQLException {
        return this.repo.findAll();
    }

    public Integer userByName (String name, String pass){
        User tmp = this.repo.findByID(name);
        if (pass.equals(tmp.getPassword())){
            return 1;
        } else {
            return 0;
        }
    }

    public int addUser(String name, String pass){
        if((this.repo.findByID(name))==null){
            User newPerson = new User();
            newPerson.setPassword(pass);
            newPerson.setUserName(name);
            this.repo.save(newPerson);
            return 1;
        } else {
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
