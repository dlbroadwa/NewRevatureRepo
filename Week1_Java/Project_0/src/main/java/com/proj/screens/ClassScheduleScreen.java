package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;

public class ClassScheduleScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((SchedulingApplication)app).getScanner();

        System.out.println("CLASS SCHEDULE: Here are the classes you have signed up for.");


        return null;
    }
}
