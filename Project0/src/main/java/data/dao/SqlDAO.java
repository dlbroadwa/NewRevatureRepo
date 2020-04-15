package data.dao;

import book.Item;
import connections.ConnectionUtil;
import connections.PostgresConnectionUtil;
import data.Catalog;
import data.repos.ItemSQLRepository;
import data.repos.Repository;

import java.util.ArrayList;
import java.util.List;

public class SqlDAO implements DAO {
    // Instance Variables
    ArrayList<Item> items = null;
    String url = null;
    String username = null;
    String password = null;
    String defaultSchema = null;

    // Constructor
    public SqlDAO(String url, String username, String password, String defaultSchema) {
        ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
        Repository<Item, Integer> itemSQLRepo = new ItemSQLRepository(connectionUtil);

        items = itemSQLRepo.findAll();
    }

    public void recordData(Catalog catalog) {

    }

    public ArrayList<Item> getContent() {
        return items;
    }
}
