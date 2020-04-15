package ConsoleMenus;

import Application.ATMApplication;

public class TransactionInputScreen extends InputScreen {
    TransactionInputScreen() {
        super();
    }

    @Override
    public Screen run(ATMApplication app) {
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
                }
            } else {
                switch (prompt(app.getScan(), "Select one of the following options by typing it's number.\n\t1) Deposit\n\t2) Withdraw\n\t3) Transfer\n\t4) Logout")[0]) {
                    case "1":
                        return new DepositInputScreen();
                    case "2":
                        return new WithdrawInputScreen();
                    case "3":
                        return new TransferInputScreen();
                    case "4":
                        app.setCredentialsEntered(null);
                        return new LoginScreen();
                }
            }
        }
    }
}
