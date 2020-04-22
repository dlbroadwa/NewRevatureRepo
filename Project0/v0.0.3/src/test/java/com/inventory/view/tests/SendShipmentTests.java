package com.inventory.view.tests;

import com.inventory.controller.services.system.ConsoleIn;
import com.inventory.model.DcOrder;
import com.inventory.model.DcOrderItems;
import com.inventory.model.Shipment;
import com.inventory.model.Stockpile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class SendShipmentTests {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ConsoleIn consoleIn;

    @Mock DcOrder dcOrder;

    @Mock DcOrderItems dcOrderItems;

    @Test
    public void getNew(){
        //Create expected values
        int dcOrderIdTest = 1;
        int dcIdTest = 2;
        int itemIdTest = 3;
        int quantityTest = 4;
        int warehouseIdTest = 5;
        LocalDate dateTest = LocalDate.now();
        Stockpile stockpileTest = new Stockpile(warehouseIdTest, itemIdTest, quantityTest);
        DcOrder dcOrderTest = new DcOrder(dcOrderIdTest, warehouseIdTest, dcIdTest, dateTest);
        DcOrderItems dcOrderItemsTest = new DcOrderItems(dcOrderIdTest, itemIdTest, quantityTest);
        Shipment shipmentTest = new Shipment(stockpileTest, dcOrderTest, dcOrderItemsTest);


        //read from mocked console window, using exact same test values
        when(consoleIn.nextInt()).thenReturn(1);
        int dcOrderId = consoleIn.nextInt();
        when(consoleIn.nextLine()).thenReturn("\n");
        consoleIn.nextLine();   //this consumes the carriage return

        when(consoleIn.nextInt()).thenReturn(2);
        int dcId = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        when(consoleIn.nextInt()).thenReturn(3);
        int itemId = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        when(consoleIn.nextInt()).thenReturn(4);
        int quantity = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        when(consoleIn.nextInt()).thenReturn(5);
        int warehouseId = consoleIn.nextInt();
        consoleIn.nextLine();   //this consumes the carriage return

        //Create objects
        LocalDate date = LocalDate.now();

        //Assert that the objects equal the test objects
        Stockpile stockpile = new Stockpile(warehouseId, itemId, quantity);
        Assert.assertEquals(0, stockpile.compareTo(stockpileTest));

        DcOrder dcOrder = new DcOrder(dcOrderId, warehouseId, dcId, date);
        Assert.assertEquals(0, dcOrder.compareTo(dcOrderTest));

        DcOrderItems dcOrderItems = new DcOrderItems(dcOrderId, itemId, quantity);
        Assert.assertEquals(0, dcOrderItems.compareTo(dcOrderItemsTest));

        Shipment shipment = new Shipment(stockpile, dcOrder, dcOrderItems);
        Assert.assertEquals(0, shipment.compareTo(shipmentTest));
    }
}
