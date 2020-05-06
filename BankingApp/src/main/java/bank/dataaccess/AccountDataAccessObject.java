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


    /**
     * Inset new BankAccount into the database given the parameters
     * @param currentBankAccount new Bank account information
     * @return a number if it passed or failed
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
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

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(BankAccount obj) {

        Connection connection = null;
        String sql = "DELETE FROM " + connectionUtils.getDefaultSchema() + "WHERE accountid = ?";
        try {
            connection = connectionUtils.getConnection();
            PreparedStatement  statement = connection.prepareStatement(sql);
            statement.setInt(1, obj.getAccountID());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param currentAccount
     * @return
     */
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

    /**
     *
     * @param accountTransferFrom
     * @param accountTransferTo
     * @return
     */
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
