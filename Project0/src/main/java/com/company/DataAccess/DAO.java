package com.company.DataAccess;

import com.company.banking.BankCustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAO implements DAOI<BankCustomer, Integer> {

    private ConnectionUtils connectionUtils;
    private Connection connection = null;
    public DAO(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     * Searches database for user accounts based on the user ID and returns the user account
     * @param id Specified User ID
     * @return BankCustomer with accounts found from database
     */
    @Override
    public BankCustomer findAccountById(Integer id) {
        BankCustomer tempCustomer = new BankCustomer();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".accounts where id = '" + id + "';";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int foundId = rs.getInt("id");
                double checkingsAmount = rs.getDouble("checkings");
                double savingsAmount = rs.getDouble("savings");

                tempCustomer.setCheckings(checkingsAmount);
                tempCustomer.setSavings(savingsAmount);
                tempCustomer.setId(foundId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return tempCustomer;
    }

    /**
     * Finds the user in the database given the username and password
     * @param userName Given username
     * @param passWord Given password
     * @return The found user
     */
    @Override
    public BankCustomer findByUserNamePassword(String userName, String passWord) {
        BankCustomer tempCustomer = new BankCustomer();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select * from " + schemaName + ".customers where username = '" + userName + "' and \"password\" = '" + passWord + "';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                tempCustomer.setFirstName(firstName);
                tempCustomer.setLastName(lastName);
                tempCustomer.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return tempCustomer;
    }

    /**
     * Function that updates one column from the account table
     * @param id User ID
     * @param amount amount the account will be updated to
     * @param columnName account name
     * @return true if update was successful or false if it wasn't
     */
    @Override
    public boolean updateOneAccount(Integer id, double amount, String columnName) {
        boolean wasUpdated = false;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Update " + schemaName + ".accounts SET " + columnName + " = " + amount + " where id = '" + id + "';";
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(sql);
            if(rowsUpdated == 1)
            {
                wasUpdated = true;
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                }
            }
        }
        return wasUpdated;
    }

    /**
     * Updates both of the user's accounts with the new amount of money
     * @param id user id
     * @param checkingAmount new checking amount
     * @param savingAmount new saving amount
     * @return true if account was updated false otherwise
     */
    @Override
    public boolean updateAccounts(Integer id, double checkingAmount, double savingAmount) {
        boolean wasUpdated = false;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Update " + schemaName + ".accounts SET checkings= " + checkingAmount + ", savings= " + savingAmount + " where id = '" + id + "';";
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(sql);
            if(rowsUpdated == 1)
            {
                wasUpdated = true;
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                }
            }
        }
        return wasUpdated;
    }

    /**
     * Inserts bank transaction into transaction table
     * @param id User Id
     * @param date Date of transaction
     * @param transaction Actual transaction
     */
    public boolean addTransaction(Integer id, LocalDate date, String transaction) {
        boolean transactionWorked = false;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Insert into " + schemaName + ".transactions (id, transaction, date) values (" + id + ",'" + transaction + "', '" + date + "');";
            Statement statement = connection.createStatement();
            int check = statement.executeUpdate(sql);
            if(check == 1)
            {
                transactionWorked = true;
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                }
            }
        }
        return transactionWorked;
    }

    /**
     * Finds all user transactions given the user ID
     * @param id USer Id
     * @return list of transactions made by the user
     */
    @Override
    public List<String> findAllTransactionsById(Integer id) {
        List<String> transactions = new ArrayList<>();
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".transactions where id=" + id + ";";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String action = rs.getString("transaction");
                Date date = rs.getDate("date");
                String singleTransaction = date + " " + action;
                // Add date and transaction as one string
                transactions.add(singleTransaction);
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                }
            }
        }
        return transactions;
    }

    /**
     * Delete all transcations made by the user
     * @param id user id
     */
    @Override
    public void deleteAllTransactionsById(Integer id) {
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Delete from " + schemaName + ".transactions where id=" + id + ";";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //throwables.printStackTrace();
                }
            }
        }
    }

}
