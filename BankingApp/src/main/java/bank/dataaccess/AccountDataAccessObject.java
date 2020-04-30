package bank.dataaccess;

import bank.model.BankAccount;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.util.ArrayList;

public class AccountDataAccessObject implements DAO<BankAccount, ID>{
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
    public ID save(BankAccount obj) {
        return null;
    }

    @Override
    public ArrayList<BankAccount> retrieveAll() {
        return null;
    }

    @Override
    public BankAccount[] retrieveByID(ID id) {
        return new BankAccount[0];
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

    public boolean transfer(String userName, int userAccountID, double amount, int transferredAccountID) {
        return false;
    }


}
