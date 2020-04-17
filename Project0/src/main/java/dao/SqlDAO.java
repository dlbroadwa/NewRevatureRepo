package dao;

import models.Item;
import connections.ConnectionUtil;
import connections.PostgresConnectionUtil;
import models.Catalog;
import data.ItemSQLRepository;
import data.Repository;

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
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 16 April 2020
 */
public class SqlDAO implements DAO {
    // Instance Variables
    ArrayList<Item> items = null;
    String url = null;
    String username = null;
    String password = null;
    String defaultSchema = null;
    ConnectionUtil connectionUtil = null;
    Repository<Item, Integer> itemSQLRepo = null;

    // Constructor
    public SqlDAO(String url, String username, String password, String defaultSchema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = defaultSchema;
        this.connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
        this.itemSQLRepo = new ItemSQLRepository(connectionUtil);

        items = itemSQLRepo.findAll();
    }

    public ArrayList<Item> getContent() {
        return items;
    }

    public void addItem(Item obj) { itemSQLRepo.save(obj); }

    public void removeItem(Item obj) { itemSQLRepo.delete(obj); }

    public void updateCheck(Item obj, int checkBit) {
        itemSQLRepo.update(obj,checkBit);
    }

    public void recordData(Catalog catalog) {} // Do nothing for now
}
