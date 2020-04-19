package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.DcOrder;
import com.inventory.model.Item;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DcOrderCRUD extends CRUD<DcOrder> {
    private static final String SCHEMA_TABLE = "assistant.\"DcOrder\" ";

    @Override
    public void create(int connIndex, @NotNull DcOrder dcOrder) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + dcOrder.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public List<DcOrder> read(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            List<DcOrder> dcOrderList = new ArrayList<>();
            while (rs.next()) {
                dcOrderList.add(new DcOrder(
                        rs.getInt("warehouseId"), rs.getInt("dcId"),
                        rs.getDate("date").toLocalDate(), rs.getInt("id"))
                );
            }
            return dcOrderList;
        }
    }

    @Override
    public void update(int connIndex, @NotNull DcOrder dcOrder, @NotNull DcOrder newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + dcOrder.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where id = " + dcOrder.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull DcOrder dcOrder) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + dcOrder.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
