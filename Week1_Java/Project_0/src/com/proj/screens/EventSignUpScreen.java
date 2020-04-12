package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventSignUpScreen implements Screen {
    @Override
    public Screen doScreen(Application app) throws IOException {
        Scanner scanner = ((SchedulingApplication)app).getScanner();
        File allEvents = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\allEvents");
        File userName = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\userSchedule");
        FileWriter fw = new FileWriter(userName, true);
        PrintWriter pw = new PrintWriter(fw);

        Scanner scan = new Scanner(allEvents);

        System.out.println("CLASS SIGN UPS: pick a class you would like to enroll into.");
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

        while (true) {
            String selectedEvent = scanner.nextLine();

            if(selectedEvent.length() == 0 || selectedEvent.trim().equals("")) {
                continue;
            } else if(selectedEvent.equals("back")) {
                return (Screen) new AdminScreen();
            } else {
                pw.println(selectedEvent);
            }

            pw.close();
            break;
        }




        return null;
    }
}






// putting the file line for line into an array
/*        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\allEvents"));
        String str;

        List<String> eventList = new ArrayList<String>();
        while((str = in.readLine()) != null){
            eventList.add(str);
        }

        String[] stringArr = eventList.toArray(new String[0]);

 */

