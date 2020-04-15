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
import java.util.List;

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

    public ArrayList<Item> findAll() {
        Connection connection = null;
        ArrayList<Item> items = new ArrayList<>();

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
                String title = rs.getString("title");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");
                int year = rs.getInt("itemyear");
                
                if (itemType.equals("D")) {
                    String language = rs.getString("dictlanguage");
                    int wordCount = rs.getInt("dictwordcount");
                    temp = new Dictionary(idNum,title,author,publisher,year,language,wordCount);
                } else if (itemType.equals("N")) {
                    String genre = rs.getString("novlgenre");
                    temp = new Novel(idNum,title,author,publisher,year,genre);
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

    }

    public void delete(Item obj) {

    }
}
