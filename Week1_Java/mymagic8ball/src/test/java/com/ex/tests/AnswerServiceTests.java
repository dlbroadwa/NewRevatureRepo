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

    @Before // run this method before each and every test
    public void testInit() {
        // setup a fake file with answers
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("testAnswers.txt"));
            writer.write("All signs point to yes");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        service = new AnswerService("testAnswers.txt");
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
        String output = service.getAnswer(0);
        Assert.assertEquals("Didn't return desired string", "All signs point to yes", output);
    }
}
