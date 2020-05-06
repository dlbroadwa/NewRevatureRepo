package com.ex.data;

/**
 *   AccountSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added added DatabaseConnection, add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method - April 29
 *
 */

import com.ex.models.Account;
import com.ex.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountSQLDatabase implements GenericDAO<Account, String> {
    private final DatabaseConnection dc;

//Constructors
    public AccountSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
    }

//Methods
    public boolean add(Account newAccount) {
        if (findByID(newAccount.getEmail()) != null) {
            return false;
        }
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
    public List<Account> findAll() {
        List<Account> results = null;

        String sql = "SELECT * from " + dc.getSchema() + ".accounts order by name";

        try (Connection conn = dc.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            results = new ArrayList<>();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isEmployee = rs.getBoolean("is_employee");
                boolean isManager = rs.getBoolean("is_manager");

                results.add(new Account(name, email, password, isEmployee, isManager));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
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
        int deletedRowCount = -1;

        String sql = "DELETE FROM " + dc.getSchema() + ".accounts WHERE email=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);

            deletedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deletedRowCount != -1;
    }

}//End of AccountSQLDatabase Class
