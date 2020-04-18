package com.inventory.app;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.ItemCRUD;
import com.inventory.controller.services.data.WarehouseCRUD;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.Item;
import com.inventory.model.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //establish connection
        try {
            PostgresSQLService.addDBConnection("jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/db1",
                    "davide", "jw8s0F4");

            ConsoleOut.println("Established db connection.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to db. Program ends.");
            System.exit(1);
        }

        //ItemCRUD tests
        System.out.println("Beginning ItemCRUD examples");
        ItemCRUD itemCRUD = new ItemCRUD();
        List testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        Item testItem = new Item(44, "test", 4234.23, (short) 34);
        try {
            itemCRUD.create(0, testItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Item) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        Item secondTestItem = new Item(20, "testtest", 20.00, (short) 20);
        try {
            itemCRUD.update(0, testItem, secondTestItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Item) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            itemCRUD.delete(0, secondTestItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = itemCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Item) i).toSQLString());

        //WarehouseCRUD tests
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning WarehouseCRUD examples");
        WarehouseCRUD warehouseCRUD = new WarehouseCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = warehouseCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        Warehouse testWarehouse1 = new Warehouse(444, "TX", "Nowhere", "5234 Nowhere ln", 99999);
        try {
            warehouseCRUD.create(0, testWarehouse1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = warehouseCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Warehouse) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        Warehouse testWarehouse2 = new Warehouse(000, "FL", "Nowhere!", "9934 Nowhere ln", 00000);
        try {
            warehouseCRUD.update(0, testWarehouse1, testWarehouse2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = warehouseCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Warehouse) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            warehouseCRUD.delete(0, testWarehouse2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = warehouseCRUD.read(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Warehouse) i).toSQLString());
    }
}
