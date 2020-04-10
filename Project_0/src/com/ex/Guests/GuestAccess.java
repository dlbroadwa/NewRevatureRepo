package com.ex.Guests;

import com.ex.MainAndMenu.Runner;
import com.ex.MainAndMenu.InventoryScreen;
import com.ex.MainAndMenu.Screen;

import java.util.List;

/*GuestAccess is intended to be the guest only menu to view animal info */

public class GuestAccess implements Screen {


    @Override
    public Screen doScreen(Runner anInterface) {
       /*Test Line make sure program directs correctly*/ System.out.println("Guest");
        return new InventoryScreen();
    }
//
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
