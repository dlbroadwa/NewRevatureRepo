package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;

public class EventEditor implements Screen {
    @Override
    public Screen doScreen(EventHandler app) throws IOException {
        System.out.println("Welcome: here you can edit your events");
        return null;
    }
}
