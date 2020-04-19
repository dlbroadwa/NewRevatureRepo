package BankApp.controller;

import BankApp.dao.user.AbstractUserDao;

public class UserController {

    private AbstractUserDao userDao;

    public UserController(AbstractUserDao userDao) {
        this.userDao = userDao;
    }

    public boolean deposit(int userId, float amount) {
        if (amount <= 0)
            return false;
        else {
            userDao.updateAmount(userId, amount, true);
            return true;
        }
    }

    public boolean withdraw(int userId, float amount , float balance){
        if (amount <= 0 || amount > balance)
           return false;
        else {
            userDao.updateAmount(userId, amount, false);
            return true;
        }
    }

    public float  getBalance(int userId) {
       return  userDao.getUser(userId).getBalance();
    }


    public void setUserDao(AbstractUserDao userDao) {
        this.userDao = userDao;
    }
}
