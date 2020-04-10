package ConsoleMenus;

import Application.ATMApplication;

public class TransactionInputScreen extends InputScreen {
    TransactionInputScreen() {
        super("Select one of the following options by typing it's number.\n\t1) Deposit\n\t2) Withdraw\n\t3) Transfer\n\t4) Logout");
    }

    @Override
    public Screen run(ATMApplication app) {
        while(true) {
            switch (prompt(app.getScan())[0]) {
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
