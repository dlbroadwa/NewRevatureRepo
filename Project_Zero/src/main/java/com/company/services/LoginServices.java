package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Account;
import com.company.banking.UserNameBankAccountIDPair;
import com.company.loginAccounts.LoginAccount;
import org.postgresql.util.PSQLException;

public class LoginServices {
    public static final String ACCOUNT_DOES_NOT_EXIST = "The user account with the provided username does not exist.";

    /**
     * Validates if any of the provided credentials are null or empty and returns true if they are.
     * @param enteredCredentials
     * @return
     */
    public static boolean validateEnteredCredentialsAreNotNullOrEmpty(LoginAccount enteredCredentials) {
        return (enteredCredentials == null || enteredCredentials.getUserName().trim().isEmpty() || enteredCredentials.getPin().trim().isEmpty());
    }

    public static boolean verifyLoginAccount(LoginAccount enteredCredentials, LoginAccountDAO loginAccountDAO)  throws PSQLException {
        if (validateEnteredCredentialsAreNotNullOrEmpty(enteredCredentials)) return false;

        LoginAccount[] retrievedAccountsByUserName = loginAccountDAO.retrieveByID(enteredCredentials.getUserName());
        if (retrievedAccountsByUserName.length == 0) return false;
        return retrievedAccountsByUserName[0].equals(enteredCredentials);
    }

    public static boolean customerExists(String username, LoginAccountDAO loginAccountDAO) {
        LoginAccount[] accounts = loginAccountDAO.retrieveByID(username);
        return (accounts != null || accounts.length != 0) || (accounts.length == 1 && accounts[0].isAdmin());
    }

    public static boolean loginAccountExists(String username, LoginAccountDAO loginAccountDAO) {
        LoginAccount[] accounts = loginAccountDAO.retrieveByID(username);
        return (accounts != null || accounts.length != 0);
    }

    public static void createLoginAccount(String username, String pin, boolean admin, LoginAccountDAO loginAccountDAO)  throws PSQLException {
        loginAccountDAO.save(new LoginAccount(username, pin, admin));
    }

    public static void deleteLoginAccount(String username, LoginAccount currentUser, LoginAccountDAO loginAccountDAO, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (username.equals(currentUser.getUserName())) {
            System.out.println("Cannot delete user account while logged into said user account.");
            return;
        }
        System.out.println("\""+username+"\"");
        // check if user exists
        if (!loginAccountExists(username, loginAccountDAO)) {
            System.out.println(ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        // delete user
        loginAccountDAO.delete(new LoginAccount(username, "", false));

        // find all associated accounts
        UserNameBankAccountIDPair[] listOfAccountIDsForDeletedUser = pairDAO.retrieveByID(username);

        // if there are no accounts associated with the user account then skip deleting any accounts
        if (listOfAccountIDsForDeletedUser.length == 0) return;

        // delete all associations to accounts
        for (int i = 0; i < listOfAccountIDsForDeletedUser.length; i++) {
            int accountID = listOfAccountIDsForDeletedUser[i].getAccountID();
            // if the only association is with the deleted user then delete account
            // retrieve list of user for the current account
            UserNameBankAccountIDPair[] listOfUsersForCurrentAccount = pairDAO.retrieveByID(accountID);

            // if there is only one username associated with the accountID and it is the same as the deleted username delete the account
            if (listOfUsersForCurrentAccount.length == 1 && listOfUsersForCurrentAccount[0].equals(listOfAccountIDsForDeletedUser[i])) bankAccountDAO.delete(new Account(accountID, 0, null));

            // delete the association between the deleted username and the current account
            pairDAO.delete(listOfAccountIDsForDeletedUser[i]);
        }

    }
}
