package com.company.DAO;

import com.company.Banking.Account;
import com.company.Banking.Transaction;
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
            Statement statement = connection.createStatement();

            ResultSet bankAccountsQueryResults = statement.executeQuery(bankAccountsSQLQuery);

            while (bankAccountsQueryResults.next()) {
                int accountID = bankAccountsQueryResults.getInt("accountid");
                double currentBalance = bankAccountsQueryResults.getDouble("currentbalance");
                ArrayList<Transaction> balanceHistory = new ArrayList<>();

                // retrieve balance history for account

                String transactionsSQLQuery = "SELECT transactionid, previousbalance, transactionamount, description, timeoftransaction FROM " + schema + ".transactions WHERE accountid = " + accountID;
                ResultSet transactionsQueryResults = statement.executeQuery(transactionsSQLQuery);

                while (bankAccountsQueryResults.next()) {
                    balanceHistory.add(new Transaction(
                                    transactionsQueryResults.getInt("transactionid"),
                                    transactionsQueryResults.getDouble("previousbalance"),
                                    transactionsQueryResults.getDouble("transactionamount"),
                                    transactionsQueryResults.getString("description"),
                                    transactionsQueryResults.getTimestamp("timeoftransaction")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account retrieveByID(Integer integer) {
        return null;
    }

    @Override
    public void delete(Account obj) {

    }

    @Override
    public void update(Account newObj) {

    }
}
