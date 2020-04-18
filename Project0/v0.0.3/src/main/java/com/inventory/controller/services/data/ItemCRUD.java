package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemCRUD {
    private static final String SCHEMA_TABLE = "assistant.\"Item\" ";

    public void create(int connIndex, @NotNull Item item) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + item.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
                statement.execute(sql);
        }
    }

    public List<Item> read(int connIndex) throws SQLException {
        String sql = "select * from assistant.\"Item\"";
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

    public void update(int connIndex, @NotNull Item itemToUpdate, @NotNull Item replacementItem) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set (id, \"name\", value, \"shelfLife\") = " +
                replacementItem.toSQLString() + "where id = " + itemToUpdate.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
             statement.execute(sql);
        }
    }

    public void delete(int connIndex, @NotNull Item item) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + item.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
