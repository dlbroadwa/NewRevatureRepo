package com.proj.screens;

import com.proj.app.Application;

import java.io.IOException;

public interface Screen {
    Screen doScreen(Application app) throws IOException;
}