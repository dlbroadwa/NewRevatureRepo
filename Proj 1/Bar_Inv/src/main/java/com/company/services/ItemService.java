package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;
import com.company.DAO.data.ItemRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private Repository<Item, Integer, String> repo;

    public ItemService(Repository<Item,Integer, String> repo) {this.repo=repo;}

    public int getAllItemsForCustomer() throws SQLException {
        //get the whole inventory and print it to the console
        //this version shows only the info that the customer would need to see
        List<Item> tmp = this.repo.findAll();
        if (tmp != null) {
            System.out.println("Item Name, ID Number, Number in Stock");
            for (Item i : tmp) {
                System.out.println(i.getItemName() + ", " + i.getId() + ", " + i.getOnHand());
            }
            return 1;
        } else {
            return 0;
        }
    }

    public int getAllItems() throws SQLException {
        //get the whole inventory and print it to the console
        //this version shows all the info that the admin would need to see
        List<Item> tmp = this.repo.findAll();
        if (tmp != null) {
            System.out.println("Item Name, ID Number, Number in Stock, Low Level, Optimal Level");
            for (Item i : tmp) {
                System.out.println(i.getItemName() + ", " + i.getId() + ", " + i.getOnHand() + ", " + i.getLowLevel() + ", " + i.getOptLevel());
            }
            return 1;
        } else {
            return 0;
        }
    }

    public Item itemByID (Integer id){
        //here we will take an ID number and find the associated item
        return this.repo.findByID(id);
    }

    public void addItem(String name, int id, int onHand, int lowLevel, int optLevel){
        //take all the aspects of a new item from admin input
        Item newItem = new Item(); //create a new item, populate its attributes
        newItem.setItemName(name);
        newItem.setId(id);
        newItem.setOnHand(onHand);
        newItem.setLowLevel(lowLevel);
        newItem.setOptLevel(optLevel);
        this.repo.save(newItem); //save new item
    }

    public void removeItem(Integer id){
        //take the id number, delete it
        this.repo.deleteByID(id);
    }

    public void updateItem(Item item){
        //given an Item object, update that item
        this.repo.updateByID(item);
    }

    //view items whose onHand<=optLevel
    public List<Item> orderSoon(){
        List<Item> orderSoon = new ArrayList();
        String s1 = "onhand";
        String s2 = "optlevel";
        String s3 = "<=";
        orderSoon =this.repo.compareColumns(s1,s2,s3);
        return orderSoon;
    }

    //view items whose onHand<=lowLevel
    public List<Item> orderNow(){
        List<Item> orderNow = new ArrayList();
        String s1 = "onhand";
        String s2 = "lowlevel";
        String s3 = "<=";
        orderNow=this.repo.compareColumns(s1,s2,s3);
        return orderNow;
    }

    //view items whose onHand<=0
    public List<Item> backOrderItems(){
        List<Item> backOrder = new ArrayList();
        String s1 = "onhand";
        String s2 = "0";
        String s3 = "<=";
        backOrder=this.repo.compareColumns(s1,s2,s3);
        return backOrder;
    }
}
