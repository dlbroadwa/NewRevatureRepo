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
        Connection conn = null;
        List<Item> low = new ArrayList();
        try{
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sql = "select * from "+schemaName+".inventory where "+column1 + comparer + column2;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Item tmp = new Item();
                tmp.setItemName(rs.getString("itemname"));
                tmp.setId(rs.getInt("id"));
                tmp.setOnHand(rs.getInt("onhand"));
                tmp.setLowLevel(rs.getInt("lowlevel"));
                tmp.setOptLevel(rs.getInt("optlevel"));

                low.add(tmp);

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
        return low;
    }
    @Override
    public Item findByID(Integer id) {
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
        return oneItem;
    }

    @Override
    public List<Item> findAll() throws SQLException {
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
    public List<Item> findAllForName(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void save(Item obj) {
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
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "delete from "+schemaName+".inventory where id=" + integer;

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
        Connection conn = null;
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "update "+schemaName+".inventory set itemname= '"+item.getItemName() + "', onhand =" + item.getOnHand()+
                    ", lowlevel =" + item.getLowLevel() + ", optlevel ="+ item.getOptLevel() +" where id=" + item.getId();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //update works by deleting the old entry with the specified id number, then adding a new item
//        Integer integer = item.getId();
//        deleteByID(integer);
//        save(item);
    }

}
