package com.company.DAO.data;

import com.company.DAO.models.Item;
import com.company.DAO.utils.ConnectionUtils;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements Repository<Item, Integer, String> {
    private ConnectionUtils connectionUtils;
    public ItemRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    public List<Item> compareColumns(String column1, String column2, String comparer){
        //this allows us to use the values of each entry in the inventory table to find a set of entries that meet certain requirements
        //inputs define what columns to look at and how they will be compared
        Connection conn = null;
        List<Item> low = new ArrayList(); //define a list of items, which will be returned
        try{
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
                        // select all the rows in inventory that have the relationship we define
            String sql = "select * from "+schemaName+".inventory where "+column1 + comparer + column2;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Item tmp = new Item();
                String name =rs.getString("itemname");
                tmp.setItemName(name);
                int id = rs.getInt("id");
                tmp.setId(id);
                int onhand = rs.getInt("onhand");
                tmp.setOnHand(onhand);
                int lowLevel = rs.getInt("lowlevel");
                tmp.setLowLevel(lowLevel);
                int optLevel = rs.getInt("optlevel");
                tmp.setOptLevel(optLevel);

                low.add(tmp); //fill the list with the objects
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return low; //give that list
    }

    @Override
    public Item findByID(Integer id) {
        //find the item that has the same ID number as the input (id is unique on the inventory table)
        Connection conn = null;
        Item oneItem = new Item();
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "select * from "+schemaName+".inventory where id="+ id;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                oneItem.setItemName(rs.getString("itemname"));
                oneItem.setId(rs.getInt("id"));
                oneItem.setOnHand(rs.getInt("onhand"));
                oneItem.setLowLevel(rs.getInt("lowlevel"));
                oneItem.setOptLevel(rs.getInt("optlevel"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return oneItem; //return that item
    }

    @Override
    public List<Item> findAll() throws SQLException {
        //get everything from the inventory table
        //return these items as a list of items
        Connection connection = null;
        List<Item> inventory = new ArrayList();
        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select itemname, id, onhand, lowlevel, optlevel from " + schemaName + ".inventory";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                String itemName = rs.getString("itemname");
                int id = rs.getInt("id");
                int onHand = rs.getInt("onhand");
                int lowLevel = rs.getInt("lowlevel");
                int optLevel = rs.getInt("optlevel");

                Item tmp = new Item();
                tmp.setItemName(itemName);
                tmp.setId(id);
                tmp.setLowLevel(lowLevel);
                tmp.setOnHand(onHand);
                tmp.setOptLevel(optLevel);

                inventory.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return inventory;
    }

    @Override
    public void save(Item obj) {
        //take a new item object, add it to the inventory table
        Connection conn = null;
        try{
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into "+schemaName+".inventory (itemname, id, onhand, lowlevel, optlevel) values " +
                    "('"+obj.getItemName()+"',"+obj.getId()+","+obj.getOnHand()+","+obj.getLowLevel()+","+obj.getOptLevel()+")";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteByID(Integer integer) {
        //given an id number, remove the associated item from the table
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "delete from "+schemaName+".inventory where id=" + integer;
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateByID(Item item) {
        //take the item object, and update the values
        //the id number is the constant when updating the item, so that id is used to define the entry on inventory to update
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "update "+schemaName+".inventory set itemname= '"+item.getItemName() + "', onhand =" + item.getOnHand()+
                    ", lowlevel =" + item.getLowLevel() + ", optlevel ="+ item.getOptLevel() +" where id=" + item.getId();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);


        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Item> findAllForName(Integer integer) throws SQLException {
        //don't need this for items, since the item id is unique
        return null;
    }




}
