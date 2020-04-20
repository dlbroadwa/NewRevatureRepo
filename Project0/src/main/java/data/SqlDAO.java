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
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 16 April 2020
 */
public class SqlDAO implements DAO {
    // Instance Variables
    private ArrayList<Item> items = null;
    private String url = null;
    private String username = null;
    private String password = null;
    private String defaultSchema = null;
    private ConnectionUtil connectionUtil = null;
    private Repository<Item, Integer> itemSQLRepo = null;

    // Constructor
//    public SqlDAO(String url, String username, String password, String defaultSchema) {
//        this.url = url;
//        this.username = username;
//        this.password = password;
//        this.defaultSchema = defaultSchema;
//        this.connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
//        this.itemSQLRepo = new ItemSQLRepository(connectionUtil);
//
//        items = itemSQLRepo.findAll();
//    }

    public SqlDAO(Repository<Item, Integer> itemSQLRepo) {
        this.itemSQLRepo = itemSQLRepo;
        items = itemSQLRepo.findAll();
    }

    // Getter Methods

    public ArrayList<Item> getItems() { return items; }

    public String getUrl() { return url; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getDefaultSchema() { return defaultSchema; }

    public ConnectionUtil getConnectionUtil() { return connectionUtil; }

    public Repository<Item, Integer> getItemSQLRepo() { return itemSQLRepo; }

    // Setter Methods

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    public void setItemSQLRepo(Repository<Item, Integer> itemSQLRepo) {
        this.itemSQLRepo = itemSQLRepo;
    }


    // Methods

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