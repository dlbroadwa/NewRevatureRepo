package com.company.login;
/**
 * Connects to Database and verifies user login credentials and returns the found user
 */

import com.company.banking.BankCustomer;
import com.company.DataAccess.DAOI;
import com.company.validation.Validate;

public class LoginService {

    Validate validation = new Validate();

    private DAOI<BankCustomer, Integer> repo;

    public LoginService(){};

    public LoginService(DAOI<BankCustomer, Integer> repo) {
        this.repo = repo;
    }

    public BankCustomer login (String userName, String passWord)
    {
        BankCustomer temp = repo.findByUserNamePassword(userName, passWord);
        return temp;
    }


}
