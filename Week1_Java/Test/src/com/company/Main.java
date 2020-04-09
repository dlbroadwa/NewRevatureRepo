package com.company;

import java.util.Random;

public class Main {

    // Procedural programming concepts
    // data types
    // "functions"
    // logic branches
    // loops

    String[] messages = new String[]{"All signs point to yes!", "Outlook not so good.", "Ask again later"};
    // Shake the ball
    // Get Message
    // Print Message
    public static void main(String[] args) {
        Main m = new Main();

        String myMessage = m.shakeBall();

        m.printMessage(myMessage);
    }

    private String shakeBall() {
        int index = getMessageIndex();

        if(index < 0)
        {
            index = 0;

        }   else if (index >= messages.length)
        {
            index = messages.length -1;
        }
        return messages[index];
    }

    private int getMessageIndex() {
        Random r = new Random();

        return r.nextInt((messages.length - 0) + 1) + 0;
    }

    private void printMessage(String msg) {
        System.out.print(msg);
    }
}