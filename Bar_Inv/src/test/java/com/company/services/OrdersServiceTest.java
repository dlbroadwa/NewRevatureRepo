package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersServiceTest {
    OrdersService service;
    List<Order> orders = new ArrayList();

    @Mock
    Repository<User,String, String> repo;
    @Mock
    OrdersService mockedService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void shouldAddOrder() {
        Mockito.doNothing().
                doThrow(new RuntimeException())
                .when(mockedService).addOrder("bloke",101,4);

        mockedService.addOrder("bloke",101,4);

    }

    @Test
    public void shouldDisplayAllOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).displayAllOrders();
        mockedService.displayAllOrders();
    }

    @Test
    public void shouldDisplayOpenOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).displayOpenOrders();
        mockedService.displayOpenOrders();

    }

    @Test
    public void shouldMarkOrderComplete() {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).markOrderComplete("4");
        mockedService.markOrderComplete("4");

    }

    @Test
    public void shouldDisplayUserOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).displayUserOrders("bloke");
        mockedService.displayUserOrders("bloke");

    }
}