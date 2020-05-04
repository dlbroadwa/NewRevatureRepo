package com.revature;

import com.revature.day4.Consumer;
import com.revature.day4.Producer;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int size = 4;

        Thread p = new Thread (new Producer(list, size));
        Thread c = new Thread (new Consumer(list, size));

        p.start();
        c.start();
    }
}
