package com.inventory.controller.services.data;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.model.DistributionCenter;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DistributionCenterCRUD extends CRUD<DistributionCenter>{
    private static final String SCHEMA_TABLE = "assistant.\"DistributionCenter\" ";

    @Override
    public void create(int connIndex, @NotNull DistributionCenter distributionCenter) throws SQLException {
        String sql = "insert into " + SCHEMA_TABLE + "values " + distributionCenter.toSQLString();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public List<DistributionCenter> readAll(int connIndex) throws SQLException {
        String sql = "select * from " + SCHEMA_TABLE;
        try (
                Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();
                ResultSet rs = statement.executeQuery(sql);
        )
        {
            List<DistributionCenter> distributionCenters = new ArrayList<>();
            while (rs.next()) {
                distributionCenters.add(new DistributionCenter(
                        rs.getInt("id"), rs.getString("state"),
                        rs.getString("city"), rs.getString("address"),
                        rs.getInt("zipCode"))
                );
            }
            return distributionCenters;
        }
    }

    @Override
    public void update(int connIndex, @NotNull DistributionCenter distributionCenter, @NotNull DistributionCenter newT) throws SQLException {
        String sql = "update " + SCHEMA_TABLE + "set " + distributionCenter.getSQLColumnFormat() + "= " +
                newT.toSQLString() + "where id = " + distributionCenter.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }

    @Override
    public void delete(int connIndex, @NotNull DistributionCenter distributionCenter) throws SQLException {
        String sql = "delete from " + SCHEMA_TABLE + "where id = " + distributionCenter.getId();
        try (Statement statement = PostgresSQLService.getConnection(connIndex).createStatement();)
        {
            statement.execute(sql);
        }
    }
}
