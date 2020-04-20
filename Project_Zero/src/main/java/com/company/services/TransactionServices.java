package com.company.services;

import com.company.DAO.BankAccountDAO;
import com.company.DAO.UserNameBankAccountIDPairDAO;
import com.company.banking.Transaction;
import org.postgresql.util.PSQLException;

import static com.company.services.BankAccountServices.updateAccount;
import static com.company.services.UserAccountBankAccountAssociationServices.checkIfUserHasAccessToAccount;

public class TransactionServices {
    public static final String ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE = "Sorry, you do not have access to the account with the provided accountID, or the account with the provided accountID does not exist.";

    public static double parseAmount(String amount) throws NumberFormatException {

        String parsedAmount;
        int decimalPointIndex = amount.indexOf('.');
        if (decimalPointIndex == -1) parsedAmount = amount;
        else parsedAmount = amount.substring(0, decimalPointIndex + 3);

        return Double.parseDouble(parsedAmount);
    }


    public static void doWithdrawalOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount > 0) amount = -amount;
        else {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) updateAccount(accountID, amount, Transaction.WITHDRAWAL_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    public static void doDepositOnAccount(int accountID, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountID, username, pairDAO)) updateAccount(accountID, amount, Transaction.DEPOSIT_DESCRIPTION, bankAccountDAO);
        else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }

    public static void doTransferOnAccounts(int accountIDFrom, int accountIDTo, double amount, String username, BankAccountDAO bankAccountDAO, UserNameBankAccountIDPairDAO pairDAO) throws PSQLException {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }

        if (checkIfUserHasAccessToAccount(accountIDFrom, username, pairDAO)) {
            updateAccount(accountIDFrom, -amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
            updateAccount(accountIDTo, amount, Transaction.TRANSFER_DESCRIPTION, bankAccountDAO);
        } else System.out.println(ACCOUNT_DOES_NOT_EXIST_OR_USER_DOES_NOT_HAVE_ACCESS_MESSAGE);
    }
}
