package com.game.screens;

import com.game.app.Application;
import com.game.data.AccountSQLRepo;

public interface Screen {
    Screen doScreen(Application app);
}
