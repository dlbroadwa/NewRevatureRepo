package com.data;

import com.model.Accounts;
import com.model.Users;

import java.util.List;

/**
 * Accounts DAO interface to direct implementing class specific Data operations with database
 */
public interface IAccounts <T, ID> {
    void updateBalance (Accounts obj, float amount);
    T findByAccount(String email);
    List<T> findAll();
    T createNewAccount(Users user, String type, float initialBalance);


}
