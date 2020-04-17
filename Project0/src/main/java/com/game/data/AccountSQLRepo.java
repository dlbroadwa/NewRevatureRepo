package com.game.data;

import com.game.models.Account;
import com.game.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountSQLRepo implements Repository<Account,String> {
    private ConnectionUtils connectionUtils;

    public AccountSQLRepo(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Account findById(String s) {
        Account temp = null;
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "select password, isadmin, credits from " + schemaName + ".accountlist " +
                    "where username = '"+s+"';";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                temp = new Account(s, rs.getString("password"), rs.getBoolean("isadmin"), rs.getInt("credits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public List findAll() {
        //reuse code from flashcard project
        Connection connection;
        List<Account> accountList = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select username, password, credits, isadmin from " + schemaName + ".accountlist ";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            Account temp;

            while(rs.next()) {
                temp = new Account(rs.getString("username"),rs.getString("password"),
                        rs.getBoolean("isadmin"),rs.getInt("credits"));

                accountList.add(temp);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    //add new account object
    @Override
    public void save(Account obj) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "insert into " + schemaName + ".accountlist " +
                    "(username,password,isadmin,credits) values ('" +
                    obj.getName()+"','"+obj.getPassword()+"',"+
                    obj.isAdmin()+","+obj.getBalance()+");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account obj) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "update " + schemaName + ".accountlist " +
                    "set password = '"+obj.getPassword()+"',"+
                    "credits = "+obj.getBalance()+" where " +
                    "username = '"+obj.getName()+"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String s) {
        try {
            Connection connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "delete from " + schemaName + ".accountlist " +
                    "where username = '"+s+"';";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
