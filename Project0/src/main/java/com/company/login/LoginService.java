package com.company.login;

import com.company.Banking.BankCustomer;
import com.company.DataAccess.DAO;
import com.company.Validation.Validate;

public class LoginService {

    Validate validation = new Validate();

    private DAO<BankCustomer, Integer> repo;

    public LoginService(){};

    public LoginService(DAO<BankCustomer, Integer> repo) {
        this.repo = repo;
    }

    public BankCustomer login (String userName, String passWord)
    {
        BankCustomer temp = new BankCustomer();
        temp = repo.findByUserNamePassword(userName, passWord);
        return temp;
    }


}
