package com.proj.screens;

import com.proj.app.EventHandler;

import java.io.IOException;
import java.sql.SQLException;

//**********************************Screen Interface*****************************************//
/**
 * The Screen interface is an important interface that allows the user
 * to swap between screens in the application.
 * the currentScreen would be set to the screen being used by the user at any particular time
 */

public interface Screen {
    Screen doScreen(EventHandler app) throws IOException, SQLException;
}
