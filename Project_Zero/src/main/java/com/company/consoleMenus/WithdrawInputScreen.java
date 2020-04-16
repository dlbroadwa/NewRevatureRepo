package com.company.consoleMenus;

import com.company.application.ATMApplication;
import com.company.banking.Transaction;

import java.sql.Timestamp;

public class WithdrawInputScreen extends InputScreen {

    @Override
    public Screen run(ATMApplication app) {
        System.out.println("\nwithdraw transactions screen is still in the works\n");
        double previousBalance = 2.0;
        double transactionAmount = -0.8;
        String description = "withdrawal";
        Transaction transaction = new Transaction(0, previousBalance, transactionAmount, description, new Timestamp(System.currentTimeMillis()));
        transaction.printToScreen();
        System.out.println(transaction.toString());



        return new TransactionInputScreen();
    }
}
