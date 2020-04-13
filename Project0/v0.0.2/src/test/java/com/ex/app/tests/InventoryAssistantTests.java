package com.ex.app.tests;

import com.ex.app.InventoryAssistant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryAssistantTests {
    InventoryAssistant inventoryAssistant;
    @Before
    public void init(){
        inventoryAssistant = new InventoryAssistant();
    }
    @Test
    public void canaryFalse(){ Assert.assertFalse(false);}
    @Test
    public void canaryTrue(){ Assert.assertTrue(true); }
    @Test
    public void empty(){
        Assert.assertTrue(inventoryAssistant.items.isEmpty());
    }
    @Test
    public void title(){
        Assert.assertTrue(inventoryAssistant.getTitle().equals("Inventory Assistant"));
    }

    
}
