package com.company.DataAccess;

import com.company.Banking.BankCustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreatorSQLRepository implements DAO<BankCustomer, Integer> {

    private ConnectionUtils connectionUtils;
    public CreatorSQLRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public BankCustomer findAccountById(Integer id) {

        Connection connection = null;
        BankCustomer tempCustomer = new BankCustomer();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".accounts where id = '" + id + "';";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                double checkingsAmount = rs.getDouble("checkings");
                double savingsAmount = rs.getDouble("savings");

                tempCustomer.setCheckings(checkingsAmount);
                tempCustomer.setSavings(savingsAmount);
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
     *
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public BankCustomer findByUserNamePassword(String userName, String passWord) {
        Connection connection = null;
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
     *
     * @param id
     * @param amount
     * @param columnName
     * @return
     */
    @Override
    public boolean updateOneAccount(Integer id, double amount, String columnName) {
        Connection connection = null;
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
        return wasUpdated;
    }

    /**
     *
     * @param id
     * @param checkingAmount
     * @param savingAmount
     * @return
     */
    @Override
    public boolean updateAccounts(Integer id, double checkingAmount, double savingAmount) {
        Connection connection = null;
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
        return wasUpdated;
    }

    /**
     *
     * @param id
     * @param date
     * @param transaction
     */
    public void addTransaction(Integer id, LocalDate date, String transaction) {
        Connection connection = null;
        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Insert into " + schemaName + ".transactions (id, transaction, date) values (" + id + ",'" + transaction + "', '" + date + "');";
            Statement statement = connection.createStatement();
            int check = statement.executeUpdate(sql);
            if(check == 1)
            {
                System.out.println("Transaction Worked");
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
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public List<String> findAllTransactionsById(Integer id) {
        Connection connection = null;
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
                transactions.add(singleTransaction);
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
        return transactions;
    }

}
