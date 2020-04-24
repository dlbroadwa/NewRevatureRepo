package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.model.Golfer;
import com.Project0.services.GolferService;

import java.util.Scanner;

public class Golfer_AddGolfer implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferService service = app.getgService();


        String name, address, carMake, carModel, carLicensePlate;
        Long phone, emergencyPhone;
        Golfer golfer = null;

        //build golfer object
        System.out.println("CREATE NEW GOLFER WIZARD");
        System.out.println("Golfer's name: ");
        name = scanner.nextLine();
        System.out.println("Golfer's address:");
        address = scanner.nextLine();
        System.out.println("Golfer's phone:");
        while(true) {
            String curr = scanner.nextLine();
            if(curr.length() != 10){
                System.out.println("TOO SHORT - 10 DIGITS PLEASE");
                continue;
            }
            try {
                phone = Long.parseLong(curr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter 10 digits NUMERIC only");
                continue;
            }
        }
        System.out.println("Golfer's emergency phone:");
        while(true) {
            String curr = scanner.nextLine();
            if (curr.length() != 10) {
                System.out.println("TOO SHORT - 10 DIGITS PLEASE");
                continue;
            }
            try {
                emergencyPhone = Long.parseLong(curr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter 10 digits NUMERIC only");
                continue;
            }
        }
        System.out.println("Golfer's car make:");
        carMake = scanner.nextLine();
        System.out.println("Golfer's car model:");
        carModel = scanner.nextLine();
        System.out.println("Golfer's car license plate:");
        carLicensePlate = scanner.nextLine();

        //populate golfer object with input data and pass on to dao
        golfer = new Golfer(0, name, address, Long.toString(phone), Long.toString(emergencyPhone), carMake, carModel, carLicensePlate);
        try{
            if(service.createGolfer(golfer)){
                System.out.println("CREATE GOLFER WAS SUCCESSFULL!!");
                return new GolferOptionsMain();
            }
            else {
                System.out.println("CREATE GOLFER WAS UNNSUCCESFUL :(");
                return new GolferOptionsMain();
            }
        } catch (Exception e) {
            System.out.println("CREATE GOLFER WAS UNNSUCCESFUL :(");
            return new GolferOptionsMain();
        }
    }
}
