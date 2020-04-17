package com.company.consoleMenus;

import com.company.DAO.BankAccountDAO;
import com.company.application.ATMApplication;
import com.company.banking.Account;
import com.company.services.BankAccountServices;

public class TransactionInputScreen extends InputScreen {
    TransactionInputScreen() {
        super();
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
                        return new DepositInputScreen();
                    case "2":
                        String[] input = prompt(app.getScan(), "Enter the AccountID of the account you wish to withdraw from:", "Enter the amount you would like to withdraw:");
                        BankAccountServices.doWithdrawalOnAccount(Integer.parseInt(input[0]), Integer.parseInt(input[1]), app.getBankAccountDAO());
                        break;
                    case "3":
                        return new TransferInputScreen();
                    case "4":
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser("", null, null);
                        BankAccountServices.printToScreenAllAccountsAssociatedWithUser(app.getCredentialsEntered().getUserName(), app.getBankAccountDAO(), app.getUserNameBankAccountIDPairDAO());
                        break;
                    case "5":
                        BankAccountServices.printAccountToScreen(Integer.parseInt(prompt(app.getScan(), "AccountID:")[0]), app.getBankAccountDAO());
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
