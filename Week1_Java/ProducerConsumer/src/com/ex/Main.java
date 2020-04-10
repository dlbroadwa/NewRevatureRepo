package com.ex;

import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        LinkedList<Integer> list = new LinkedList<>();
        int cap = 5;

        Thread pro = new Thread(new Producer(list, cap));
        Thread con = new Thread(new Consumer(list));

        pro.start();
        con.start();
        con.join();
        pro.join();

        System.out.println("All orders served!");

    }

}