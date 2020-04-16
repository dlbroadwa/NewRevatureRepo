package com.ex.services;

import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseIDService {
    private final DatabaseConnection dc;

    protected DatabaseConnection getConnection() {
        return dc;
    }
    protected int runQuery(String query, int param) {
        try (Connection conn = dc.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, param);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }

    public DatabaseIDService(DatabaseConnection dc) {
        this.dc = dc;
    }

    public abstract int toID(int value);
    public abstract int fromID(int id);
}
