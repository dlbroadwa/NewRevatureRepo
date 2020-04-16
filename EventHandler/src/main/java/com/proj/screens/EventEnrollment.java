package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;

public class EventEnrollment implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException {
        System.out.println("Here are the people enrolled into your classes");
        return null;
    }
}
