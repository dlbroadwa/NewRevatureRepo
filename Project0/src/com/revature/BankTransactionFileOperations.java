package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;

public class BankTransactionFileOperations extends BankTransactionDAO {
    public final static String  transactionsListFileLocation = "input\\TransactionsList.csv";

    @Override
    public void addTransaction(Transaction transaction) {
        LinkedList <Transaction> allTransactions = getAllTransactions();
    }

    private LinkedList<Transaction> getAllTransactions() {
        FileReader reader = null;
        BufferedReader bReader = null;
        String line = "";
        try {
            reader = new FileReader(transactionsListFileLocation);
            bReader = new BufferedReader(reader);
            while ((line = bReader.readLine()) != null){
                String[] str = line.split(",");
                String tmpTransId = str [0];
                Timestamp timestamp = Timestamp.valueOf(str[1]);

            }//end while

            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
