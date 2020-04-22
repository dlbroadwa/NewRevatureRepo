package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EventScheduleScreen implements Screen {
    @Override
    public Screen doScreen(Application app) throws FileNotFoundException {
        Scanner scanner = ((SchedulingApplication)app).getScanner();
        File nameAndSchedule = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\nameAndSchedule");

        System.out.println("EVENT SCHEDULE: Here are the classes you have signed up for.");
        Scanner scan = new Scanner(nameAndSchedule);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

        return (Screen) new UserScreen();
    }
}
