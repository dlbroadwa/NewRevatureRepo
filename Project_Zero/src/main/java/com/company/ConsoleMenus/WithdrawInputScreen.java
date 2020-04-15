package com.company.ConsoleMenus;

import com.company.Application.ATMApplication;
import com.company.Banking.Transaction;

public class WithdrawInputScreen extends InputScreen {

    @Override
    public Screen run(ATMApplication app) {
        System.out.println("\nwithdraw transactions screen is still in the works\n");
        double previousBalance = 2.0;
        double transactionAmount = -0.8;
        String description = "withdrawal";
        Transaction transaction = new Transaction(0, previousBalance, transactionAmount, description);
        transaction.printToScreen();
        System.out.println(transaction.toString());

        return new TransactionInputScreen();
    }
}
