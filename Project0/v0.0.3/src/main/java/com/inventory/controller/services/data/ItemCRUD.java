package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCRUD extends CRUD<Item>{
    private static final String SCHEMA_TABLE = "assistant.\"Item\" ";

    @Override
    public void create(int connIndex, @NotNull Item item) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + item.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
                statement.execute(sql);
        }
    }

    @Override
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

    @Override
    public void update(int connIndex, @NotNull Item item, @NotNull Item newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + item.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where id = " + item.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
             statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull Item item) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + item.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
