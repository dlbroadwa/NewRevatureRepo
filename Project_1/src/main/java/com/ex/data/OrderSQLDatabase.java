package com.ex.data;

import com.ex.models.Account;
import com.ex.models.Order;
import com.ex.models.Product;
import com.ex.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *   OrderSQlDatabase is used
 *   Created by: Perry Lee on April 28,2020
 *   Perry Lee: Added the add method, findById method, update method, and remove method - April 28
 *   Paityn Maynard: Added findAll method, DatabaseConnection and Constructor - April 29
 */
public class OrderSQLDatabase implements GenericDAO<Order, Integer> {//Start OrderSQLDatabase Class
//Instant Variables
    private final DatabaseConnection dc;
    private final AccountIDHandler accountIDHandler;
    private final ProductTypeHandler productTypeHandler;

//Constructors
    public OrderSQLDatabase(DatabaseConnection dc) {
        this.dc = dc;
        this.accountIDHandler = new AccountIDHandler(this.dc);
        this.productTypeHandler = new ProductTypeHandler(this.dc);
    }
// Private helpers
    private boolean doAdd(Order order, Connection conn) throws SQLException {
        int addedRowCount = 0;
        String sql = "INSERT INTO " + dc.getSchema() +
                ".orders (order_id, customer_id, status_id) VALUES (?, ?, ?)";
        int customerID = accountIDHandler.getCustomerID(order.getCustomer());
        // Abort if customer doesn't exist for whatever reason
        if (customerID == -1)
            return false;

        int orderID = order.getOrderConfirmation();
        // Create entry in orders table
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ps.setInt(2, customerID);
            ps.setInt(3, order.getStatusCode());

            addedRowCount = ps.executeUpdate();
        }

        // If that failed, then don't bother
        if (addedRowCount < 1)
            return false;

        addedRowCount = 0;
        // Populate order_items table
        sql = "INSERT INTO " + dc.getSchema() +
                ".order_items (order_id, product_id, sale_price, sale_qty) VALUES (?,?,?,?)";
        for (Product prod : order.getOrderProducts()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, orderID);
                ps.setInt(2, prod.getProductID());
                ps.setInt(3, prod.getPrice());
                ps.setInt(4, prod.getQty());

                addedRowCount += ps.executeUpdate();
            }
        }
        return addedRowCount == order.getOrderProducts().size();
    }

    private boolean doRemove(Integer orderNumber, Connection conn) throws SQLException {
        int removedRowCount = -1;
        String sql = "DELETE FROM " + dc.getSchema() + ".orders WHERE order_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderNumber);

            removedRowCount = ps.executeUpdate();
        }

        return removedRowCount != -1;
    }
    
//Methods
    /**
     * Adds an order to the orders database.
     * Note, this method does NOT update the corresponding stock quantity for
     * the products in the order, nor does it verify the sale price or quantity
     * of those products.
     * @param order the order to be added
     * @return <code>true</code> if the order was successfully added, <code>false</code> otherwise.
     */
    public boolean add(Order order) {
        Connection conn = null;
        
        try {
            conn = dc.getConnection();
            // For the sake of ACID, this whole thing should be one transaction--all or nothing
            conn.setAutoCommit(false);
            
            if (doAdd(order, conn)) {
                conn.commit(); // Save everything
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Not sure if this line is needed, but just to be safe
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Retrieves the order with the specified order number.
     * @param orderNumber the order number to search for
     * @return the requested order information, or <code>null</code> if that order could not be found.
     */
    public Order findByID(Integer orderNumber) {
        Order order = null;
        // This is disgusting
        String sql = "SELECT O.order_id, O.customer_id, O.status_id, " +
                "I.product_id, P.product_name, P.type_id, I.sale_price, I.sale_qty FROM " +
                dc.getSchema() + ".orders O INNER JOIN " + dc.getSchema() +
                " .order_items I ON O.order_id=I.order_id INNER JOIN " + dc.getSchema() +
                ".products P ON I.product_id=P.product_id WHERE O.order_id=?";

        try (Connection conn = dc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderNumber);

            Account customerInfo = null;
            order = new Order();
            List<Product> orderProducts = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    order.setOrderConfirmation(rs.getInt(1));
                    order.setCustomer(accountIDHandler.getCustomerAccount(rs.getInt(2)));
                    order.setStatusCode(rs.getInt(3));

                    do {
                        int prodID = rs.getInt(4);
                        String prodName = rs.getString(5);
                        int prodTypeID = rs.getInt(6);
                        String prodType = productTypeHandler.idToType(prodTypeID);
                        int salePrice = rs.getInt(7);
                        int saleQty = rs.getInt(8);

                        orderProducts.add(new Product(prodID, prodName, prodType, salePrice, saleQty));
                    } while (rs.next());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return order;
    }

    /**
     * Retrieves a list of all orders.
     * @return a list of orders, or <code>null</code> if an error occurred.
     */
    public List<Order> findAll() {
        // First get a list of all order IDs
        String sql = "SELECT order_id FROM " + dc.getSchema() + ".orders";
        List<Integer> allOrderIds = new ArrayList<>();

        try (Connection conn = dc.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                allOrderIds.add(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        // Then call findByID on each one to get a list of all orders...
        List<Order> allOrders = new ArrayList<>();
        for (Integer oid: allOrderIds) {
            Order order = findByID(oid);
            if (order != null) {
                allOrders.add(order);
            }
            else {
                // Hopefully this never happens?
                System.err.println("WARNING: phantom order???");
            }

        }

        return allOrders;
    }

    
    public boolean update(Integer orderNumber, Order newOrderInfo) {
        // Man, this code is really a mess...
        Connection conn = null;

        try {
            conn = dc.getConnection();
            conn.setAutoCommit(false);

            if (doRemove(orderNumber, conn) && doAdd(newOrderInfo, conn)) {
                conn.commit();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    public boolean remove(Integer orderNumber) {
        try (Connection conn = dc.getConnection()) {
            return doRemove(orderNumber, conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}//End OrderSQLDatabase Class
