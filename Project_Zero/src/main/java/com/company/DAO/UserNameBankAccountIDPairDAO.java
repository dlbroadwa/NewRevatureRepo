package com.company.DAO;

import com.company.Banking.UserNameBankAccountIDPair;

import java.util.ArrayList;

public class UserNameBankAccountIDPairDAO implements DAO<UserNameBankAccountIDPair> {

    private ArrayList<UserNameBankAccountIDPair> pairs = new ArrayList<UserNameBankAccountIDPair>();

    @Override
    public Integer save(UserNameBankAccountIDPair obj) {
        return null;
    }

    @Override
    public ArrayList<UserNameBankAccountIDPair> retrieveAll() {
        return null;
    }

    @Override
    public UserNameBankAccountIDPair retrieve(UserNameBankAccountIDPair obj) {
        return null;
    }

    @Override
    public void delete(UserNameBankAccountIDPair obj) {

    }

    public int[] retrieveAllAccountsAssociatedWithUserName(String username) {
        return null;
    }

    public String retrieveAllUserNamesAssociatedWithAccount(int accountID) {
        return null;
    }
}
