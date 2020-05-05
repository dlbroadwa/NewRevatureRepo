package com.clients;

import com.data.ITransaction;
import com.model.Accounts;
import com.model.Transactions;

import java.util.List;

public class TransactionService {
    private ITransaction <Transactions, Integer> transaction;

    public TransactionService (ITransaction <Transactions, Integer> trans){
        this.transaction = trans;
    }


    public Transactions findTransactionbyId(Accounts account, int id) {

        return this.transaction.findTransactionbyId(account, id);
    }

    public List<Transactions> allTransactionByAccount(Accounts account) {

        return this.transaction.allTransactionByAccount(account);
    }

    public void insertTransaction(Accounts account, float amount, String type) {
        if (type.equals("withdraw")){
            amount = amount * (-1);
        }

        this.transaction.insertTransaction(account, amount, type);
    }
}
