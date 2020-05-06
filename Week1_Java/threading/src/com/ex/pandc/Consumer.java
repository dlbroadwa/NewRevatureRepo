package com.ex.pandc;

import java.util.List;

public class Consumer implements  Runnable{
  private final List<Integer> taskQueue;

  public Consumer(List<Integer> sharedQueue) {
    this.taskQueue = sharedQueue;
  }

  @Override
  public void run() {
    while(true) {
      try {
        consume();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void consume() throws InterruptedException {
    synchronized (taskQueue) {
      // loop and wait if the queue is empty
      while(taskQueue.isEmpty()) {
        System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for the Producer");
        taskQueue.wait();
      }

      // otherwise consume 1 item
      //Thread.sleep(1000);
      int i = (Integer) taskQueue.remove(0);
      System.out.println("Consumed " + i);
      taskQueue.notifyAll();
    }
  }
}
