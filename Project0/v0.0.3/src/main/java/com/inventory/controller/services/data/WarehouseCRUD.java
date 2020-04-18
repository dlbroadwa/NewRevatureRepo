package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.Warehouse;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WarehouseCRUD extends CRUD<Warehouse>{
    private static final String SCHEMA_TABLE = "assistant.\"Warehouse\" ";

    @Override
    public void create(int connIndex, @NotNull Warehouse warehouse) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + warehouse.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
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

    @Override
    public void update(int connIndex, @NotNull Warehouse warehouse, @NotNull Warehouse newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + warehouse.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where id = " + warehouse.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull Warehouse warehouse) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + warehouse.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
