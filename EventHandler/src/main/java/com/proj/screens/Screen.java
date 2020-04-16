package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;

public interface Screen {
    Screen doScreen(EventHandler app) throws IOException;
}
