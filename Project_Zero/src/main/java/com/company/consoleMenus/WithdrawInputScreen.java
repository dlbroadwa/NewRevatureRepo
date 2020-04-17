package com.company.consoleMenus;

import com.company.application.ATMApplication;
import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.services.BankAccountServices;

import java.sql.Timestamp;

public class WithdrawInputScreen extends InputScreen {

    @Override
    public Screen run(ATMApplication app) {

        return new TransactionInputScreen();
    }
}
