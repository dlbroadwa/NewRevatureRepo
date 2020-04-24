package com.company.screens;

import com.company.app.Application;

import java.io.IOException;

public interface Screen {
    Screen doScreen(Application app) throws Exception;

}
