package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.sql.SQLException;

public interface Screen {
    Screen doScreen(EventHandler app) throws IOException, SQLException;
}
