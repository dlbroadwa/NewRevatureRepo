package com.company.DAO.data;

import com.company.DAO.models.Item;
import com.company.DAO.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements Repository<Item, Integer> {
    private ConnectionUtils connectionUtils;
    public ItemRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public Item findByID(Integer integer) {
        return null;
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
    public void save(Item obj) {
    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public void updateByID(Integer integer) {

    }

}
