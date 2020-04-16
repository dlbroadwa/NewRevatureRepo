package com.company.consoleMenus;

import com.company.application.ATMApplication;

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
            if (app.getLoginAccount().isAdmin()) {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Deposit\n\t2) Withdraw\n\t3) Transfer\n\t4) Create Bank Account\n\t5) Delete Bank Account\n\t6) Create Login Account\n\t7) Delete Login Account\n\t8) Associate Bank Account and Login Account\n\t9) Logout")[0]) {
                    case "1":
                        return new DepositInputScreen();
                    case "2":
                        return new WithdrawInputScreen();
                    case "3":
                        return new TransferInputScreen();
                    case "9":
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
                        return new WithdrawInputScreen();
                    case "3":
                        return new TransferInputScreen();
                    case "4":

                        break;
                    case "5":
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
