package com.ex.MainAndMenu;

import com.ex.DAO.FileIoDAO;

import java.util.List;

public class InventoryScreen implements Screen {


    public Screen doScreen(Runner anInterface) {
        int row = 0,column=0;
        FileIoDAO fileIoDAO = ((KeeperGuestSorter) anInterface).getFileIoDAO();

        for(row=0; row<100; row++) {
            String animalInventory = fileIoDAO.getAnimalInventory(row);
            if (animalInventory == null) {
                break;
            } else {
                System.out.println(animalInventory);
            }
        }
        return null;
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
