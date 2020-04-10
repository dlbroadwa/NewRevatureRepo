package com.ex.Keepers;
import com.ex.MainAndMenu.Runner;
import com.ex.MainAndMenu.InventoryScreen;
import com.ex.MainAndMenu.Screen;

import java.util.List;

/*KeeperAccess class is intended to be the keeper only menu which will include animal info and add/remove animals*/

public class KeeperAccess implements Screen {


    public Screen doScreen(Runner anInterface) {
        /*Test Line make sure program directs correctly*/ System.out.println("Keeper");
        return new InventoryScreen();
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
