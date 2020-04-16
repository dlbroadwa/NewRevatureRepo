package com.company.DAO;

import com.company.banking.UserNameBankAccountIDPair;

import java.util.ArrayList;

public class UserNameBankAccountIDPairDAO implements DAO<UserNameBankAccountIDPair, Integer> {

    //private ArrayList<UserNameBankAccountIDPair> pairs = new ArrayList<UserNameBankAccountIDPair>();

    @Override
    public Integer save(UserNameBankAccountIDPair obj) {
        return null;
    }

    @Override
    public ArrayList<UserNameBankAccountIDPair> retrieveAll() {
        return null;
    }

    @Override
    public UserNameBankAccountIDPair[] retrieveByID(Integer accountID) {
        return null;
    }

    @Override
    public void delete(UserNameBankAccountIDPair obj) {

    }

    @Override
    public void update(UserNameBankAccountIDPair newObj) {

    }

    public UserNameBankAccountIDPair[] retrieveByID(String userName) {
        return null;
    }
}
