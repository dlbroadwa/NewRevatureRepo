package main.java.dao;

import main.java.models.Customer;
import main.java.utils.ConnectionUtils;
import main.java.utils.PostgresConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Project 2:<br>
 * <br>
 *  The CustomerDAO class serves to collect, manipulate and persist data brought in from a AWS RDB hosted
 *    Postgresql database used as the main method of data storage for Project 2's coaster system.
 *  This will only work with Customers and integers as a primary key.
 *
 *  <br> <br>
 *  Created: <br>
 *     11 May 2020, Barthelemy Martinon<br>
 *     With assistance from:<br>
 *  Modifications: <br>
 *     11 May 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 11 May 2020
 */

public class CustomerDAO implements DAO<Customer, Integer> {
    // Instance Variables
    private ConnectionUtils connectionUtil;

    // Constructor
    public CustomerDAO(PostgresConnectionUtil connectionUtil) {
        if(connectionUtil != null) {
            this.connectionUtil = connectionUtil;
        }
    }

    // Getter Methods

    public ConnectionUtils getConnectionUtil() { return connectionUtil; }

    // Setter Methods

    public void setConnectionUtil(ConnectionUtils connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    // Methods

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query with WHERE clause to search for an entry with the
     *   specified integer for idnum. Returns null is nothing is found.
     *
     * 	@return c Customer with target idnum (or null)
     */
    public Customer findById(Integer integer) {
        Connection connection = null;
        Customer c = null;
        Integer targetIdNum = integer;

        try {
            connection = connectionUtil.getConnection();
            //String schemaName = connectionUtil.getDefaultSchema();
            //String sql = "Select * from " + schemaName + ".animals where petid=" + targetIdNum;
            String sql = "Select * from project2.customers where customerid=" + targetIdNum;
            PreparedStatement findByIDStatement = connection.prepareStatement(sql);
            ResultSet rs = findByIDStatement.executeQuery();

            while(rs.next()) {
                int ticketid = rs.getInt("ticketid");
                int customerid = rs.getInt("customerid");
                String email = rs.getString("email");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                c = new Customer(ticketid,customerid,firstname,lastname,email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return c;
    }

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query to obtain all entries.
     * All "translated" entries are put into an ArrayList of Cutsomers, which is returned.
     *
     * 	@return customerList Customer ArrayList of all database rows.
     */
    public ArrayList<Customer> findAll() {
        Connection connection = null;
        ArrayList<Customer> customerList = new ArrayList<Customer>();

        try {
            connection = connectionUtil.getConnection();
            //String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from project2.customers order by customerid";
            PreparedStatement findAllStatement = connection.prepareStatement(sql);
            ResultSet rs = findAllStatement.executeQuery();

            while(rs.next()) {
                Customer temp = null;

                int ticketid = rs.getInt("ticketid");
                int customerid = rs.getInt("customerid");
                String email = rs.getString("email");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");

                temp = new Customer(ticketid,customerid,firstname,lastname,email);
                customerList.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return customerList;
    }

    /**
     * Takes a newly created Customer and runs a hard-coded INSERT SQL statement to add the Customer into the database
     *   as a table entry.
     * Information found within the Customer given as input is collected and converted into the necessary values needed
     *   to complete the INSERT statement.
     *
     *  @param obj New Customer created to be added
     *
     * 	@return statement.executeUpdate(sql) Integer value representing the amount of rows affected by the statement.
     */
    public Integer save(Customer obj) {
        Connection connection = null;

        // Extract all information from Customer instance to be stored as values for the new table entry
        String email = "'" + obj.getEmail() + "'";
        String lastname = "'" + obj.getLastname() + "'";
        String firstname = "'" + obj.getFirstname() + "'";

        try {
            connection = connectionUtil.getConnection();
            //String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Insert into project2.customers (email, lastname, firstname) values " +
                    "(" + email + ", " + lastname + ", " + firstname + ")";
            PreparedStatement saveStatement = connection.prepareStatement(sql);
            saveStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return -1;
    }

    /**
     * Takes a Customer with updated information and an Integer that represents a target Customer's ID number and search
     *    through the customers table for a match. If one is found, the new Customer information overrides the target's
     *   information. (Mainly ticket information) If none are found, nothing happens.
     *
     *  @param newObj Customer to be updated
     *  @param integer Integer value representing target Customer ID
     */
    public void update(Customer newObj, Integer integer) {
        Connection connection = null;

        int targetCustomerID = integer;

        String email = "'" + newObj.getEmail() + "'";
        String lastname = "'" + newObj.getLastname() + "'";
        String firstname = "'" + newObj.getFirstname() + "'";
        String ticketid = "'" + newObj.getTicketID() + "'";

        try {
            connection = connectionUtil.getConnection();
            //String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Update project2.customers set " +
                    "ticketid=" + ticketid + "," +
                    "email=" + email + "," +
                    "lastname=" + lastname + "," +
                    "firstname=" + firstname + " where customerid=" + targetCustomerID;
            PreparedStatement updateStatement = connection.prepareStatement(sql);
            updateStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    /**
     * Takes a Customer and runs a hard-coded DELETE SQL statement to remove the entry from the database.
     *
     *  @param obj Customer to be removed
     */
    public void delete(Customer obj) {
        Connection connection = null;
        int idNum = obj.getCustomerID();

        try {
            connection = connectionUtil.getConnection();
            //String schemaName = connectionUtil.getDefaultSchema();
            String sql = "delete from project2.customers where customerid=" + idNum;
            PreparedStatement deleteStatement = connection.prepareStatement(sql);
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }


}
