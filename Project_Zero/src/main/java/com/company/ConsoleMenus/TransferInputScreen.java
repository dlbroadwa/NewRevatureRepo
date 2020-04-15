package com.company.ConsoleMenus;

import com.company.Application.ATMApplication;

public class TransferInputScreen implements Screen {

    @Override
    public Screen run(ATMApplication app) {
        System.out.println("\ntransfer transactions screen is still in the works\n");
        return new TransactionInputScreen();
    }
}
