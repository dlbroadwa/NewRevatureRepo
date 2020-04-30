package com.ex.data;

import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeHandler {
    private final DatabaseConnection dc;

    public ProductTypeHandler(DatabaseConnection dc) {
        this.dc = dc;
    }

    private int findID(String type) throws SQLException {
        String sql = "SELECT id FROM " + dc.getSchema() + ".product_types WHERE type_name=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    public int typeToID(String typeName) throws SQLException {
        // Look to see if the product type already exists in the database
        int id = findID(typeName);

        // If it doesn't, try to add it
        if (id != -1) {
            String insertSql = "INSERT INTO " + dc.getSchema() +
                    ".product_types (type_name) VALUES (?) RETURNING id";
            try (Connection conn = dc.getConnection();
                 PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, typeName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt(1);
                    }
                    else {
                        throw new RuntimeException("Couldn't add new product type to the database!");
                    }
                }
            }
        }

        return id;
    }

    public String idToType(int id) throws SQLException {
        String sql = "SELECT type_name FROM " + dc.getSchema() + ".product_types WHERE id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getString(1);
            }
        }

        throw new RuntimeException("Type ID not found");
    }
}
