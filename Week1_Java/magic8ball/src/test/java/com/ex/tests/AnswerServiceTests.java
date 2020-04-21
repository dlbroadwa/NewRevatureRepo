package com.ex.tests;

import com.ex.services.AnswerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AnswerServiceTests {
  AnswerService service;
  private Object Assert;


  @Before // run this method before each and every test
  public void testInit() {
    // setup a fake file with answers
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("testAnswers"));
      writer.write("All signs point to yes");
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    service = new AnswerService("testAnswers");
  }

  @Test // this is a single test
  public void shouldPrintHelloWorld() {
    System.out.println("Hello, World");
  }

  @Test
  public void shouldCreateAnswerService() {

    Assert.assertNotNull(service);

  }

  @Test
  public void shouldReturnAValidString() {

    // Screen --> Service -> DAO
    // Mock the Screen by directly invoking the method
    // Mock the DAO by creating a fake file to read
    String output = service.getAnswer(0);
    Assert.assertEquals("Didn't return desired string", "All signs point to yes", output);
  }
}

// Service -> DAO -> Database
// Testing the DAO
// Mock the Service by directly invoking the DAO method
// Mock the database by creating a fake database (h2, Mockito)
