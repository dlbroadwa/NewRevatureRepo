package com.ex;

import com.ex.models.Employee;
import com.ex.models.Problem;
import com.ex.models.Task;

public class Main {

    public static void main(String[] args) { // is the first thread created by the JVM
      Problem problem = new Problem();
      problem.run();
      try {
          Thread.sleep(100);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      Employee employee = new Employee(problem, "Mr. Gutsy");
    }
}
