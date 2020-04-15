package com.ex.pandc;

import java.util.List;

public class Producer implements Runnable{
  private final List<Integer> taskQueue;
  private final int MAX_CAPACITY;

  public Producer(List<Integer> sharedQueue, int capacity) {
    this.taskQueue = sharedQueue;
    this.MAX_CAPACITY = capacity;
  }


  @Override
  public void run() {
    int counter = 0;

    while(true) {
      try {
        produce(counter++);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void produce(int i) throws InterruptedException {
    synchronized (taskQueue) {
      // loop and wait if the queue is full
      while(taskQueue.size() == MAX_CAPACITY) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for the consumer. " + taskQueue.size());
        taskQueue.wait();
      }

      //otherwise add to queue
      //Thread.sleep(500);
      taskQueue.add(i);
      System.out.println("Produced " + i);
      taskQueue.notifyAll();
    }
  }
}
