package com.ex.tests;

import com.ex.services.AnswerService;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class AnswerServiceTests {
    @Test //this is a single test
    public void shouldPrintHelloWorld(){
        System.out.println("Hello World");
    }

    @Test
    public void shouldCreateAnswerService(){
        BufferedWriter writer = new BufferedWriter(new FileWriter("testAnswers"));
        AnswerService service = new AnswerService();
    }
}
