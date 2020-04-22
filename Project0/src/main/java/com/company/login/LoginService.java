package com.company.login;
/**
 *
 */

import com.company.Banking.BankCustomer;
import com.company.DataAccess.DAOI;
import com.company.Validation.Validate;

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
