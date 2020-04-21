package com.game.screens;

import com.game.app.Application;

/**
 * Simple interface so that the application layer could cycle
 * through classes that implement Screen
 */
public interface Screen {
    Screen doScreen(Application app);
}
