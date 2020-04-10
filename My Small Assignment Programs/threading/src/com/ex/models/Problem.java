package com.ex.models;

import java.util.ArrayList;
import java.util.List;

public class Problem implements Runnable{
    private int taskCount;
    private List<Task> taskList;
    private int taskNumber;

    public synchronized void produce(){
        ++taskCount;
        ++taskNumber;
        Task newTask = new Task("Task" + taskNumber);
        taskList.add(new Task("Task" + taskNumber));
        System.out.println("Produced " + newTask.getName());
    }
    public synchronized void remove(){
        System.out.println("Finish/Removing " + taskList.get(0).getName());
        taskList.remove(0);
        taskCount--;
    }
    public synchronized boolean hasTask(){
        return !taskList.isEmpty();
    }
    public Problem(){
        taskCount = 0;
        taskNumber = 0;
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
