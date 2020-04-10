package com.ex.MainAndMenu;

import com.ex.DAO.FileIoDAO;

import java.util.List;

public class InventoryScreen implements Screen {

    @Override
    public Screen doScreen(Runner anInterface) {
        /*Test Line make sure program directs correctly*/ System.out.println("In Inventory Screen");
        return new FileIoDAO("resources/animalInventory");
    }

//    @Override
//    public Integer save(Object o) {
//        return null;
//    }
//
//    @Override
//    public Object getById(Integer id) {
//        return null;
//    }
//
//    @Override
//    public void update(Object o, Integer id) {
//
//    }
//
//    @Override
//    public void delete(Object o) {
//
//    }
//
//    @Override
//    public List<Object> getAll() {
//        return null;
//    }
}
