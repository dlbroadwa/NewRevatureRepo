package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.LeagueDAO;
import com.Project0.dao.LeagueDAOImplementation_FileIO;

import java.util.Scanner;

public class League_AddGolfer implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        LeagueDAO dao = new LeagueDAOImplementation_FileIO();

        System.out.println("ADD GOLFER TO A LEAGUE WIZARD");
        System.out.println("SELECT WHICH LEAGUE YOU WANT TO ADD TO:");

        return null;
    }
}
