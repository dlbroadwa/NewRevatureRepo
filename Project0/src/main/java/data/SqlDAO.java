package data;

import models.Item;
import connections.ConnectionUtil;
import connections.PostgresConnectionUtil;
import repos.ItemSQLRepository;
import repos.Repository;
import services.Catalog;

import java.util.ArrayList;

/**
 *  Project 0:<br>
 * <br>
 *  The SqlDAO class serves as a means to obtain Item information from a persistent SQL database instance hosted on
 *    AWS RDB to allow for the catalog to persist between sessions in terms of changes in favor over local file storage.
 *  The SqlDAO as of 4/15/2020 will be designed to communicate with a Postgresql server hosted on AWS RDB.
 *
 *  <br> <br>
 *  Created: <br>
 *     15 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     15 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented database input upon SqlDAO instance creation via
 *     										    a persistent repository (Repository interface + ItemSQLRepository class),
 *     										    java.sql.DriverManager and java.sql.Connection instances within the
 *     										    PostgresConnectionUtil class.
 * <br>
 *     16 April 2020, Barthelemy Martinon,    Updated code to include Item's new checkStatus constructor parameter.
 * <br>
 *     20 April 2020, Barthelemy Martinon,    Reworked constructor to take a Repository instance as a parameter to allow
 *      										SqlDAO instance to take on specific configurations by specifying what
 *                                              Repository instance it should take on when running LibraryApplication.
 *                                              Taking a Repository as a parameter will allow us to configure SqlDAO
 *                                              to obtain information from that Repository's database connection.
 *                                            Removed invalidated instance variables, getters and setters from having
 *                                              phased out the old constructor.
 * <br>
 *     21 April 2020, Barthelemy Martinon,    Removed redundant Item list variable (along with getter and setter) over
 *                                              fears of desynchronization with database.
 *                                            Implemented getItem to run ItemSQLRepository's recently implemented
 *                                              findByID method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 21 April 2020
 */
public class SqlDAO implements DAO {
    // Instance Variables
    private Repository<Item, Integer> itemSQLRepo = null;

    // Constructor
    public SqlDAO(Repository<Item, Integer> itemSQLRepo) {
        this.itemSQLRepo = itemSQLRepo;
    }

    // Getter Methods

    public Repository<Item, Integer> getItemSQLRepo() { return itemSQLRepo; }

    // Setter Methods

    public void setItemSQLRepo(Repository<Item, Integer> itemSQLRepo) {
        this.itemSQLRepo = itemSQLRepo;
    }


    // Methods

    public ArrayList<Item> getContent() { return itemSQLRepo.findAll(); }

    public Item getItem(int i) { return itemSQLRepo.findById(i); }

    public void addItem(Item obj) { itemSQLRepo.save(obj); }

    public void removeItem(Item obj) { itemSQLRepo.delete(obj); }

    public void updateCheck(Item obj, int checkBit) { itemSQLRepo.update(obj,checkBit); }

    public void recordData(Catalog catalog) {} // Do nothing for now
}