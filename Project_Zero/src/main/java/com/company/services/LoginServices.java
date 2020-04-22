package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Account;
import com.company.banking.UserNameBankAccountIDPair;
import com.company.loginAccounts.LoginAccount;
import org.postgresql.util.PSQLException;

/***
 * This class contains static methods to provide business logic to for processing login accounts.
 */
public class LoginServices {
    public static final String ACCOUNT_DOES_NOT_EXIST = "The user account with the provided username does not exist.";

    /**
     * Checks if any of the provided credentials are null or empty and returns true if they are.
     * @param enteredCredentials
     * @return
     */
    public static boolean validateEnteredCredentialsAreNullOrEmpty(LoginAccount enteredCredentials) {
        return (enteredCredentials == null || enteredCredentials.getUserName().trim().isEmpty() || enteredCredentials.getPin().trim().isEmpty());
    }

    /***
     * This method verifies if the entered credentials for a login account matches the credentials in persistent storage.
     *
     * @param enteredCredentials
     * @param loginAccountDAO
     * @return
     * @throws PSQLException
     */
    public static boolean verifyLoginAccount(LoginAccount enteredCredentials, LoginAccountDAO loginAccountDAO)  throws PSQLException {
        if (validateEnteredCredentialsAreNullOrEmpty(enteredCredentials)) return false;

        LoginAccount[] retrievedAccountsByUserName = loginAccountDAO.retrieveByID(enteredCredentials.getUserName());
        if (retrievedAccountsByUserName.length == 0) return false;
        return retrievedAccountsByUserName[0].equals(enteredCredentials);
    }

    /***
     * Verifies that a username belongs to a customer's login account (non-admin login account).
     * @param username
     * @param loginAccountDAO
     * @return
     */
    public static boolean customerExists(String username, LoginAccountDAO loginAccountDAO) {
        LoginAccount[] accounts = loginAccountDAO.retrieveByID(username);
        if ((accounts == null || accounts.length == 0)) return false;
        else return (accounts.length == 1 && !(accounts[0].isAdmin()));
    }

    /***
     * Verifies that a username belongs to any login account in persistent storage.
     * @param username
     * @param loginAccountDAO
     * @return
     */
    public static boolean loginAccountExists(String username, LoginAccountDAO loginAccountDAO) {
        LoginAccount[] accounts = loginAccountDAO.retrieveByID(username);
        return !(accounts == null || accounts.length == 0);
    }

    /***
     *
     * Handles the creation of a new login account, by validating that the credentials are not empty strings, that the
     * username is unique, and then passing the validated credentials to the login account DAO to save to persistent storage.
     * @param username
     * @param pin
     * @param admin
     * @param loginAccountDAO
     * @throws PSQLException
     */
    public static void createLoginAccount(String username, String pin, boolean admin, LoginAccountDAO loginAccountDAO)  throws PSQLException {
        LoginAccount newLoginAccount = new LoginAccount(username, pin, admin);
        if (validateEnteredCredentialsAreNullOrEmpty(newLoginAccount)) {
            System.out.println("The username or pin is an empty string (\"\"). Please enter a value for both the username and pin.");
            return;
        }
        if (loginAccountExists(username, loginAccountDAO)) {
            System.out.println("The username already exists. Please enter a unique username.");
            return;
        }
        loginAccountDAO.save(newLoginAccount);
    }

    /***
     *
     * Handles the deletion of an existing login account, by make sure that the targeted account is not the currently
     * logged-in account and that the targeted account actually exists before passing the username to the login account DAO to delete from persistent storage.
     * @param username
     * @param currentUser
     * @param loginAccountDAO
     * @param bankAccountDAO
     * @param pairDAO
     * @throws PSQLException
     */
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
