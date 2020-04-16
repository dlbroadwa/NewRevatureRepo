package data.repos;

import book.Dictionary;
import book.Item;
import book.Novel;
import connections.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *  Project 0:<br>
 * <br>
 *  The ItemSQLRepository class serves to collect, manipulate and persist data brought in from a AWS RDB hosted
 *    Postgresql database used as the main method of data storage for Project 0's local library cataloging system.
 *  This Repository subclass is the main force behind the collection, manipulation and persisting of the database
 *    contents and will run most of the background activity as commissioned through user input in the Menu class.
 *  This will only work with Items and integers as a primary key.
 *
 *  <br> <br>
 *  Created: <br>
 *     15 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     15 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented findAll to be used to collect all information found on the
 *     										    database to be added into the Catalog.
 * <br>
 *     16 April 2020, Barthelemy Martinon,    Updated code to include Item's new checkStatus constructor parameter.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 16 April 2020
 */
public class ItemSQLRepository implements Repository<Item, Integer> {
    // Instance Variables
    private ConnectionUtil connectionUtil;

    // Constructor
    public ItemSQLRepository(ConnectionUtil connectionUtil) {
        if(connectionUtil != null) {
            this.connectionUtil = connectionUtil;
        }

    }

    public Item findById(Integer integer) {
        return null;
    }

    /*
     * Takes the database content, runs a hard-coded SELECT SQL query to obtain all entries.
     * Entries are scanned for a specific value under itemType to be "translated" into an instance of the appropraite
     *   Item subclass with the basic Item information and specialized subclass information as parameter inputs.
     * All "translated" entries are put into an ArrayList of items, which is returned to fill the Catalog instance
     *   for Menu interaction.
     * Used when a new SqlDAO instance is created.
     *
     * 	@return items Item ArrayList of all database rows.
     */
    public ArrayList<Item> findAll() {
        Connection connection = null;
        ArrayList<Item> items = new ArrayList<Item>();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Select * from " + schemaName + ".itemcatalog";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                Item temp = null;
                String itemType = rs.getString("itemtype");
                int idNum = rs.getInt("idnum");

                int checkStatusBit = rs.getInt("checkstatus");
                boolean checkStatus;
                if ( checkStatusBit == 1 ) {
                    checkStatus = true;
                } else {
                    checkStatus = false;
                }

                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("itemyear");
                
                if (itemType.equals("D")) {
                    String language = rs.getString("dictlanguage");
                    int wordCount = rs.getInt("dictwordcount");
                    temp = new Dictionary(idNum,checkStatus,title,author,publisher,year,language,wordCount);
                } else if (itemType.equals("N")) {
                    String genre = rs.getString("novlgenre");
                    temp = new Novel(idNum,checkStatus,title,author,publisher,year,genre);
                }
                items.add(temp);
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
        return items;
    }

    public Integer save(Item obj) {
        return null;
    }

    public void update(Item newObj, Integer integer) {
        Connection connection = null;
        int checkStatusBit = integer;

        if (newObj.getCheckStatus() && checkStatusBit == 1) {
            System.out.println("Cannot perform status update. Already checked in.");
            return;
        } else if (!newObj.getCheckStatus() && checkStatusBit == 0) {
            System.out.println("Cannot perform status update. Already checked out.");
            return;
        } else if (checkStatusBit < 0 || checkStatusBit > 1) {
            System.out.println("Cannot perform status update. Invalid int for bit. Use 0 for false, 1 for true.");
            return;
        }

        int idNum = newObj.getID();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Update " + schemaName + ".itemcatalog set checkstatus=cast(" + checkStatusBit
                    + "as bit) where idnum=" + idNum;
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);

            System.out.println("Rows affected: " + rowsAffected);

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

    public void delete(Item obj) {

    }
}
