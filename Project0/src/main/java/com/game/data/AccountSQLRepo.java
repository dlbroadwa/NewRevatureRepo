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
        return null;
    }

    @Override
    public List findAll() {
        //reuse code from flashcard project
        Connection connection = null;
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return accountList;
    }

    @Override
    public String save(Account obj) {
        return null;
    }

    @Override
    public void update(Account newObj, String s) {

    }

    @Override
    public void delete(Account obj) {

    }
}
