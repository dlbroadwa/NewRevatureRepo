package com.ex.data;

import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSQLDatabase implements GenericDAO<Product, Integer> {
    private final DatabaseConnection dc;
    private final ProductTypeHandler productTypeHandler;
    private final String joinQuery;

    public ProductSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
        this.productTypeHandler = new ProductTypeHandler(this.dc);
        this.joinQuery = dc.getSchema() + ".products P INNER JOIN " + dc.getSchema() +
                ".product_types T ON P.type_id = T.id";
    }

    @Override
    public boolean add(Product prod) {
        int addedRowCount = 0;

        String sql = "INSERT INTO " + dc.getSchema() +
                ".products (product_id, type_id, product_name, price_cents, qty) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            int productTypeID = productTypeHandler.typeToID(prod.getProductType());

            conn = dc.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, prod.getProductID());
            ps.setInt(2, productTypeID);
            ps.setString(3, prod.getName());
            ps.setInt(4, prod.getPrice());
            ps.setInt(5, prod.getQty());

            addedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return addedRowCount > 0;
    }

    @Override
    public Product findByID(Integer id) {
        Product result = null;

        String sql = "SELECT product_id, product_name, price_cents, qty, type_name FROM " + joinQuery +
                " WHERE product_id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new Product();
                    result.setProductID(rs.getInt("product_id"));
                    result.setName(rs.getString("product_name"));
                    result.setPrice(rs.getInt("price_cents"));
                    result.setQty(rs.getInt("qty"));
                    result.setProductType(rs.getString("type_name"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(Integer id, Product newObj) {
        return false;
    }

    @Override
    public boolean remove(Integer id) {
        int removedRowCount = 0;

        String sql = "DELETE FROM " + dc.getSchema() + ".products WHERE product_id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            removedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return removedRowCount > 0;
    }
}
