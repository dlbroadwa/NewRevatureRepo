package com.ex;

import java.util.LinkedList;

public class Doer {
    public LinkedList<Integer> list = new LinkedList<>();
    public int cap = 5;

    public void make() throws InterruptedException{
        int value = 0;
        while (true){
            synchronized (this){
                while(list.size() == cap)
                    wait();
                System.out.println("Employee made order " + value);
                list.add(value++);
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }
    public void take() throws InterruptedException{
        while (true){
            synchronized (this){
                while (list.size() == 0)
                    wait();
                int taken = list.removeFirst();
                System.out.println("Customer took order " + taken);
                notifyAll();
                Thread.sleep(750);
            }
        }
    }


}
