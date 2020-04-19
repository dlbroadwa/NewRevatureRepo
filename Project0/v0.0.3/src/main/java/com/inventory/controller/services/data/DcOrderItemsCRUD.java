package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.DcOrderItems;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DcOrderItemsCRUD extends CRUD<DcOrderItems> {
    private static final String SCHEMA_TABLE = "assistant.\"DcOrderItems\" ";

    @Override
    public void create(int connIndex, @NotNull DcOrderItems dcOrderItems) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + dcOrderItems.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public List<DcOrderItems> read(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            List<DcOrderItems> dcOrderItems = new ArrayList<>();
            while (rs.next()) {
                dcOrderItems.add(new DcOrderItems(
                                rs.getInt("orderId"), rs.getInt("itemId"),
                                rs.getInt("quantity")
                        )
                );
            }
            return dcOrderItems;
        }
    }

    @Override
    public void update(int connIndex, @NotNull DcOrderItems dcOrderItems, @NotNull DcOrderItems newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + dcOrderItems.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where \"orderId\" = " + dcOrderItems.getOrderId() + " AND " +
                "\"itemId\" = " + dcOrderItems.getItemId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull DcOrderItems dcOrderItems) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where \"orderId\" = " + dcOrderItems.getOrderId() + " AND" +
                " \"itemId\" = " + dcOrderItems.getItemId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
