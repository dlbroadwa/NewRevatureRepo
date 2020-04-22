package com.ex.pandc;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Integer> taskQueue = new ArrayList<>();
    int MAX_CAPACITY = 5;

    Thread prod = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
    Thread consumer = new Thread(new Consumer(taskQueue), "Consumer");

    prod.start();
    consumer.start();

  }
}
