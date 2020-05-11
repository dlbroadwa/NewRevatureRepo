package main.java.com.dao;

import main.java.com.model.User;


/**
 * This is a UserDAO interface to be implemented amongst DAO classes.
 */
public interface UserDAO {

    //
    public User loginUser(String username, String password) throws Exception;
    public void addUser(User user) throws Exception;
    public List<UserInfo> displayUser();
    public void updateUser(User user) throws Exception;
    public void deleteUser(User user) throws Exception;
}
