package ConsoleMenus;

import Application.ATMApplication;

public class DepositInputScreen extends InputScreen {
    @Override
    public Screen run(ATMApplication app) {
        System.out.println("\ndeposit transactions screen is still in the works\n");
        return new TransactionInputScreen();
    }
}
