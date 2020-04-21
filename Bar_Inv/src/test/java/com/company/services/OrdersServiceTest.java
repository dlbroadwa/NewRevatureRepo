package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersServiceTest {

    @Mock
    Repository<Order,String, String> repo;
    @InjectMocks
    OrdersService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void shouldAddOrder() {
        Order tmp = new Order();
        tmp.setItemID(101);
        tmp.setQuantity(4);
        tmp.setCustomerName("bloke");
        Mockito.doNothing().when(repo).save(tmp);

        service.addOrder("bloke",101,4);

        Mockito.verify(repo, Mockito.times(1)).save(tmp);

    }

    @Test
    public void shouldDisplayAllOrders() throws SQLException {
        Mockito.doNothing().when(service).displayAllOrders();
        service.displayAllOrders();
        Mockito.verify(service, Mockito.times(1)).displayAllOrders();

    }

    @Test
    public void shouldDisplayOpenOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(service).displayOpenOrders();
        service.displayOpenOrders();
        Mockito.verify(service, Mockito.times(1)).displayOpenOrders();


    }

    @Test
    public void shouldMarkOrderComplete() {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(service).markOrderComplete("4");
        service.markOrderComplete("4");
        Mockito.verify(service, Mockito.times(1)).markOrderComplete("4");


    }

    @Test
    public void shouldDisplayUserOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(service).displayUserOrders("bloke");
        service.displayUserOrders("bloke");
        Mockito.verify(service, Mockito.times(1)).displayUserOrders("bloke");


    }
}