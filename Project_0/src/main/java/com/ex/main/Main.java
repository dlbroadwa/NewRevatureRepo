package com.ex.main;

import com.ex.dao.Animals;
import com.ex.dao.DAO;
import com.ex.dao.SqlDatabase;

import java.util.List;

public class Main {

    public static void main(String[] args) {
       Runner anInterface = new KeeperGuestSorter();
        anInterface.run();

    }
}
