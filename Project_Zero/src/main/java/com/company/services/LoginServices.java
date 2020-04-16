package com.company.services;

import com.company.DAO.LoginAccountDAO;
import com.company.application.ATMApplication;
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
}
