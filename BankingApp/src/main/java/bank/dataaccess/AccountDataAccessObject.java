package bank.dataaccess;

import bank.model.BankAccount;
import bank.model.UserNameBankAccountIDPair;

import java.sql.*;
import java.util.ArrayList;

public class AccountDataAccessObject implements DAO<BankAccount, Integer>{
    private ConnectionUtils connectionUtils;
    private Connection connection = null;
    public AccountDataAccessObject(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

//    public void findAllAccounts() {
//        try {
//            connection = connectionUtils.getConnection();
//            String schemaName = connectionUtils.getDefaultSchema();
//            String sql = "Select * from " + schemaName + ".users;";
//            Statement statement = connection.createStatement();
//
//            ResultSet rs = statement.executeQuery(sql);
//            while(rs.next()) {
//                System.out.println(rs.getString("firstname"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            if(connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public Integer save(BankAccount currentBankAccount) {
        boolean wasSuccessful = true;
        Connection connection = null;
        String tableName = "bankaccounts";
        try {
            connection = connectionUtils.getConnection();
            // Insert the bankaccounts table
            String saveStatement = "INSERT INTO " + connectionUtils.getDefaultSchema() + "." + tableName + " (accountid, currentbalance) VALUES (default,?)";
            PreparedStatement bankAccountStatement = connection.prepareStatement(saveStatement);
            bankAccountStatement.setDouble(1, currentBankAccount.getCurrentBalance());
            bankAccountStatement.executeUpdate();
            return currentBankAccount.getAccountID();
        } catch (SQLException e) {
            wasSuccessful = false;
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                wasSuccessful = false;
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<BankAccount> retrieveAll() {

        Connection connection = null;
        String tableName = "bankaccounts";
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String sql = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + tableName;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                bankAccounts.add(new BankAccount(resultSet.getInt("accountID"), resultSet.getDouble("currentbalance")));
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
        return bankAccounts;
    }

    @Override
    public BankAccount[] retrieveByID(Integer id) {

        Connection connection = null;
        String tableName = "bankaccounts";
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String sql = "SELECT * FROM " + connectionUtils.getDefaultSchema() + "." + tableName + " WHERE accountid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                bankAccounts.add(new BankAccount(resultSet.getInt("accountID"), resultSet.getDouble("currentbalance")));
            }

            return bankAccounts.toArray(new BankAccount[]{});

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new BankAccount[]{};
    }

    @Override
    public boolean delete(BankAccount obj) {
        return false;
    }

    @Override
    public boolean update(BankAccount currentAccount) {
        boolean wasSuccessful = true;
        Connection connection = null;
        try {
            connection = connectionUtils.getConnection();

            // update the bankaccounts table
            String updateBankAccountSQL = "UPDATE " + connectionUtils.getDefaultSchema() + ".bankaccounts SET currentbalance = ? WHERE accountid = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(updateBankAccountSQL);
            bankAccountStatement.setDouble(1, currentAccount.getCurrentBalance());
            bankAccountStatement.setInt(2, currentAccount.getAccountID());
            bankAccountStatement.executeUpdate();
        } catch (SQLException e) {
            wasSuccessful = false;
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                wasSuccessful = false;
                e.printStackTrace();
            }
        }
        return wasSuccessful;
    }

    public boolean transfer(BankAccount accountTransferFrom, BankAccount accountTransferTo) {

        boolean wasSuccessful = true;
        Connection connection = null;
        try {
            connection = connectionUtils.getConnection();
            connection.setAutoCommit(false);

            Savepoint savepoint = connection.setSavepoint();

            // update the bankaccounts table
            String updateBankAccountSQL = "UPDATE " + connectionUtils.getDefaultSchema() + ".bankaccounts SET currentbalance = ? WHERE accountid = ?";
            PreparedStatement bankAccountStatement = connection.prepareStatement(updateBankAccountSQL);
            bankAccountStatement.setDouble(1, accountTransferFrom.getCurrentBalance());
            bankAccountStatement.setInt(2, accountTransferFrom.getAccountID());
            bankAccountStatement.executeUpdate();

            bankAccountStatement.setDouble(1, accountTransferTo.getCurrentBalance());
            bankAccountStatement.setInt(2, accountTransferTo.getAccountID());
            bankAccountStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            wasSuccessful = false;
            try
            {
                connection.rollback();
            } catch(Exception ex)
            {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                wasSuccessful = false;
                e.printStackTrace();
            }
        }
        return wasSuccessful;
    }


}
