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
        // given an order, save it in the prevorders table
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
    public Order findByID(String id) {
        // pull the order based on the order ID given as an input
        Order order = new Order();
        Connection conn = null;
        int idInt = Integer.parseInt(id); //change the string input into an int
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select * from " + schemaName + ".prevorders where orderid=" + idInt;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()){
                //define the attributes of the order that will be returned
                order.setItemID(rs.getInt("itemid"));
                order.setCustomerName(rs.getString("customer"));
                order.setQuantity(rs.getInt("quantity"));
                order.setOrderID(rs.getInt("orderid"));
                order.setMarked_complete(rs.getInt("marked_complete"));
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
            return order;
        }
    }

    @Override
    public void updateByID(Order obj) {
        //used only for marking an order as complete
        //take an input of an order
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "update " + schemaName + ".prevorders set marked_complete=1 where orderid=" + obj.getOrderID(); //use the order ID saved in the order object to find the correct place
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);

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
    }

    @Override
    public List<Order> findAllForName(String s)  {
        //given the input of a username, return all the orders placed by that user
        List<Order> custOrders = new ArrayList();
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select * from " + schemaName + ".prevorders where customer= '" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while (rs.next()){
                Order tmp = new Order();
                tmp.setCustomerName(rs.getString("customer"));
                tmp.setItemID(rs.getInt("itemid"));
                tmp.setQuantity(rs.getInt("quantity"));
                tmp.setOrderID(rs.getInt("orderid"));
                tmp.setMarked_complete(rs.getInt("marked_complete"));
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
        //return all of the rows on the prevorders table
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
                tmp.setOrderID(rs.getInt("orderid"));
                tmp.setMarked_complete(rs.getInt("marked_complete"));


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
        //we do not delete old orders, only mark them as complete
    }


    @Override
    public List<Order> compareColumns(String s1, String s2, String s3) {
        //we don't need this functionality for the orders
        return null;
    }



}
