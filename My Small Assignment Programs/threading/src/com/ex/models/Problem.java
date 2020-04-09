package com.ex.models;

import java.util.ArrayList;
import java.util.List;

public class Problem implements Runnable{
    private int taskCount;
    private List<Task> taskList = new ArrayList<>();

    public synchronized void produce(){
        System.out.println("Producing a task.");
        ++taskCount;
        taskList.add(new Task("Task" + taskCount));
    }
    public synchronized void remove(){
        taskList.remove(0);
    }
    public synchronized boolean hasTask(){
        return !taskList.isEmpty();
    }
    public Problem(){
        this.run();
    }

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
