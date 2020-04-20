package com.inventory.app;

import com.inventory.controller.services.connect.PostgresSQLService;
import com.inventory.controller.services.data.*;
import com.inventory.controller.system.ConsoleOut;
import com.inventory.model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExampleApplication extends Application{

    ExampleApplication() {
        super("Examples App");
    }

    @Override
    public void run(){
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

        //ItemCRUD examples
        System.out.println("Beginning ItemCRUD examples");
        ItemCRUD itemCRUD = new ItemCRUD();
        List testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = itemCRUD.readAll(0);
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
            testList = itemCRUD.readAll(0);
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
            testList = itemCRUD.readAll(0);
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
            testList = itemCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Item) i).toSQLString());

        //WarehouseCRUD examples
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning WarehouseCRUD examples");
        WarehouseCRUD warehouseCRUD = new WarehouseCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = warehouseCRUD.readAll(0);
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
            testList = warehouseCRUD.readAll(0);
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
            testList = warehouseCRUD.readAll(0);
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
            testList = warehouseCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Warehouse) i).toSQLString());

        //StockpileCRUD examples
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning StockpileCRUD examples");
        StockpileCRUD stockpileCRUD = new StockpileCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = stockpileCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        Stockpile stockpile1 = new Stockpile(1, 2, 44);
        try {
            stockpileCRUD.create(0, stockpile1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = stockpileCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Stockpile) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        Stockpile stockpile2 = new Stockpile(1, 2, 22);
        try {
            stockpileCRUD.update(0, stockpile2, stockpile2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = stockpileCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Stockpile) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            stockpileCRUD.delete(0, stockpile2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = stockpileCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((Stockpile) i).toSQLString());

        //DistributionCenterCRUD examples
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning DistributionCenterCRUD examples");
        DistributionCenterCRUD distributionCenterCRUD = new DistributionCenterCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = distributionCenterCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        DistributionCenter distributionCenter1 = new DistributionCenter(222, "MI", "RiverMill",
                "2345 RiverMill ln", 43465);
        try {
            distributionCenterCRUD.create(0, distributionCenter1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = distributionCenterCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DistributionCenter) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        DistributionCenter distributionCenter2 = new DistributionCenter(222, "OK", "RiverMiller",
                "2345 RiverMiller ln", 32464);
        try {
            distributionCenterCRUD.update(0, distributionCenter1, distributionCenter2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = distributionCenterCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DistributionCenter) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            distributionCenterCRUD.delete(0, distributionCenter1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = distributionCenterCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DistributionCenter) i).toSQLString());


        //DcOrderCRUD examples
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning DcOrderCRUD examples");
        DcOrderCRUD dcOrderCRUD = new DcOrderCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = dcOrderCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        DcOrder dcOrder1 = new DcOrder(1, 1, LocalDate.of(2008, 3, 3), 5 );
        try {
            dcOrderCRUD.create(0, dcOrder1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = dcOrderCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrder) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        DcOrder dcOrder2 = new DcOrder(1, 1, LocalDate.of(2009, 3, 3), 6 );
        try {
            dcOrderCRUD.update(0, dcOrder1, dcOrder2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = dcOrderCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrder) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            dcOrderCRUD.delete(0, dcOrder2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = dcOrderCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrder) i).toSQLString());

        //DcOrderItemsCRUD examples
        System.out.println("");
        System.out.println("-------------------------------");
        System.out.println("Beginning DcOrderItemsCRUD examples");
        DcOrderItemsCRUD dcOrderItemsCRUD = new DcOrderItemsCRUD();
        testList = null;

        System.out.println("");
        System.out.println("read and print the current list");
        try {
            testList = dcOrderItemsCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(i.toString());

        System.out.println("");
        System.out.println("add a new test obj");
        DcOrderItems dcOrderItems1 = new DcOrderItems(1, 3, 55);
        try {
            dcOrderItemsCRUD.create(0, dcOrderItems1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the added obj list");
        try {
            testList = dcOrderItemsCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrderItems) i).toSQLString());

        System.out.println("");
        System.out.println("update the value of the test obj");
        DcOrderItems dcOrderItems2 = new DcOrderItems(1, 3, 89);
        try {
            dcOrderItemsCRUD.update(0, dcOrderItems1, dcOrderItems2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the updated obj list");
        try {
            testList = dcOrderItemsCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrderItems) i).toSQLString());

        System.out.println("");
        System.out.println("delete the updated test obj");
        try {
            dcOrderItemsCRUD.delete(0, dcOrderItems2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("read and print the deleted obj, obj list");
        try {
            testList = dcOrderItemsCRUD.readAll(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Object i : testList)
            ConsoleOut.println(((DcOrderItems) i).toSQLString());
    }
}
