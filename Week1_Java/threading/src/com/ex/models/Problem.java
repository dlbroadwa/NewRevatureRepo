package com.ex.models;

import java.util.ArrayList;
import java.util.List;

public class Problem {
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
}
