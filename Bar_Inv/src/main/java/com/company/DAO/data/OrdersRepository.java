package com.company.DAO.data;

import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository implements Repository<Order, String, String> {
    private ConnectionUtils connectionUtils;
    public OrdersRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }


    //create new order
    @Override
    public void save(Order obj) {
        Connection conn = null;
        try{
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql ="insert into "+schemaName+".prevorders (customer, itemid, quantity) values ('"+obj.getCustomerName()+"', "+obj.getItemID()+", "+obj.getQuantity()+")";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //display orders for a given username

    @Override
    public List<Order> findAllForName(String s)  {
        List<Order> custOrders = new ArrayList();
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select * from " + schemaName + ".prevorders where customer=" + s;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()){
                Order tmp = new Order();
                tmp.setCustomerName(rs.getString("customer"));
                tmp.setItemID(rs.getInt("itemid"));
                tmp.setQuantity(rs.getInt("quantity"));
                custOrders.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return custOrders;
    }

    @Override
    public List<Order> findAll() throws SQLException {
        Connection conn = null;
        List<Order> orders = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select * from " + schemaName + ".prevorders";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);

            while(rs.next()){
                String customer = rs.getString("customer");
                int id = rs.getInt("itemid");
                int quant = rs.getInt("quantity");
                Order tmp = new Order();
                tmp.setQuantity(quant);
                tmp.setItemID(id);
                tmp.setCustomerName(customer);

                orders.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return orders;
    }


    @Override
    public void deleteByID(String s) {

    }

//don't need to update old orders
    @Override
    public void updateByID(Order obj) {

    }

    @Override
    public List<Order> compareColumns(String s1, String s2, String s3) {
        return null;
    }

    //don't need to display a single order from a user, I need to display all the order from a user
    @Override
    public Order findByID(String username) {
        return null;
    }


}
