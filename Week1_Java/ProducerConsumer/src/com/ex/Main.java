package com.ex;

import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws InterruptedException { ;

        final Doer doer = new Doer();
        Thread pro = new Thread(new Producer("Jimmy"));
        Thread con = new Thread(new Consumer("Hungry guy"));

        pro.start();
        con.start();
        con.join();
        pro.join();

        System.out.println("All orders served!");

    }

}