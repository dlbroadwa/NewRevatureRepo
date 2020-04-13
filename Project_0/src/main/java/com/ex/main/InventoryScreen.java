package com.ex.main;

import com.ex.dao.FileIoDAO;

public class InventoryScreen implements Screen {

    int row = 0;
    FileIoDAO fileIoDAO;

    public Screen doScreen(Runner anInterface) {
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

}
