package com.ex.data;

/**
 *   ProductSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added added the add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method, DatabaseConnection and Constructor - April 29
 */
 
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        // Get the product type ID from the product_types database first
        int productTypeID;
        try {
            productTypeID = productTypeHandler.typeToID(prod.getProductType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        // Now add the new product
        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, prod.getProductID());
            ps.setInt(2, productTypeID);
            ps.setString(3, prod.getName());
            ps.setInt(4, prod.getPrice());
            ps.setInt(5, prod.getQty());

            addedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    public List<Product> findAll() {
        List<Product> results = null;

        String sql = "SELECT * from " + joinQuery;

        try (Connection conn = dc.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            results = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                int price = rs.getInt("price_cents");
                int qty = rs.getInt("qty");
                String prodType = rs.getString("type_name");

                results.add(new Product(id, name, prodType, price, qty));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }
    
    @Override
    public boolean update(Integer id, Product newProd) {
        int updatedRowCount = 0;

        String sql = "UPDATE " + dc.getSchema() +
                ".products SET product_id=?, type_id=?, product_name=?, price_cents=?, qty=? WHERE product_id=?";

        // Get the updated product type ID first
        int productTypeID;
        try {
            productTypeID = productTypeHandler.typeToID(newProd.getProductType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        // Now we have all of the information to do the update
        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newProd.getProductID());
            ps.setInt(2, productTypeID);
            ps.setString(3, newProd.getName());
            ps.setInt(4, newProd.getPrice());
            ps.setInt(5, newProd.getQty());
            ps.setInt(6, id);

            updatedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updatedRowCount > 0;
    }

    @Override
    public boolean remove(Integer id) {
        int removedRowCount = -1;

        String sql = "DELETE FROM " + dc.getSchema() + ".products WHERE product_id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            removedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return removedRowCount != -1;
    }

}//End of ProductSQLDatabase
