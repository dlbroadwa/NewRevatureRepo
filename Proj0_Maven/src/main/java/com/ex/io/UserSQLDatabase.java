package com.ex.io;

import com.ex.dao.UserDAO;
import com.ex.models.User;
import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSQLDatabase implements UserDAO {
    private final DatabaseConnection dc;

    UserSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

    @Override
    public boolean add(User user) {
        // Ensure that a user with the same card number doesn't already exist
        if (getUserInfo(user.getCardNumber()) != null)
            return false;

        int addedRowCount = 0;
        String sql = "INSERT INTO " + dc.getSchema() +
                ".users (card_number, first_name, last_name) values (?, ?, ?)";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, user.getCardNumber());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());

            addedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return addedRowCount == 1;
    }

    @Override
    public boolean remove(int cardNumber) {
        int deletedRowCount = 0;
        String sql = "DELETE FROM " + dc.getSchema() + ".users where card_number = ?";
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, cardNumber);

            deletedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deletedRowCount > 0;
    }

    @Override
    public boolean update(int cardNumber, User newUserInfo) {
        int updatedRowCount = 0;
        String sql = "UPDATE " + dc.getSchema() +
                ".users set card_number=?, first_name=?, last_name=? where card_number=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, newUserInfo.getCardNumber());
            statement.setString(2, newUserInfo.getFirstName());
            statement.setString(3, newUserInfo.getLastName());

            updatedRowCount = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updatedRowCount > 0;
    }

    @Override
    public User getUserInfo(int cardNumber) {
        String sql = "SELECT card_number, first_name, last_name from " + dc.getSchema()
                + ".users WHERE card_number = ?";

        User user = null;
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, cardNumber);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setCardNumber(rs.getInt("card_number"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    private List<User> runFindQuery(String query, String param) {
        List<User> result = new ArrayList<>();

        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, param);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setCardNumber(rs.getInt("card_number"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));

                    result.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<User> findLastName(String query) {
        // ILIKE is a PostgreSQL extension...
        String sql = "SELECT card_number, first_name, last_name from " + dc.getSchema()
                + ".users WHERE last_name ILIKE %?%";
        return runFindQuery(sql, query);
    }

    @Override
    public List<User> findFirstName(String query) {
        // More ILIKE usage O_o
        String sql = "SELECT card_number, first_name, last_name from " + dc.getSchema()
                + ".users WHERE first_name ILIKE %?%";
        return runFindQuery(sql, query);
    }
}
