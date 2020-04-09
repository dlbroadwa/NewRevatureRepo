package DAO;

import Application.Account;

import java.util.ArrayList;

public interface AccountDAO {
    /**
     * Retrieves all of the accounts.
     * @return
     */
    ArrayList<Account> retrieveAccounts();

    /**
     * Retrieves a specific account based on the accountID.
     * @param accountID
     * @return Account
     */
    Account retrieveAccount(int accountID);
}
