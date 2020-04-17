package data;

import models.Dictionary;
import models.Item;
import models.Novel;
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
 *    contents and will run most of the background activity as commissioned through user input in the app.Menu class.
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
 *                                            Implemented update for use of checking Items in/out using an Integer
 *                                              parameter to represent T/F through bit values 1 and 0 respectively.
 * <br>
 *     17 April 2020, Barthelemy Martinon,    Implemented save for use of adding Items into the database.
 *                                            Implemented delete for use of removing Items from the database.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 17 April 2020
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
     *   for app.Menu interaction.
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

    /*
     * Takes a newly created Item from the Catalog, and runs a hard-coded INSERT SQL statement to add the Item into the
     *   database as a table entry.
     * Information found within the Item given as input is collected and converted into the necessary values needed to
     *   complete the INSERT statement.
     *
     *  @param obj New Item created by user
     *
     * 	@return statement.executeUpdate(sql) Integer value representing the amount of rows affected by the statement.
     */
    public Integer save(Item obj) {
        Connection connection = null;

        // Extract all information from Item instance to be stored as values for the new table entry
        // Base Item Info
        String idNum = "'" + obj.getID() + "'";

        boolean checkStatusBool = obj.getCheckStatus();
        int checkStatusBit;
        if (checkStatusBool) {
            checkStatusBit = 1;
        } else {
            checkStatusBit = 0;
        }
        String checkStatus = "'"+ checkStatusBit + "'";

        String title = "'" + obj.getTitle() + "'";
        String author = "'" + obj.getAuthor() + "'";
        String publisher = "'" + obj.getPublisher() + "'";
        String itemyear = "'" + obj.getYear() + "'";

        // Subclass Info
        String itemtype = null;
        String dictlanguage = null;
        String dictwordcount = null;
        String novlgenre = null;

        if ( obj instanceof Dictionary ) {
            itemtype = "'D'";
            dictlanguage = "'" + ((Dictionary) obj).getLangauge() + "'";
            dictwordcount = "'" + ((Dictionary) obj).getWordCount() + "'";
        } else if ( obj instanceof Novel ) {
            itemtype = "'N'";
            novlgenre = "'" + ((Novel) obj).getGenre() + "'";
        }

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "Insert into " + schemaName + ".itemcatalog (itemtype, idnum, checkstatus, title, author, " +
                    "publisher, itemyear, dictlanguage, dictwordcount, novlgenre) values " +
                    "(" + itemtype + ", " + idNum + ", " + checkStatus + ", " + title + ", " + author +
                    ", " + publisher + ", " + itemyear + ", " + dictlanguage + ", "  + dictwordcount + ", " +
                    novlgenre + ")";
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
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

    /*
     * Takes an Item from the Catalog and an Integer that reprsents either true or false (treated as a bit) and runs a
     *   hard-coded UPDATE SQL statement to set the Item entry's checkstatus field to the appropriate bit.
     * Using 0 as for the Integer is equivalent to setting checkStatus to False, while using 1 sets checkStatus to true.
     *
     *  @param newObj Item to be checked in/out
     *  @param integer Integer value representing bit value 0 (false) or 1 (true)
     */
    public void update(Item newObj, Integer integer) {
        Connection connection = null;
        int checkStatusBit = integer;

        // Check for discrepancies and abort process if needed
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

    /*
     * Takes an Item from the Catalog and runs a hard-coded DELETE SQL statement to remove the entry from the database.
     *
     *  @param obj Item to be removed
     */
    public void delete(Item obj) {
        Connection connection = null;
        int idNum = obj.getID();

        try {
            connection = connectionUtil.getConnection();
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "delete from " + schemaName + ".itemcatalog where idnum=" + idNum;
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
}
