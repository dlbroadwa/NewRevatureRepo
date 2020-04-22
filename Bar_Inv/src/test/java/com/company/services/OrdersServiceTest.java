package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Order;
import com.company.DAO.models.User;
import org.junit.Before;
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
    List<Order> orders = new ArrayList();

    @Mock
    Repository<Order,String, String> repo;
    @Mock
    OrdersService mockedService;
    @InjectMocks
    OrdersService service;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new OrdersService(repo);
    }
    @Test
    public void shouldAddOrder() {
        Mockito.doNothing().when(mockedService).addOrder("bloke",101,4);

        mockedService.addOrder("bloke",101,4);

        Mockito.verify(mockedService, Mockito.times(1)).addOrder("bloke",101,4);

    }

    @Test
    public void shouldDisplayAllOrders() throws SQLException {
        Mockito.doNothing().when(mockedService).displayAllOrders();
        mockedService.displayAllOrders();
        Mockito.verify(mockedService, Mockito.times(1)).displayAllOrders();

    }

    @Test
    public void shouldDisplayOpenOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).displayOpenOrders();
        mockedService.displayOpenOrders();
        Mockito.verify(mockedService, Mockito.times(1)).displayOpenOrders();


    }

    @Test
    public void shouldMarkOrderComplete() {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).markOrderComplete("4");
        mockedService.markOrderComplete("4");
        Mockito.verify(mockedService, Mockito.times(1)).markOrderComplete("4");


    }

    @Test
    public void shouldDisplayUserOrders() throws SQLException {
        Mockito.doNothing().doThrow(new RuntimeException())
                .when(mockedService).displayUserOrders("bloke");
        mockedService.displayUserOrders("bloke");
        Mockito.verify(mockedService, Mockito.times(1)).displayUserOrders("bloke");


    }
}