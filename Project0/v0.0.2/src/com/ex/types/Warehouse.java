package com.ex.types;

import com.ex.console.ConsoleInput;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    Warehouse(){
        name = ConsoleInput.getInstance().getName();
        inventory = new ArrayList<Item>();
    }

    final String name;
    final private List<Item> inventory;
}
