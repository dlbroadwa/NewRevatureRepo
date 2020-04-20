package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.UserNameBankAccountIDPair;

/***
 * This class contains static methods to provide business logic to for the many to many relationship between the user login accounts and the bank accounts.
 *
 * @author Shawyn Kane
 */
public class UserAccountBankAccountAssociationServices {

    public static boolean checkIfUserHasAccessToAccount(int accountID, String username, UserNameBankAccountIDPairDAO pairDAO) {
        return pairDAO.relationshipBetweenUserAndAccountExists(new UserNameBankAccountIDPair(accountID, username));
    }

    /***
     * Checks if a bank account with the provided accountID exists and if a customer login account exits with the provided username. (NOTE: It excludes admin accounts.) After validation of the accountID and username it creates and saves the association to persistent data storage.
     *
     * @author Shawyn Kane
     * @param username
     * @param accountID
     * @param pairDAO
     * @param bankAccountDAO
     * @param loginAccountDAO
     */
    public static void createAssociation(String username, int accountID, UserNameBankAccountIDPairDAO pairDAO, BankAccountDAO bankAccountDAO, LoginAccountDAO loginAccountDAO) {

        // check if account exists
        if (!BankAccountServices.accountExists(accountID, bankAccountDAO)) {
            System.out.println(BankAccountServices.ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        // check if username exists
        if (!LoginServices.customerExists(username, loginAccountDAO)) {
            System.out.println(LoginServices.ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        // create and save the new association
        pairDAO.save(new UserNameBankAccountIDPair(accountID, username));
    }
}
