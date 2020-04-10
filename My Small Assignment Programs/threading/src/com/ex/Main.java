package com.ex;

import com.ex.models.Employee;
import com.ex.models.Problem;
import com.ex.models.Task;

public class Main {

    public static void main(String[] args) { // is the first thread created by the JVM
      Problem problem = new Problem();
      Thread problemThread = new Thread(problem);
      problemThread.start();
      //Employee employee = new Employee(problem, "Mr. Gutsy");
    }
}
