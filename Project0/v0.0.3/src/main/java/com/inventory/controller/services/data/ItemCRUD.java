package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCRUD {
    private static final String SCHEMA_TABLE = "assistant.\"Item\" ";

    public void create(int connIndex, @NotNull Item obj) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + obj.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
                statement.execute(sql);
        }
    }

    public List<Item> read(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
            )
        {
            List<Item> itemList = new ArrayList<>();
            while (rs.next()) {
                itemList.add(new Item(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("value"), rs.getShort("shelfLife"))
                );
            }
            return itemList;
        }
    }

    public void update(int connIndex, @NotNull Item oldObj, @NotNull Item newObj) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + oldObj.getSQLColumnFormat() + "= " +
                newObj.toSQLString() + "where id = " + oldObj.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
             statement.execute(sql);
        }
    }

    public void delete(int connIndex, @NotNull Item obj) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + obj.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
