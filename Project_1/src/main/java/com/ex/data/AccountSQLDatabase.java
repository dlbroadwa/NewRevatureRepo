package com.ex.data;

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSQLDatabase implements GenericDAO<Account, String> {
    private final DatabaseConnection dc;

    public AccountSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

    @Override
    public boolean add(Account newAccount) {
        if (findByID(newAccount.getEmail()) != null)
            return false;

        int addedRowCount = 0;
        String sql = "INSERT INTO " + dc.getSchema() +
                ".accounts (name, email, password, is_employee, is_manager) values (?, ?, ?, ?, ?)";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newAccount.getName());
            ps.setString(2, newAccount.getEmail());
            ps.setString(3, newAccount.getPassword());
            ps.setBoolean(4, newAccount.getEmployee());
            ps.setBoolean(5, newAccount.getManager());

            addedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return addedRowCount == 1;
    }

    @Override
    public Account findByID(String email) {
        Account result = null;

        String sql = "SELECT name, email, password, is_employee, is_manager FROM " +
                dc.getSchema() + ".accounts WHERE email=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new Account();
                    result.setName(rs.getString("name"));
                    result.setEmail(rs.getString("email"));
                    result.setPassword(rs.getString("password"));
                    result.setEmployee(rs.getBoolean("is_employee"));
                    result.setManager(rs.getBoolean("is_manager"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(String email, Account newAccount) {
        int updatedRowCount = 0;

        String sql = "UPDATE " + dc.getSchema() +
                ".accounts SET name=?, email=?, password=?, is_employee=?, is_manager=? WHERE email=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newAccount.getName());
            ps.setString(2, newAccount.getEmail());
            ps.setString(3, newAccount.getPassword());
            ps.setBoolean(4, newAccount.getEmployee());
            ps.setBoolean(5, newAccount.getManager());
            ps.setString(6, email);

            updatedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updatedRowCount > 0;
    }

    @Override
    public boolean remove(String email) {
        int deletedRowCount = 0;

        String sql = "DELETE FROM " + dc.getSchema() + ".accounts WHERE email=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            deletedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deletedRowCount > 0;
    }
}
