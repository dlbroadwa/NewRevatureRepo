package com.company.DAO.data.web;

import com.company.DAO.DatabaseConnection;
import com.company.DAO.models.Order;
import com.company.DAO.utils.servletsConnection.PostgresSQLService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    private OrdersDAO(){}
    DatabaseConnection databaseConnection = new DatabaseConnection();
    private static OrdersDAO ordersDAO;
    public static OrdersDAO getInstance(){
        if(ordersDAO == null)
            ordersDAO = new OrdersDAO();
        return ordersDAO;
    }
    public static Connection getConnection() throws SQLException {
        PostgresSQLService.addDBConnection(
                "jdbc:postgresql://project0-bar-inv.ctadktwfuhte.us-west-1.rds.amazonaws.com:5432/postgres", "bar_guy", "bigpass");
        Connection connection = PostgresSQLService.getConnection(0);
        return connection;
    }

    public static int save(Order obj){
        int status =0;
        Connection conn = null;
        try{
            conn = OrdersDAO.getConnection();
            String sql ="insert into public.prevorders (customer, itemid, quantity) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,obj.getCustomerName());
            ps.setInt(2,obj.getItemID());
            ps.setInt(3, obj.getQuantity());
            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static Order findByID(Integer id) {
        Order order = new Order();
        Connection conn = null;
        try {
            conn = ordersDAO.getConnection();
            String sqlQuery = "Select * from public.prevorders where orderid=?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
        }
        return order;
    }

    public static int markComplete(Integer id) {
        //used only for marking an order as complete
        //take an input of an order
        Connection conn = null;
        int status =0;
        try {
            conn = ordersDAO.getConnection();
            String sqlQuery = "update public.prevorders set marked_complete=1 where orderid=?"; //use the order ID saved in the order object to find the correct place
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            status=ps.executeUpdate(sqlQuery);

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
        return status;
    }

    public static List<Order> findAllForName(String s)  {
        //given the input of a username, return all the orders placed by that user
        List<Order> custOrders = new ArrayList();
        Connection conn = null;
        try {
            conn = ordersDAO.getConnection();
            String sqlQuery = "Select * from public.prevorders where customer= ?";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setString(1,s);
            ResultSet rs = ps.executeQuery();
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

    public static List<Order> findAll()  {
        //given the input of a username, return all the orders placed by that user
        List<Order> custOrders = new ArrayList();
        Connection conn = null;
        try {
            conn = ordersDAO.getConnection();
            String sqlQuery = "Select * from public.prevorders";
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
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

}
