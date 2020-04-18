package com.company.consoleMenus;

import com.company.DAO.BankAccountDAO;
import com.company.application.ATMApplication;
import com.company.banking.Account;
import com.company.services.BankAccountServices;

import java.math.BigDecimal;

public class TransactionInputScreen extends InputScreen {
    private static final String NON_NUMBER_INPUT_MESSAGE_RESPONSE = "Please input a number.";

    private double parseAmount(String amount) throws NumberFormatException {

        String parsedAmount;
        int decimalPointIndex = amount.indexOf('.');
        if (decimalPointIndex == -1) parsedAmount = amount;
        else parsedAmount = amount.substring(0, decimalPointIndex + 3);

        return Double.parseDouble(parsedAmount);
    }
    @Override
    public Screen run(ATMApplication app) {
        final String notAnOption = "The input given is not an option. Please enter just the number for the option.";
        // TODO add transaction and save to database
        // TODO Add Admin options like 'create account' and 'delete account'
        while(true) {
            if (app.getCredentialsEntered().isAdmin()) {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Create Bank Account\n\t2) Delete Bank Account\n\t3) Create Login Account\n\t4) Delete Login Account\n\t5) Add customer to bank account \n\t6) Logout")[0]) {
                    case "1":
                        String[] input = prompt(app.getScan(), "Enter the name of the username of the customer for the bank account:", "Enter the initial amount to put in the new account:");
                        // TODO finish code for creating accounts
                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    case "5":

                        break;
                    case "6":
                        app.setCredentialsEntered(null);
                        return new LoginScreen();
                    default:
                        System.out.println(notAnOption);
                }
            } else {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Deposit\n\t2) Withdraw\n\t3) Transfer\n\t4) Display AccountsIDs for Current User\n\t5) Display Account\n\t6) Logout")[0]) {
                    case "1":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to deposit to:", "Enter the amount you would like to deposit:");
                            BankAccountServices.doDepositOnAccount(Integer.parseInt(input[0]), parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "2":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to withdraw from:", "Enter the amount you would like to withdraw:");
                            BankAccountServices.doWithdrawalOnAccount(Integer.parseInt(input[0]), parseAmount(input[1]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "3":
                        try {
                            String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to transfer from:", "Enter the AccountID of the account you wish to transfer to:", "Enter the amount you would like to transfer:");
                            BankAccountServices.doTransferOnAccounts(Integer.parseInt(input[0]), Integer.parseInt(input[1]), parseAmount(input[2]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "4":
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser("", null, null);
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser(app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        break;
                    case "5":
                        try {
                            BankAccountServices.printAccountToScreen(Integer.parseInt(prompt(app.getScan(), "AccountID:")[0]), app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        } catch (NumberFormatException e) {
                            System.out.println(NON_NUMBER_INPUT_MESSAGE_RESPONSE);
                        }
                        break;
                    case "6":
                        app.setCredentialsEntered(null);
                        return new LoginScreen();
                    default:
                        System.out.println(notAnOption);
                }
            }
        }
    }
}
