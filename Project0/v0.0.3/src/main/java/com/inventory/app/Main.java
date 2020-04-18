package com.inventory.app;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.ItemCRUD;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //establish connection
        try{
            PostgresSQLService.addDBConnection("jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/db1",
                    "davide", "jw8s0F4");

            ConsoleOut.println("Established db connection.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to db. Program ends.");
            System.exit(1);
        }
        ItemCRUD itemCRUD = new ItemCRUD();
        List<Item> testList = null;

        System.out.println("");
        System.out.println("read and print the current item list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Item i: testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test item");
        Item testItem = new Item(44, "test", 4234.23, (short) 34);
        try {
            itemCRUD.create(0,testItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added item list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Item i: testList)
            ConsoleOut.println(i.toSQLString());

        System.out.println("");
        System.out.println("update the value of the test item");
        Item secondTestItem = new Item(20, "testtest", 20.00, (short)20);
        try {
            itemCRUD.update(0, testItem, secondTestItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated item list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Item i: testList)
            ConsoleOut.println(i.toSQLString());

        System.out.println("");
        System.out.println("delete the updated test item");
        try{
            itemCRUD.delete(0, secondTestItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted item, item list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Item i: testList)
            ConsoleOut.println(i.toSQLString());
    }
}
