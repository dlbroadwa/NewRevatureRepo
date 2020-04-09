package com.ex;

import com.ex.models.Employee;
import com.ex.models.Problem;
import com.ex.models.Task;

public class Main {

    public static void main(String[] args) { // is the first thread created by the JVM
      Problem problem = new Problem();
      for(int i = 0; i < 20; i++){
        problem.produce();
      }
      Employee employee = new Employee("Mr. Gutsy");
      employee.run(problem);



//      Thread emp1 = new Employee("John"); // Employee extends Thread
//      Thread task1 = new Thread(new Task("Paint the fence")); // Task implements Runnable
//
//      task1.start();
//      emp1.start();
//
//      try {
//        emp1.join(); // force the main thread to wait for emp1 to finish
//                      // main thread will join emp1 thread
//      }catch (InterruptedException ex) {
//        ex.printStackTrace();
//      }
//      System.out.println("Main is ending");
    }
}
