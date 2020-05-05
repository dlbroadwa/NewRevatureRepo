package com.data;


import com.clients.UsersService;
import com.model.Accounts;
import com.model.Users;
import com.utilities.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


/**
 * Class description: this class is the Controller class for the Account model.  It has methods that updates balance
 * via withdraw or deposit.  It will also find the user details via database.  Database connection is needed
 */
public class AccountsInSQLRepo implements IAccounts <Accounts, Integer > {

    private ConnectionUtils connectionUtils;

    //Constructor
    public AccountsInSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    /**
     * this method will update user balance, regardless if it is a deposit or it is a withdraw.
     * the amount to be added (depsosit) or substracted (withdraw) is handled by caller method
     * @param obj Account to be updated
     * @param amount amount to be updated
     */
    public void updateBalance(Accounts obj, float amount) {
        Connection conn = null;
        float newBalance = obj.getBalance() + amount;
        try {
            conn = connectionUtils.getConnection();
            String sql = "update myschema.accounts set balance = " + newBalance + " where id = " +
                    obj.getAccount_id() + ";";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
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

    /**
     *
     * @param email email is user ID in this bank system.  it will be used to find the user account detail in the database
     * @return the desired user account object.
     */
    public Accounts findByAccount(String email) {
        Connection conn = null;
        Accounts account = new Accounts();

        Users user;
        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);

        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.accounts ;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String tmpEmail = rs.getString("account_holder");
                if (tmpEmail.equals(email)){

                    account.setAccountType(rs.getString("account_type"));
                    account.setBalance(rs.getFloat("balance"));
                    account.setAccount_id(rs.getInt("id"));

                    user = usersService.findUserByEmail(tmpEmail);
                    account.setHolder(user);
                }
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

        return account;
    }


    public List<Accounts> findAll() {
        Connection conn = null;
        Accounts account = new Accounts();
        Accounts tmpAccount = new Accounts();
        Users user;
        IBankUsers<Users, String> IBankUsers = new BankUsersInSQLRepo(connectionUtils) ;
        UsersService usersService = new UsersService(IBankUsers);
        LinkedList<Accounts> allAccounts = new LinkedList<Accounts>();
        try {
            conn = connectionUtils.getConnection();
            String sql = "select * from myschema.accounts a order by id desc;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                tmpAccount.setAccountType(rs.getString("account_type"));
                tmpAccount.setBalance(rs.getFloat("balance"));
                String tmpEmail = rs.getString("account_holder");
                tmpAccount.setAccount_id(rs.getInt("id"));
                Users tmpUser = usersService.findUserByEmail(tmpEmail);
                tmpAccount.setHolder(tmpUser);

                allAccounts.add(tmpAccount);
                tmpAccount = new Accounts();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allAccounts;
    }

    public Accounts createNewAccount(Users user, String type, float initialBalance) {
        Accounts account = null;
        Connection conn = null;
        account = findByAccount (user.getEmail_address());
        Accounts newAccount = null;
        if (!account.getHolder().getEmail_address().equals("default")){
            System.out.println("User already has an account.");
        }
        else{
            try {
                conn = connectionUtils.getConnection();

                String sql = "insert into myschema.accounts (account_type, balance , account_holder ) values ('" +
                        type + "', " + initialBalance + " ,'" + user.getEmail_address() + "');";
                Statement statement = conn.createStatement();
                statement.executeUpdate(sql);
                newAccount = findByAccount(user.getEmail_address());
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


        return newAccount;
    }


}

