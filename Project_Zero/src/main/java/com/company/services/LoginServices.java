package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.UserNameBankAccountIDPair;
import com.company.loginAccounts.LoginAccount;

public class LoginServices {

    /**
     * Validates if any of the provided credentials are null or empty and returns true if they are.
     * @param enteredCredentials
     * @return
     */
    public static boolean validateEnteredCredentialsAreNotNullOrEmpty(LoginAccount enteredCredentials) {
        return (enteredCredentials == null || enteredCredentials.getUserName().trim().isEmpty() || enteredCredentials.getPin().trim().isEmpty());
    }

    public static boolean verifyLoginAccount(LoginAccount enteredCredentials, LoginAccountDAO loginAccountDAO) {
        if (validateEnteredCredentialsAreNotNullOrEmpty(enteredCredentials)) return false;

        LoginAccount[] retrievedAccountsByUserName = loginAccountDAO.retrieveByID(enteredCredentials.getUserName());
        if (retrievedAccountsByUserName.length == 0) return false;
        return retrievedAccountsByUserName[0].equals(enteredCredentials);
    }

    public static void createLoginAccount(String username, String pin, boolean admin, LoginAccountDAO loginAccountDAO) {
        loginAccountDAO.save(new LoginAccount(username, pin, admin));
    }

    public static void deleteLoginAccount(String username, LoginAccountDAO loginAccountDAO, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) {
        // TODO implement deleteLoginAccount(...)
        // delete user
        // find all associated accounts
        // delete all associations to accounts
        // if the only association is with the deleted user then delete account
    }
}
