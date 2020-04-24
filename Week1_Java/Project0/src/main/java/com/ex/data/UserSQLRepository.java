package com.ex.data;

import com.ex.models.User;
import com.ex.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserSQLRepository implements Repository<User, Integer>{
    private ConnectionUtils connectionUtils;
    public UserSQLRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> Users = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "Select id, email from " + schemaName + ".User";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");

                User temp = new User();
                temp.setEmail(email);
                temp.setId(id);

                Users.add(temp);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return Users;
    }

    @Override
    public Integer save(User obj) {
        return null;
    }

    @Override
    public void update(User newObj, Integer integer) {

    }

    @Override
    public void delete(User obj) {

    }
}
