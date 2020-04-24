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
    @Mock
    ItemService mockedService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
        service = new ItemService(repo);
        Item tmp = new Item();
        Item tmp2 = new Item();
        Item tmp3 = new Item();
        tmp.setItemName("triple sec");
        tmp.setId(107);
        tmp.setOnHand(24);
        tmp.setLowLevel(10);
        tmp.setOptLevel(17);
        tmp2.setItemName("sambuca");
        tmp2.setId(118);
        tmp2.setOnHand(-1);
        tmp2.setLowLevel(4);
        tmp2.setOptLevel(6);
        tmp3.setItemName("grand marnier");
        tmp3.setId(115);
        tmp3.setOnHand(1);
        tmp3.setLowLevel(3);
        tmp3.setOptLevel(5);

        items.add(tmp);
        items.add(tmp2);
        items.add(tmp3);
    }

    @Test
    public void shouldGetAllItems() throws SQLException {
        //ask for all the items
        //assert that all items are returned
        Mockito.when(repo.findAll()).thenReturn(items);
        int actual = service.getAllItems();
        Assert.assertEquals(1,actual);
//        Mockito.doNothing().when(mockedService).getAllItems();
//        mockedService.getAllItems();
//        Mockito.verify(mockedService,Mockito.times(1)).getAllItems();

//        Mockito.when(repo.findAll()).thenReturn(items);
//        service.getAllItems();
//        Assert.assertEquals(3,);
    }

    @Test
    public void shouldGetItemByID() {
        //ask for one item by the id
        //assert that the correct item is retrieved
        Item tmp3 = new Item();
        Mockito.when(repo.findByID(115)).thenReturn(tmp3);
        Item actual = service.itemByID(115);
        Assert.assertSame(tmp3,actual);

    }

    @Test
    public void shouldReturnOrderSoon(){
        //view items whose onHand<=optLevel
        //should get tmp2, tmp3
        Item tmp = new Item();
        Item tmp2 = new Item();
        Item tmp3 = new Item();
        List<Item> twoAndThree = new ArrayList<Item>();
        twoAndThree.add(tmp2);
        twoAndThree.add(tmp3);
        String s1 = "onhand";
        String s2 = "optlevel";
        String s3 = "<=";
        Mockito.when(repo.compareColumns(s1,s2,s3)).thenReturn(twoAndThree);
        List<Item> actual = service.orderSoon();
        Assert.assertArrayEquals(twoAndThree.toArray(),actual.toArray());
    }

    @Test
    public void shouldReturnOrderNow() {
        //view items whose onHand<=lowLevel
        //should get tmp2, tmp3
        Item tmp = new Item();
        Item tmp2 = new Item();
        Item tmp3 = new Item();
        List<Item> twoAndThree = new ArrayList<Item>();
        twoAndThree.add(tmp2);
        twoAndThree.add(tmp3);
        String s1 = "onhand";
        String s2 = "lowlevel";
        String s3 = "<=";
        Mockito.when(repo.compareColumns(s1,s2,s3)).thenReturn(twoAndThree);
        List<Item> actual = service.orderNow();
        Assert.assertArrayEquals(twoAndThree.toArray(),actual.toArray());

    }

    @Test
    public void shouldReturnBackOrderItems() {
        //view items whose onHand<=0
        //should get tmp2
        Item tmp = new Item();
        Item tmp2 = new Item();
        Item tmp3 = new Item();
        String s1 = "onhand";
        String s2 = "0";
        String s3 = "<=";
        List<Item> two = new ArrayList();
        two.add(tmp2);
        Mockito.when(repo.compareColumns(s1,s2,s3)).thenReturn(two);
        List<Item> actual = service.backOrderItems();
        Assert.assertArrayEquals(two.toArray(),actual.toArray());


    }



    @Test
    public void updateItem() {
        Item tmp = new Item();
        Mockito.doNothing().when(mockedService).updateItem(tmp);

        mockedService.updateItem(tmp);

        Mockito.verify(mockedService,Mockito.times(1)).updateItem(tmp);

    }
}