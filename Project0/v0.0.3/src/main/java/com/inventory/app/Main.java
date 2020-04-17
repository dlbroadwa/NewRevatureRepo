package com.inventory.app;

import com.inventory.controller.services.sql.connect.PostgresSQLService;
import com.inventory.controller.system.ConsoleOut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try{
            PostgresSQLService.addDBConnection("jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/db1",
                    "davide", "jw8s0F4");

            ConsoleOut.println("Established db connection.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to db. Program ends.");
            System.exit(1);
        }

        try {
            String schemaName = "assistant";
            String sql = "Select * from assistant.\"Item\"";
            Statement statement = PostgresSQLService.getConnection(0).createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.toString());
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getDouble("value"));
                System.out.println(rs.getShort("shelfLife"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(PostgresSQLService.getConnection(0) != null) {
                try {
                    PostgresSQLService.getConnection(0).close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
