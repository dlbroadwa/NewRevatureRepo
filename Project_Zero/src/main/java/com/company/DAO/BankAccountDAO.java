package com.company.DAO;

import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.databaseUtils.PostgresqlConnection;
import com.sun.corba.se.spi.monitoring.StatisticMonitoredAttribute;

import java.sql.*;
import java.util.ArrayList;

public class BankAccountDAO implements DAO<Account, Integer> {

    private PostgresqlConnection postgresqlConnection = null;
    public BankAccountDAO(PostgresqlConnection postgresqlConnection) {
       this.postgresqlConnection = postgresqlConnection;
    }

    @Override
    public Integer save(Account obj) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".bankaccounts (currentbalance) VALUES (?)";
            PreparedStatement bankAccountStatement = connection.prepareStatement(bankAccountsSQLQuery);
            bankAccountStatement.setDouble(1, obj.getCurrentBalance());
            bankAccountStatement.executeUpdate(bankAccountsSQLQuery);

            // get accountID
            String bankAccountIDSQLQuery = "SELECT MAX(accountid) AS maxaccountid FROM " + postgresqlConnection.getDefaultSchema() + ".bankaccounts";
            Statement idQuery = connection.createStatement();
            ResultSet rs = idQuery.executeQuery(bankAccountIDSQLQuery);
            rs.next();
            return rs.getInt("maxaccountid");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public ArrayList<Account> retrieveAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".bankaccounts";
            Statement bankAccountStatement = connection.createStatement();

            ResultSet bankAccountsQueryResults = bankAccountStatement.executeQuery(bankAccountsSQLQuery);

            while (bankAccountsQueryResults.next()) {
                int accountID = bankAccountsQueryResults.getInt("accountid");
                double currentBalance = bankAccountsQueryResults.getDouble("currentbalance");
                ArrayList<Transaction> balanceHistory = new ArrayList<>();

                // retrieve balance history for account

                String transactionsSQLQuery = "SELECT transactionid, previousbalance, transactionamount, description, timeoftransaction FROM " + postgresqlConnection.getDefaultSchema() + ".transactions WHERE accountid = " + accountID + "ORDER BY timeoftransaction";
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
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".bankaccounts WHERE accountID = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(bankAccountsSQLQuery);
            bankAccountStatement.setInt(1, obj.getAccountID());


            String transactionQuery = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".transactions WHERE accountID = ?";
            PreparedStatement transactionStatement = connection.prepareStatement(bankAccountsSQLQuery);
            transactionStatement.setInt(1, obj.getAccountID());

            bankAccountStatement.executeUpdate();
            transactionStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Account newObj) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();

            // update the bankaccounts table
            String updateBankAccountSQL = "UPDATE " + postgresqlConnection.getDefaultSchema() + ".bankaccounts SET currentbalance = ? WHERE accountid = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(updateBankAccountSQL);
            bankAccountStatement.setDouble(1, newObj.getCurrentBalance());
            bankAccountStatement.setInt(2, newObj.getAccountID());
            bankAccountStatement.executeUpdate();

            // update the transaction table
            String insertTransaction = "INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".transactions (accountid, previousbalance, transactionamount, description, timeoftransaction) VALUES ";
            ArrayList<String> values = new ArrayList<>();
            for (int i = newObj.getBalanceHistory().size()-1; i > -1; i--) {
                if (newObj.getBalanceHistory().get(i).getTransactionID() != Transaction.NEW_TRANSACTIONID) break;
                values.add(("('" + newObj.getAccountID() + "', '" + newObj.getBalanceHistory().get(i).getPreviousBalance() + "', '" + newObj.getBalanceHistory().get(i).getTransactionAmount() + "', '" + newObj.getBalanceHistory().get(i).getDescription() + "', '" + newObj.getBalanceHistory().get(i).getTimeOfTransaction().toString().substring(0, newObj.getBalanceHistory().get(i).getTimeOfTransaction().toString().indexOf('.')) + "')"));
            }

            for (int i = (values.size()-1); i > -1; i--) {
                if (i == values.size()-1) insertTransaction += values.get(i);
                else insertTransaction += ", " + values.get(i);
            }

            PreparedStatement transactionStatement = connection.prepareStatement(insertTransaction);
            System.out.println(insertTransaction);
            transactionStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
