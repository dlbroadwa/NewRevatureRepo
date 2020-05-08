package dev.repos;

import dev.connections.ConnectionUtil;
import dev.models.user.Admin;
import dev.models.user.Customer;
import dev.models.user.Employee;
import dev.models.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *  Project 1:<br>
 * <br>
 *  The UserSQLRepository class serves to collect, manipulate and persist data brought in from a AWS RDB hosted
 *    Postgresql database used as the main method of data storage for Project 1's local shelter system.
 *  This Repository subclass is the main force behind the ability to authenticate a user either as a Customer with the
 *    ability and clearance to only search for Pets, while Employees will be able to manipulate the Pet database.
 *  This will only work with Users and integers as a primary key.
 *
 *  <br> <br>
 *  Created: <br>
 *     01 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     01 May 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped findByID, finaAll, save and delete methods with current
 *     										    projected idea of structure based around current User design.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 01 May 2020
 */
public class UserSQLRepository implements Repository<User, Integer> {
    // Instance Variables
    private ConnectionUtil connectionUtil;

    // Constructor
    public UserSQLRepository(ConnectionUtil connectionUtil) {
        if(connectionUtil != null) {
            this.connectionUtil = connectionUtil;
        }
    }

    // Getter Methods

    public ConnectionUtil getConnectionUtil() { return connectionUtil; }

    // Setter Methods

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    // Methods

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query with WHERE clause to search for an entry with the
     *   specified integer for idnum. Returns null is nothing is found.
     *
     * 	@return user User with target idnum (or null)
     */
    public User findById(Integer integer) {
        Connection connection = null;
        User user = null;
        Integer targetIdNum = integer;

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".users where userid=" + targetIdNum;
            PreparedStatement findByIDStatement = connection.prepareStatement(sql);
            ResultSet rs = findByIDStatement.executeQuery();

            while(rs.next()) {
                String userType = rs.getString("usertype");

                int userID = rs.getInt("userid");
                String fName = rs.getString("fname");
                String lName = rs.getString("lname");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");

                if (userType.equals("customer")) {
                    user = new Customer(fName,lName,userID,userName,passWord);
                } else if (userType.equals("employee")) {
                    user = new Employee(fName,lName,userID,userName,passWord);
                } else if (userType.equals("admin")) {
                    user = new Admin(fName,lName,userID,userName,passWord);
                }
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
        return user;
    }

    /**
     * Takes the database content, runs a hard-coded SELECT SQL query to obtain all entries.
     * Entries are scanned for a specific value under userType to be "translated" into an instance of the appropriate
     *   User subclass with the User information as parameter inputs.
     * All "translated" entries are put into an ArrayList of users, which is returned to for ShelterApplication
     *   interaction.
     *
     * 	@return users User ArrayList of all database rows.
     */
    public ArrayList<User> findAll() {
        Connection connection = null;
        ArrayList<User> users = new ArrayList<User>();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".users order by userid";
            PreparedStatement findAllStatement = connection.prepareStatement(sql);
            ResultSet rs = findAllStatement.executeQuery();

            while(rs.next()) {
                User temp = null;

                String userType = rs.getString("usertype");

                int userID = rs.getInt("userid");
                String fName = rs.getString("fname");
                String lName = rs.getString("lname");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");

                if (userType.equals("customer")) {
                    temp = new Customer(fName,lName,userID,userName,passWord);
                } else if (userType.equals("employee")) {
                    temp = new Employee(fName,lName,userID,userName,passWord);
                } else if (userType.equals("admin")) {
                    temp = new Admin(fName,lName,userID,userName,passWord);
                }
                users.add(temp);
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
        return users;
    }

    /**
     * Takes a newly created User from the ShelterApplication, and runs a hard-coded INSERT SQL statement to add the
     *   User into the database as a table entry.
     * Information found within the Pet given as input is collected and converted into the necessary values needed to
     *   complete the INSERT statement.
     *
     *  @param obj New User created by employee/admin
     *
     * 	@return statement.executeUpdate(sql) Integer value representing the amount of rows affected by the statement.
     */
    public Integer save(User obj) {
        Connection connection = null;

        // Extract all information from Pet instance to be stored as values for the new table entry
        // Base Item Info
        String fName = "'" + obj.getFirstname() + "'";
        String lName = "'" + obj.getLastname() + "'";
        String userID = "'" + obj.getID() + "'";
        String userName = "'" + obj.getUsername() + "'";
        String passWord = "'" + obj.getPassword() + "'";

        // Subclass Info
        String userType = null;

        if ( obj instanceof Customer ) {
            userType = "'customer'";
        } else if ( obj instanceof Employee ) {
            userType = "'employee'";
        } else if ( obj instanceof Admin ) {
            userType = "'admin'";
        }

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Insert into " + schemaName + ".users (userid, fname, lname, usertype, username, password) values " +
                    "(" + userID + ", " + fName + ", " + lName + ", " + userType + ", " + userName + ", " + passWord + ")";
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
     * Takes a User with updated information and an Integer that represents a target User's ID number and search through
     *   the animals table for a match. If one is found, the new User information overrides the target's information.
     *   If none are found, nothing happens.
     *
     *  @param newObj User to be updated
     *  @param integer Integer value representing target User ID
     */
    public void update(User newObj, Integer integer) {
        Connection connection = null;

        int targetUserID = integer;

        String fName = "'" + newObj.getFirstname() + "'";
        String lName = "'" + newObj.getLastname() + "'";
        String userName = "'" + newObj.getUsername() + "'";
        String passWord = "'" + newObj.getPassword() + "'";

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Update " + schemaName + ".users set " +
                    "fname=" + fName + "," +
                    "lname=" + lName + "," +
                    "username=" + userName + "," +
                    "password=" + passWord + " where userid=" + targetUserID;
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
     * Takes a User from the ShelterApplication and runs a hard-coded DELETE SQL statement to remove the entry from the
     *   database.
     *
     *  @param obj User to be removed
     */
    public void delete(User obj) {
        Connection connection = null;
        int idNum = obj.getID();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "delete from " + schemaName + ".users where userid=" + idNum;
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
