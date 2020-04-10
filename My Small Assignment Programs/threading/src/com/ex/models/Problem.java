package com.ex.models;

import java.util.ArrayList;
import java.util.List;

public class Problem implements Runnable{
    private int taskCount;
    private List<Task> taskList;

    public synchronized void produce(){
        ++taskCount;
        taskList.add(new Task("Task" + taskCount));
        System.out.println("Produced " + taskList.get(taskCount - 1).getName());
    }
    public synchronized void remove(){
        System.out.println("Finish/Removing " + taskList.get(0).getName());
        taskList.remove(0);
    }
    public synchronized boolean hasTask(){
        return !taskList.isEmpty();
    }
    public Problem(){
        taskCount = 0;
        taskList = new ArrayList<>();
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
