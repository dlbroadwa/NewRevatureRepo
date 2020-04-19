package BankApp.controller;

import BankApp.dao.user.AbstractUserDao;
import BankApp.model.User;

public class AuthenticationController {

    AbstractUserDao userDao;

    public AuthenticationController(AbstractUserDao userDao) {
        this.userDao = userDao;
    }

    public User login (String username, String password){
        User user;
        if (userDao.validUser(username, password)) {
            user = userDao.getUser(username, password);
        } else {
            user = null;
        }
        return user;
    }

    public void setUserDao(AbstractUserDao userDao) {
        this.userDao = userDao;
    }
}
