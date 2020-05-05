package com.data;

import com.clients.TransactionService;
import com.model.Accounts;
import com.model.Transactions;
import com.utilities.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Class description: The class will record the user transactions in the database via the myschema.transaction_details table.
 * The class will not record the transaction time, but will be using database table trigger at insert time to insert timestamp automatically.
 */
public class TransactionsSQLRepo implements ITransaction<Transactions, Integer> {
    private ConnectionUtils connectionUtils;
    public TransactionsSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     * Unused method at the moment, however, it maybe used at future point for later project if needed.
     * @param account
     * @param id
     * @return
     */
    public Transactions findTransactionbyId(Accounts account, int id) {

        Connection conn = null;
        Transactions tmpTransaction = new Transactions();
        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.transaction_details td where id = " + id + " ;";
    //        System.out.println("Find all transactions by Id: " + sql);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                tmpTransaction.setTimestamp(rs.getString("transaction_date").toString());
                tmpTransaction.setAccount(account);
                tmpTransaction.setId(rs.getInt("id"));
                tmpTransaction.setTrans_amount(rs.getFloat("amount"));
                tmpTransaction.setTransaction_type(rs.getString("transaction_type"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return tmpTransaction;
    }

    /**
     * This method will query the database for all transactions by the account
     * @param account is the user account that will be used to query for account transactions.
     * @return List of all transactions by account
     */
    public List<Transactions> allTransactionByAccount(Accounts account) {
        Connection conn = null;
        List <Transactions> allTransactionbyAccount = new LinkedList<Transactions>();
        try {
            conn = connectionUtils.getConnection();
            Transactions tmpTransaction = new Transactions();

            String sql = "select * from myschema.transaction_details td where accountid = " + account.getAccount_id() +
                    " order by transaction_date desc;";
        //    System.out.println("Find all transactions by Account: " + sql);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                tmpTransaction.setTimestamp(rs.getString("transaction_date").toString());
                tmpTransaction.setAccount(account);
                tmpTransaction.setId(rs.getInt("id"));
                tmpTransaction.setTrans_amount(rs.getFloat("amount"));
                tmpTransaction.setTransaction_type(rs.getString("transaction_type"));

                allTransactionbyAccount.add(tmpTransaction);
                tmpTransaction = new Transactions();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return allTransactionbyAccount;
    }

    /**
     * Insert transaction based on the passed in parameters.
     * @param account the account which the transaction was invoked from
     * @param amount the transaction amount
     * @param type withdraw or deposit transaction
     */
    public void insertTransaction(Accounts account, float amount, String type) {

        Connection conn = null;
        float newBalance = account.getBalance() + amount;
        try {
            conn = connectionUtils.getConnection();

            String sql2 = "insert into myschema.transaction_details (transaction_type , accountid , amount ) values ('" +
                    type + "'," + account.getAccount_id() + ", " + amount + ");";

            Statement statement = conn.createStatement();
            statement.executeUpdate(sql2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
