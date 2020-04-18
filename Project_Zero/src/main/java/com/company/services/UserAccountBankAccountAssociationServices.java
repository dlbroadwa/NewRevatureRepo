package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.LoginAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.UserNameBankAccountIDPair;

public class UserAccountBankAccountAssociationServices {
    public static void createAssociation(String username, int accountID, UserNameBankAccountIDPairDAO pairDAO, BankAccountDAO bankAccountDAO, LoginAccountDAO loginAccountDAO) {

        // check if account exists
        if (!BankAccountServices.accountExists(accountID, bankAccountDAO)) {
            System.out.println(BankAccountServices.ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        // check if username exists
        if (!LoginServices.loginAccountExists(username, loginAccountDAO)) {
            System.out.println(LoginServices.ACCOUNT_DOES_NOT_EXIST);
            return;
        }

        // create and save the new association
        pairDAO.save(new UserNameBankAccountIDPair(accountID, username));
    }
}
