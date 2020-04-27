/**
 * User Repository DAO to write to the Users table in database
 * key methods used are:
 *  findByUsername - queries for and returns 1 entry for user with given username
 *  save - inserts new row into database
 *  update - updates one row in database
 */

package com.williamchung.project0.repositories;

import com.williamchung.project0.models.User;
import com.williamchung.project0.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer> {
    private ConnectionUtil connectionUtil;

    public UserRepository(ConnectionUtil connectionUtil) {
        if(connectionUtil != null) {
            this.connectionUtil = connectionUtil;
        }
    }

    public User findByUsername(String username) {
        Connection connection = null;
        User user = null;
        try {
            //Opening Connection, creating and then executing the query
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".users where username = '" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            //Translating ResultSet into Java Object via Setters
            while(resultSet.next()){
                String tempUsername = resultSet.getString("username");
                String tempPassword = resultSet.getString("password");
                Double tempCheckingBalance = resultSet.getDouble("checking_balance");
                int tempId = resultSet.getInt("id");
                user = new User(tempUsername, tempPassword, tempCheckingBalance);
                user.setId(tempId);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            //Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return user;
    }

    @Override //Not used
    public User findById(Integer integer) {
        return null;
    }

    @Override //Not used
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        return users;
    }

    @Override
    public Integer save(User obj) {
        Connection connection = null;
        try {
            //Opening connection, creating and executing query
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();

            //Insert statement created with Getters
            String sql = "INSERT INTO " + schemaName + ".users (username, password, checking_balance) " +
                    "VALUES ('" + obj.getUsername() + "', '" + obj.getPassword() + "', " + obj.getCheckingBalance() + ");";
            Statement statement = connection.createStatement();

            //Band-aid return typing because statement.execute() returns a boolean
            boolean successful = statement.execute(sql);
            if (successful) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            //Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public void update(User newObj, Integer integer) {
        Connection connection = null;
        User user = newObj;
        try {
            //Creating connection and executing query
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            //Update statement for one row by id
            String sql = "UPDATE " + schemaName + ".users SET checking_balance = " + user.getCheckingBalance() + " WHERE id = " + integer;
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            //Close connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Override //Not used
    public void delete(User obj) {
    }
}
