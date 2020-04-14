package com.revature;

import java.io.*;
import java.util.LinkedList;

public class BankAccountFileOperations extends BankAccountDAO{

    public final static String  accountFileLocation = "input\\AccountList.csv";

    @Override
    void printAccountDetails(String accountId) {
        LinkedList<Account> allAccount = getAllAccounts();

        for (Account tmpAccount : allAccount){
            if (tmpAccount.getAccount_id().equals(accountId)){
                System.out.println(tmpAccount.getAccount_id() + "," + tmpAccount.getAccountHolderId() + "," + tmpAccount.getAccountType() + "," + String.format("%.2f", tmpAccount.getBalance()));
                break;
            }
        }
    }


    @Override
    void updateAccountBalance(String accountId, double newBalance) {
        LinkedList<Account> allAccounts = getAllAccounts();

        for (Account tmpAccount : allAccounts){
            if (tmpAccount.getAccount_id().equals(accountId)){
                tmpAccount.setBalance(newBalance);

            }
        }

        writeAccountUpdateToFile(allAccounts);
    }

    @Override
    void insertAccount(Account account) {
        LinkedList<Account> allAccounts = getAllAccounts();

        for (Account tmpAccount : allAccounts){
            if (tmpAccount.getAccount_id().equals(account.getAccount_id())){
                System.out.println("Account " + account.getAccount_id() + " already exists!");
                return;
            }
        }
        allAccounts.add(account);
        writeAccountUpdateToFile (allAccounts);
    }

    private void writeAccountUpdateToFile(LinkedList<Account> tmpAllAccounts) {
        FileWriter fw = null;
        boolean firstLine = true;
        try {
            fw = new FileWriter(accountFileLocation, false);
            BufferedWriter bWriter = new BufferedWriter(fw);

            for (Account tmpAccount : tmpAllAccounts){
                if (!firstLine) {
                    bWriter.write("\r\n");
                }
                else{
                    firstLine = false;
                }
                String line = tmpAccount.getAccount_id() + "," + tmpAccount.getAccountHolderId() + "," + tmpAccount.getAccountType() + "," + String.format("%.2f", tmpAccount.getBalance());
                bWriter.write(line);
            }

            bWriter.flush();
            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private LinkedList<Account> getAllAccounts() {
        LinkedList<Account> tmpAllAccounts = new LinkedList<Account>();
        FileReader reader = null;
        BufferedReader bReader = null;

        try {
            reader = new FileReader(accountFileLocation);
            bReader = new BufferedReader(reader);
            String line = "";
            while ((line = bReader.readLine()) != null){

                String[] str = line.split(",");
                String accountId, userId, accountType;

                double balance;

                accountId = str[0];
                userId = str[1];
                accountType = str[2];
                balance = Double.valueOf(str[3]);
                Account tmpAccount = new Account (accountId, userId, accountType, balance);

                tmpAllAccounts.add(tmpAccount);
            }
            bReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpAllAccounts;
    }

    public static void main (String ars[]){
        BankAccountFileOperations test = new BankAccountFileOperations();
        Account tmpAccount = new Account("acct_id_52300089", "id_523656789", "savings", 223300);
        test.printAccountDetails("acct_id_123456789");

        System.out.println("Updating balance for account acct_id_123456789");
        test.updateAccountBalance("acct_id_123456789", 1.53);

        test.printAccountDetails("acct_id_123456789");

        System.out.println("\nInserting account: acct_id_52300089");
        test.insertAccount(tmpAccount);
        test.printAccountDetails("acct_id_52300089");

    }//end main
}
