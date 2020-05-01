package com.ex.data;

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountIDHandler {
    private final DatabaseConnection dc;

    public AccountIDHandler(DatabaseConnection dc) {
        this.dc = dc;
    }

    public int getCustomerID(Account account) throws SQLException {
        String sql = "SELECT user_id FROM " + dc.getSchema() + ".accounts WHERE email=?";
        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        }

        return -1;
    }

    public Account getCustomerAccount(int id) throws SQLException {
        Account result = null;

        String sql = "SELECT name, email, password, is_employee, is_manager FROM " +
                dc.getSchema() + ".accounts WHERE user_id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    boolean isEmployee = rs.getBoolean("is_employee");
                    boolean isManager = rs.getBoolean("is_manager");
                    result = new Account(name, email, password, isEmployee, isManager);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
