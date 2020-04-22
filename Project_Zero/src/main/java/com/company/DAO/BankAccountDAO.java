package com.company.DAO;

import com.company.banking.Account;
import com.company.banking.Transaction;
import com.company.databaseUtils.PostgresqlConnection;

import java.sql.*;
import java.util.ArrayList;

/***
 * The BankAccountDAO class implements the DAO interface to provide direct interaction with the database for information relating to the bank accounts and the transactions performed on them (balance history).
 *
 * @author Shawyn Kane
 */
public class BankAccountDAO implements DAO<Account, Integer> {

    private PostgresqlConnection postgresqlConnection = null;
    public BankAccountDAO(PostgresqlConnection postgresqlConnection) {
       this.postgresqlConnection = postgresqlConnection;
    }

    /***
     *
     * The save(...) method inserts the passed account information into the bankaccounts table in the database to create a new bank account.
     * It does not check to see if an account matches the information provided before creating a new account in the database.
     *
     * @author Shawyn Kane
     * @param account
     * @return
     */
    @Override
    public Integer save(Account account) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".bankaccounts (currentbalance) VALUES (?)";
            PreparedStatement bankAccountStatement = connection.prepareStatement(bankAccountsSQLQuery);
            bankAccountStatement.setDouble(1, account.getCurrentBalance());
            bankAccountStatement.executeUpdate();

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
        return OPERATION_FAILED;
    }

    /***
     *
     * The retrieveAll() method retrieves all the accounts and their associated transactions (balance history) from the database. This is not actually used, even though it has been implemented.
     *
     * This was the first method I implemented to get the hang of connecting to the database and retrieving information.
     *
     * @author Shawyn Kane
     * @return
     */
    @Deprecated
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

    /***
     *
     * The retrieveByID(...) method retrieves the account information (accountID, current balance, and balance history) from the database for the provided accountID.
     * If there is no account with the provided accountID it will return an Account array (Account[]) of length zero.
     * The method will at most return one Account in an Account array (Account[]) (or in other words return an Account array (Account[]) of length one).
     * This is due to the fact that the accountID is the primary key of the bankaccounts table in the database.
     *
     * @author Shawyn Kane
     * @param accountID
     * @return
     */
    @Override
    public Account[] retrieveByID(Integer accountID) {
        Connection connection = null;

        try {
            // Setup connection and query
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "SELECT * FROM " + postgresqlConnection.getDefaultSchema() + ".bankaccounts WHERE accountID = " + accountID;
            Statement bankAccountStatement = connection.createStatement();

            // query database
            ResultSet bankAccountsQueryResults = bankAccountStatement.executeQuery(bankAccountsSQLQuery);

            // if the result set is not empty
            if (bankAccountsQueryResults.next()) {

                // retrieve account id and current balance
                bankAccountsQueryResults.getInt("accountid");
                double currentBalance = bankAccountsQueryResults.getDouble("currentbalance");

                // retrieve balance history for account
                ArrayList<Transaction> balanceHistory = new ArrayList<>();

                // create query for retrieving "bank account transaction" data from database for the balance history
                String transactionsSQLQuery = "SELECT transactionid, previousbalance, transactionamount, description, timeoftransaction FROM " + postgresqlConnection.getDefaultSchema() + ".transactions WHERE accountid = " + accountID + "ORDER BY timeoftransaction";
                Statement transactionStatement = connection.createStatement();

                // retrieve result set of the "bank account transaction" query
                ResultSet transactionsQueryResults = transactionStatement.executeQuery(transactionsSQLQuery);

                // iterate the result set and add the information to the balanceHistory ArrayList<Transaction>
                while (transactionsQueryResults.next()) {
                    balanceHistory.add(new Transaction(
                            transactionsQueryResults.getInt("transactionid"),
                            transactionsQueryResults.getDouble("previousbalance"),
                            transactionsQueryResults.getDouble("transactionamount"),
                            transactionsQueryResults.getString("description"),
                            transactionsQueryResults.getTimestamp("timeoftransaction")
                    ));
                } // while

                return new Account[]{new Account(
                        accountID,
                        currentBalance,
                        balanceHistory
                )}; // return new Account[]{...
            } // if the result set is not empty
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

    /***
     * This delete method deletes the transactions associated with the bank account passed to the method from transactions table and then proceeds to delete the bank account from the bankaccounts table.
     * It does not check to see if the account exists before attempting to delete.
     *
     * @author Shawyn Kane
     * @param account
     */
    @Override
    public void delete(Account account) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();
            String bankAccountsSQLQuery = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".bankaccounts WHERE accountID = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(bankAccountsSQLQuery);
            bankAccountStatement.setInt(1, account.getAccountID());


            String transactionQuery = "DELETE FROM " + postgresqlConnection.getDefaultSchema() + ".transactions WHERE accountID = ?";
            PreparedStatement transactionStatement = connection.prepareStatement(transactionQuery);
            transactionStatement.setInt(1, account.getAccountID());

            transactionStatement.executeUpdate();
            bankAccountStatement.executeUpdate();

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

    /**
     *
     * This method updates the account and transaction information associated with the account passed to the method.
     * It does not check to see if the account exists before attempting to update.
     *
     * @author Shawyn Kane
     * @param account
     */
    @Override
    public void update(Account account) {
        Connection connection = null;

        try {
            connection = postgresqlConnection.getConnection();

            // update the bankaccounts table
            String updateBankAccountSQL = "UPDATE " + postgresqlConnection.getDefaultSchema() + ".bankaccounts SET currentbalance = ? WHERE accountid = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(updateBankAccountSQL);
            bankAccountStatement.setDouble(1, account.getCurrentBalance());
            bankAccountStatement.setInt(2, account.getAccountID());
            bankAccountStatement.executeUpdate();

            // update the transaction table
            String insertTransaction = "INSERT INTO " + postgresqlConnection.getDefaultSchema() + ".transactions (accountid, previousbalance, transactionamount, description, timeoftransaction) VALUES ";
            ArrayList<String> values = new ArrayList<>();
            for (int i = account.getBalanceHistory().size()-1; i > -1; i--) {
                if (account.getBalanceHistory().get(i).getTransactionID() != Transaction.NEW_TRANSACTIONID) break;
                values.add(("('" + account.getAccountID() + "', '" + account.getBalanceHistory().get(i).getPreviousBalance() + "', '" + account.getBalanceHistory().get(i).getTransactionAmount() + "', '" + account.getBalanceHistory().get(i).getDescription() + "', '" + account.getBalanceHistory().get(i).getTimeOfTransaction().toString().substring(0, account.getBalanceHistory().get(i).getTimeOfTransaction().toString().indexOf('.')) + "')"));
            }

            for (int i = (values.size()-1); i > -1; i--) {
                if (i == values.size()-1) insertTransaction += values.get(i);
                else insertTransaction += ", " + values.get(i);
            }

            PreparedStatement transactionStatement = connection.prepareStatement(insertTransaction);
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
