package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.services.GolferService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Golfer_UpdateGolfer implements Screen {
    @Override
    public Screen doScreen(App app) {
        Scanner scanner = app.getScanner();
        GolferService service = app.getgService();

        System.out.println("UPDATE GOLFER WIZARD");
        System.out.println("Enter golfers name: ");
        String name = scanner.nextLine();
        ArrayList<Golfer> golfers = new ArrayList<>();
        Golfer tempGolfer = new Golfer();
        tempGolfer.setName(name);
        golfers = service.viewGolfer(tempGolfer);

        for(Golfer e : golfers)       //DEBUG LIST
            System.out.println(e.toString());

        //Handle cases of return length/emtpy
        if(golfers.size() <= 0) {
            System.out.println("NONE FOUND - SEARCH AGAIN?  Y or N");
            while(true) {
                String answer = scanner.nextLine().toLowerCase();
                switch (answer) {
                    case "y":
                        return new Golfer_UpdateGolfer();
                    case "n":
                        return new GolferOptionsMain();
                    default:
                        System.out.println("INVALID OPTIONS - Y OR N");
                        continue;
                }
            }
        }

        if(golfers.size() > 1) {
            System.out.println("MULTIPLE FOUND - ENTER NUMBER OF DESIRED GOLFER");
            for(int itr= 0; itr < golfers.size(); itr++) {
                System.out.printf("%d - %s \n", itr, golfers.get(itr).getName());
            }
            while(true) {
                String choice = scanner.nextLine();
                try{
                    int thisChoice = Integer.parseInt(choice);
                    if(thisChoice > golfers.size() - 1) {
                        System.out.println("NOT A VALID OPTION - ENTER FROM CHOICES ABOVE");
                        continue;
                    }
                    Screen newScreen = updateSingleGolfer(golfers.get(thisChoice), scanner, service);
                    return newScreen;
                } catch (NumberFormatException ex) {
                    System.out.println("NOT A VALID OPTION - ENTER FROM CHOICES ABOVE");
                    continue;
                }
            }
        }

        if(golfers.size() == 1) {
            Screen newScreen = updateSingleGolfer(golfers.get(0), scanner, service);
            return newScreen;
        }

        return null;
    }

    protected Screen updateSingleGolfer(Golfer golfer, Scanner scanner, GolferService service) {
        Golfer newGolfer = new Golfer();
        String name, address, phone, emergency, car, model, license;

        //IDS are never manually input!!!
        newGolfer.setUserID(golfer.getUserID());

        System.out.println("CURRENT GOLFER NAME: " + golfer.getName());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        name = scanner.nextLine();
        if(name.length() > 0)
            newGolfer.setName(name);
        else newGolfer.setName(golfer.getName());

        System.out.println("CURRENT GOLFER ADDRESS: " + golfer.getAddress());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        address = scanner.nextLine();
        if(address.length() > 0)
            newGolfer.setAddress(address);
        else newGolfer.setAddress(golfer.getName());

        System.out.println("CURRENT GOLFER PHONE: " + golfer.getPhone());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        phone = scanner.nextLine();
        if(phone.length() > 0)
            newGolfer.setPhone(phone);
        else newGolfer.setPhone(golfer.getPhone());

        System.out.println("CURRENT GOLFER EMERGENCY PHONE: " + golfer.getEmergencyPhone());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        emergency = scanner.nextLine();
        if(emergency.length() > 0)
            newGolfer.setEmergencyPhone(emergency);
        else newGolfer.setEmergencyPhone(golfer.getEmergencyPhone());

        System.out.println("CURRENT GOLFER CAR MAKE: " + golfer.getCarMake());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        car = scanner.nextLine();
        if(car.length() > 0)
            newGolfer.setCarMake(car);
        else newGolfer.setCarMake(golfer.getCarMake());

        System.out.println("CURRENT GOLFER CAR MODEL: " + golfer.getCarModel());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        model = scanner.nextLine();
        if(model.length() > 0)
            newGolfer.setCarModel(model);
        else newGolfer.setCarModel(golfer.getCarModel());

        System.out.println("CURRENT GOLFER LICENSE PLATE: " + golfer.getCarLicensePlate());
        System.out.println("ENTER NEW OR NOTHING TO BYPASS: ");
        license = scanner.nextLine();
        if(license.length() > 0)
            newGolfer.setCarLicensePlate(license);
        else newGolfer.setCarLicensePlate(golfer.getCarLicensePlate());

        try{
            service.updateGolfer(golfer, newGolfer);
            System.out.println("GOLFER SUCCESSFULLY UPDATED");
            System.out.println("NEW INFORMATION: " + newGolfer.toString());
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("UPDATE UNSUCCESSFUL");
        } finally {
            return new GolferOptionsMain();
        }
    }
}
