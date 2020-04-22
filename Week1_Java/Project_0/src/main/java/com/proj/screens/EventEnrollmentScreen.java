package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.util.Scanner;

public class EventEnrollmentScreen implements Screen {
    @Override
    public Screen doScreen(Application app) {
        Scanner scanner = ((SchedulingApplication)app).getScanner();

        System.out.println("CLASS ENROLLMENT: Choose a class to see the list of enrolled students");



        return (Screen) new AdminScreen();
    }
}
