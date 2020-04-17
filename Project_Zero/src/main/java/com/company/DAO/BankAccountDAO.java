package com.company.DAO;

import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.databaseUtils.PostgresqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BankAccountDAO implements DAO<Account, Integer> {

    private PostgresqlConnection postgresqlConnection = null;
    public BankAccountDAO(PostgresqlConnection postgresqlConnection) {
       this.postgresqlConnection = postgresqlConnection;
    }

    @Override
    public Integer save(Account obj) {
        return null;
    }

    @Override
    public ArrayList<Account> retrieveAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            String bankAccountsSQLQuery = "SELECT * FROM " + schema + ".bankaccounts";
            Statement bankAccountStatement = connection.createStatement();

            ResultSet bankAccountsQueryResults = bankAccountStatement.executeQuery(bankAccountsSQLQuery);

            while (bankAccountsQueryResults.next()) {
                int accountID = bankAccountsQueryResults.getInt("accountid");
                double currentBalance = bankAccountsQueryResults.getDouble("currentbalance");
                ArrayList<Transaction> balanceHistory = new ArrayList<>();

                // retrieve balance history for account

                String transactionsSQLQuery = "SELECT transactionid, previousbalance, transactionamount, description, timeoftransaction FROM " + schema + ".transactions WHERE accountid = " + accountID + "ORDER BY timeoftransaction";
                Statement transactionStatement = connection.createStatement();
                ResultSet transactionsQueryResults = transactionStatement.executeQuery(transactionsSQLQuery);

                while (transactionsQueryResults.next()) {
                    balanceHistory.add(new Transaction(
                                    transactionsQueryResults.getInt("transactionid"),
                                    transactionsQueryResults.getDouble("previousbalance"),
                                    transactionsQueryResults.getDouble("transactionamount"),
                                    transactionsQueryResults.getString("description"),
                                    transactionsQueryResults.getTimestamp("timeoftransaction")
                    ));
                }
                accounts.add( new Account(
                        accountID,
                        currentBalance,
                        balanceHistory
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return accounts;
    }

    @Override
    public Account[] retrieveByID(Integer accountID) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String schema = postgresqlConnection.getDefaultSchema();
            String bankAccountsSQLQuery = "SELECT * FROM " + schema + ".bankaccounts WHERE accountID = " + accountID;
            Statement bankAccountStatement = connection.createStatement();

            ResultSet bankAccountsQueryResults = bankAccountStatement.executeQuery(bankAccountsSQLQuery);
            if (bankAccountsQueryResults.next()) {
                bankAccountsQueryResults.getInt("accountid");
                double currentBalance = bankAccountsQueryResults.getDouble("currentbalance");
                ArrayList<Transaction> balanceHistory = new ArrayList<>();

                // retrieve balance history for account

                String transactionsSQLQuery = "SELECT transactionid, previousbalance, transactionamount, description, timeoftransaction FROM " + schema + ".transactions WHERE accountid = " + accountID + "ORDER BY timeoftransaction";
                Statement transactionStatement = connection.createStatement();
                ResultSet transactionsQueryResults = transactionStatement.executeQuery(transactionsSQLQuery);

                while (transactionsQueryResults.next()) {
                    balanceHistory.add(new Transaction(
                            transactionsQueryResults.getInt("transactionid"),
                            transactionsQueryResults.getDouble("previousbalance"),
                            transactionsQueryResults.getDouble("transactionamount"),
                            transactionsQueryResults.getString("description"),
                            transactionsQueryResults.getTimestamp("timeoftransaction")
                    ));
                }

                return new Account[]{new Account(
                        accountID,
                        currentBalance,
                        balanceHistory
                )};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Account[]{};
    }

    @Override
    public void delete(Account obj) {

    }

    @Override
    public void update(Account newObj) {

    }
}
