package com.ex;

import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws InterruptedException {
         final Doer doer;
        Thread pro = new Thread(new Producer("Jimmy"));
        Thread con = new Thread(new Consumer("Hungry guy"));


        con.start();
        pro.start();
        con.join();
        pro.join();

        System.out.println("All orders served!");

    }

}