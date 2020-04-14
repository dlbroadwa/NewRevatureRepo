package com.revature;

import sun.awt.image.ImageWatched;

import java.io.*;
import java.sql.Timestamp;
import java.util.LinkedList;

public class BankTransactionFileOperations extends BankTransactionDAO {
    public final static String  transactionsListFileLocation = "input\\TransactionsList.csv";

    @Override
    public void addTransaction(Transaction transaction) {
        LinkedList <String> allTransactions = getAllTransactions();
        String record = transaction.getTimestamp().toString() + "," + transaction.getTransactionId() + ","
                + transaction.getAccount().getAccount_id() + "," + transaction.getTypeOfTransaction() + ","
                + String.format("%.2f", transaction.getAmount());

        allTransactions.add(record);
        writeUpdateToFile(allTransactions);
        System.out.println("Transaction written: \n" + record );
    }

    @Override
    public String retrieveOneTransaction(String transactionId) {
        LinkedList <String> allTransactions = getAllTransactions();

        for (String oneTransaction : allTransactions){
            String [] columns = oneTransaction.split(",");
            if (columns[1].equals(transactionId)){

                return oneTransaction;
            }
        }

        return null;
    }

    @Override
    public LinkedList<String> retrieveTransactionByAccount(Account account) {
        String accountId = account.getAccount_id();
     //   System.out.println("accountId is " + accountId);
        LinkedList <String> transactionsByAccount = new LinkedList<String>();
        LinkedList <String> allTransactions = getAllTransactions();

        for (String oneTransaction : allTransactions){
            String [] columns = oneTransaction.split(",");
            if (accountId.equals(columns[2])){
                transactionsByAccount.add(oneTransaction);
            }
        }

        return transactionsByAccount;
    }

    private void writeUpdateToFile(LinkedList<String> allTransactions) {

        FileWriter fw = null;
        boolean firstLine = true;
        try {
            fw = new FileWriter(transactionsListFileLocation, false);
            BufferedWriter bWriter = new BufferedWriter(fw);
            for (String s : allTransactions){
                if (!firstLine) {
                    bWriter.write("\r\n");
                }
                else{
                    firstLine = false;
                }

                bWriter.write(s);


            }
            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static LinkedList<String> getAllTransactions() {
        FileReader reader = null;
        BufferedReader bReader = null;
        String line = "";
        LinkedList <String> tmpList = new LinkedList<String>();
        try {
            reader = new FileReader(transactionsListFileLocation);
            bReader = new BufferedReader(reader);
            while ((line = bReader.readLine()) != null){

                tmpList.add(line);

            }//end while

            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpList;
    }//end getAllTransactions()



    public static void main (String args[]){

        LinkedList<String> tmp = new LinkedList<String>();
        tmp = BankTransactionFileOperations.getAllTransactions();

        User tmpUser = new User ("id_123414480", "Bob" , "Jack", "2028882222", "6767" , true );
        Account tmpAccount = new Account ("acct_id_110056789", "id_123444480", "checking", 100.05);
        Timestamp tmpDate = new Timestamp (System.currentTimeMillis());
        Transaction tmpTransaction = new Transaction("trans_id_031001", tmpAccount,"withdraw", tmpDate,tmpUser, 200.05);

        BankTransactionFileOperations bankTransactionFileOperations = new BankTransactionFileOperations();

        bankTransactionFileOperations.addTransaction(tmpTransaction);

        System.out.println("Transaction retrieval: \n");
        System.out.println(bankTransactionFileOperations.retrieveOneTransaction("trans_id_000601"));

        Account tmpAccount2 = new Account("acct_id_100056789", "id_523656789", "savings", 223300);
        System.out.println("Transaction by Account retrieval: \n");
        LinkedList<String> trans = bankTransactionFileOperations.retrieveTransactionByAccount(tmpAccount2);

        for (String tempString : trans){
            System.out.println(tempString);
        }


    }

}
