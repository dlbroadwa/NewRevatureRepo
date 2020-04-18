package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.Stockpile;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockpileCRUD extends CRUD<Stockpile>{
    private static final String SCHEMA_TABLE = "assistant.\"Stockpile\" ";

    @Override
    public void create(int connIndex, @NotNull Stockpile stockpile) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + stockpile.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public List<Stockpile> read(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            List<Stockpile> stockpiles = new ArrayList<>();
            while (rs.next()) {
                stockpiles.add(new Stockpile(
                        rs.getInt("warehouseId"), rs.getInt("itemId"),
                        rs.getInt("quantity")
                        )
                );
            }
            return stockpiles;
        }
    }

    @Override
    public void update(int connIndex, @NotNull Stockpile stockpile, @NotNull Stockpile newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + stockpile.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where \"warehouseId\" = " + stockpile.getWarehouseId() + " AND " +
                "\"itemId\" = " + stockpile.getItemId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull Stockpile stockpile) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where \"warehouseId\" = " + stockpile.getWarehouseId() + " AND" +
                " \"itemId\" = " + stockpile.getItemId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
