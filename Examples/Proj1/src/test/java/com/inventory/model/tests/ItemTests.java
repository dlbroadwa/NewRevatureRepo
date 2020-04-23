package com.inventory.model.tests;

import com.inventory.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTests {
    int id;
    String name;
    double value;
    int shelfLife;
    Item testItem;

    @Before
    public void init(){
        id = 1;
        name = "testItem";
        value = 10.1;
        shelfLife = 100;
        testItem = new Item(id, name, value, shelfLife);
    }

    @Test
    public void Item() {
        Item item = new Item(id, name, value, shelfLife);
        Assert.assertEquals(item.getId(), id);
        Assert.assertEquals(item.getName(), name);
        Assert.assertEquals(item.getValue(), value, 0.000000001);
        Assert.assertEquals(item.getShelfLife(), shelfLife);
    }

    @Test
    public void getId() {
        Assert.assertEquals(testItem.getId(), id);
    }

    @Test
    public void getName() {
        Assert.assertEquals(testItem.getName(), name);
    }

    @Test
    public void getValue() {
        Assert.assertEquals(testItem.getValue(), value, 0.000000001);
    }

    @Test
    public void getShelfLife() {
        Assert.assertEquals(testItem.getShelfLife(), shelfLife, 0.000000001);
    }

    @Test
    public void toString1() {
        String expectedString = "Item{id=1, name='testItem', value=10.1, shelfLife=100}";
        Assert.assertEquals(testItem.toString(), expectedString);
    }

    @Test
    public void getSQLColumnFormat() {
        String expectedString = "(\"id\", \"name\", \"value\", \"shelfLife\")";
        Assert.assertEquals(testItem.getSQLColumnFormat(), expectedString);
    }

    @Test
    public void toSQLString() {
        String expectedString = "(1, 'testItem', 10.1, 100) ";
        Assert.assertEquals(testItem.toSQLString(), expectedString);
    }
}
