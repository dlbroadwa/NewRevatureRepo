package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    //private int[] buffer;
    private final Queue<Integer> buffer;
    private final int maxSize;

    public Buffer() {
        buffer = new LinkedList<Integer>();
        maxSize = 10;
    }
    public Buffer(int maxSize) {
        buffer = new LinkedList<Integer>();
        this.maxSize = maxSize;
    }

    public void add(int value) throws InterruptedException {
        synchronized (buffer) {
            while (buffer.size() >= maxSize)
                buffer.wait();
            buffer.add(value);
            buffer.notify();
        }
    }
    public int remove() throws InterruptedException {
        synchronized (buffer) {
            while (buffer.size() == 0)
                buffer.wait();
            int val = buffer.poll();
            buffer.notify();
            return val;
        }
    }
}
