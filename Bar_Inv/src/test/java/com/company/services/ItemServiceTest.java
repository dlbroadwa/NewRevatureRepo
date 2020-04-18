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
    Repository<Item,Integer, String> repo;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new ItemService(repo);
        Item tmp = new Item();
        Item tmp3 = new Item();
        tmp.setOptLevel(17);
        tmp.setOnHand(4);
        tmp.setLowLevel(10);
        tmp.setId(107);
        tmp.setItemName("triple sec");
        tmp3.setOptLevel(5);
        tmp3.setLowLevel(3);
        tmp3.setOnHand(1);
        tmp3.setId(115);
        tmp3.setItemName("grand marnier");
        items.add(tmp);
        items.add(tmp3);
    }
    @Test
    public void shouldGetAllItems() throws SQLException {
        //ask for all the items
        //assert that all items are returned
        Mockito.when(repo.findAll()).thenReturn(items);
        service.getAllItems();
    }
    @Before
    public void test1(){
        service = new ItemService(repo);
        Item tmp = new Item();
        Item tmp3 = new Item();
        tmp.setOptLevel(17);
        tmp.setOnHand(4);
        tmp.setLowLevel(10);
        tmp.setId(107);
        tmp.setItemName("triple sec");
        tmp3.setOptLevel(5);
        tmp3.setLowLevel(3);
        tmp3.setOnHand(1);
        tmp3.setId(115);
        tmp3.setItemName("grand marnier");
        items.add(tmp);
        items.add(tmp3);
    }

    @Test
    public void shouldGetItemByID() {
        //ask for one item by the id
        //assert that the correct item is retrieved
        Item tmp3 = new Item();
        tmp3.setOptLevel(5);
        tmp3.setLowLevel(3);
        tmp3.setOnHand(1);
        tmp3.setId(115);
        tmp3.setItemName("grand marnier");
        Mockito.when(repo.findByID(115)).thenReturn(tmp3);
        Item actual = service.itemByID(115);
        Assert.assertSame(tmp3,actual);

    }

    @Test
    public void shouldReturnOrderSoon(){


    }

    @Test
    public void shouldReturnOrderNow() {
    }

    @Test
    public void shouldReturnBackOrderItems() {
    }



    @Test
    public void updateItem() {
    }
}