package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    //private int[] buffer;
    private Queue<Integer> buffer;
    int maxSize;

    public Buffer() {
        buffer = new LinkedList<Integer>();
        maxSize = 10;
    }
    public Buffer(int maxSize) {
        this();
        this.maxSize = maxSize;
    }

    public void add(int value) throws InterruptedException {
        synchronized (this) {
            while (buffer.size() >= maxSize)
                wait();
            buffer.add(value);
            notify();
        }
    }
    public int remove() throws InterruptedException {
        synchronized (this) {
            while (buffer.size() == 0)
                wait();
            int val = buffer.poll();
            notify();
            return val;
        }
    }
}
