package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.Warehouse;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WarehouseCRUD {
    private static final String SCHEMA_TABLE = "assistant.\"Warehouse\" ";

    public void create(int connIndex, @NotNull Warehouse obj) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + obj.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    public List<Warehouse> read(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            List<Warehouse> warehouses = new ArrayList<>();
            while (rs.next()) {
                warehouses.add(new Warehouse(
                        rs.getInt("id"), rs.getString("state"),
                        rs.getString("city"), rs.getString("address"),
                        rs.getInt("zipCode"))
                );
            }
            return warehouses;
        }
    }

    public void update(int connIndex, @NotNull Warehouse oldObj, @NotNull Warehouse newObj) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + oldObj.getSQLColumnFormat() + "= " +
                newObj.toSQLString() + "where id = " + oldObj.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    public void delete(int connIndex, @NotNull Warehouse obj) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + obj.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
