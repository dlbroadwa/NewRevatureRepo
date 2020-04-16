package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;
import com.company.DAO.models.User;
import org.junit.Assert;
import org.junit.Before;
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

public class ItemServiceTest {
    ItemService service;
    List<Item> items = new ArrayList();

    @Mock
    Repository<Item,Integer> repo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new ItemService(repo);
        Item tmp = new Item();
        tmp.setOptLevel(17);
        tmp.setOnHand(4);
        tmp.setLowLevel(10);
        tmp.setId(107);
        tmp.setItemName("triple sec");
        items.add(tmp);
    }
    @Test
    public void shouldGetAllUsers() throws SQLException {
        //ask for all the items
        //assert that all items are returned
        Mockito.when(repo.findAll()).thenReturn(items);
        List<Item> actual = service.getAllItems();
        Assert.assertArrayEquals(items.toArray(),actual.toArray());
    }

    @Test
    public void itemByID() {
    }

    @Test
    public void addItem() {
    }

    @Test
    public void removeItem() {
    }

    @Test
    public void updateItem() {
    }
}