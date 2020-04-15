package com.company.DataAccess;

import com.company.Banking.BankCustomer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreatorSQLRepository implements DAO<BankCustomer, Integer> {

    private ConnectionUtils connectionUtils;
    public CreatorSQLRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public BankCustomer findById(Integer integer) {
        return null;
    }

    @Override
    public List<BankCustomer> findAll() {
        Connection connection = null;
        List<BankCustomer> creators = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, username from " + schemaName + ".customers";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String creatorName = rs.getString("username");

                BankCustomer temp = new BankCustomer();
                temp.setUserName(creatorName);
                temp.setId(id);

                creators.add(temp);
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
        return creators;
    }

    @Override
    public Integer save(BankCustomer obj) {
        return null;
    }

    @Override
    public void update(BankCustomer newObj, Integer integer) {

    }

    @Override
    public void delete(BankCustomer obj) {

    }

}
