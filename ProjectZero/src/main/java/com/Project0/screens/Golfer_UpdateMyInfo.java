package com.Project0.screens;

import com.Project0.application.App;
import com.Project0.dao.GolferDAO;
import com.Project0.dao.GolferDAOImpl_FileIO;
import com.Project0.model.Golfer;
import com.Project0.services.GolferService;

import java.util.ArrayList;
import java.util.Scanner;

public class Golfer_UpdateMyInfo implements Screen {
    @Override
    public Screen doScreen(App app) {
        Golfer golfer = new Golfer();
        golfer = app.getGolferFromLoggedInUser();
        GolferService service = app.getgService();
        return updateSingleGolfer(golfer, app.getScanner(), service);
    }

    protected Screen updateSingleGolfer(Golfer golfer, Scanner scanner, GolferService service) {
        Golfer newGolfer = new Golfer();
        String name, address, phone, emergency, car, model, license;

        //IDS are never manually input!!!
        newGolfer.setUserID(golfer.getUserID());

        //ALLOW INPUT FOR CHANGING SOMETHING - OR HIT ENTER FOR NOTHING TO BYPASS AND MOVE TO NEXT FIELD
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
//            System.out.println("NEW INFORMATION: " + newGolfer.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UPDATE UNSUCCESSFUL");
        } finally {
            return new GolferOptionsMain();
        }
    }

}
