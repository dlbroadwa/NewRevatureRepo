package ConsoleMenus;

import Application.ATMApplication;

public class WithdrawInputScreen extends InputScreen {

    @Override
    public Screen run(ATMApplication app) {
        System.out.println("\nwithdraw transactions screen is still in the works\n");
        return new TransactionInputScreen();
    }
}
