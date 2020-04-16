package com.Project0.services;

import com.Project0.dao.GolferDAO;
import com.Project0.model.Golfer;

import java.util.ArrayList;

public class GolferService {
    private GolferDAO gdao = null;

    public GolferService(GolferDAO gdao) {
        this.gdao = gdao;
    }

    public Boolean createGolfer(Golfer golfer) {
        try{
            return gdao.createGolfer(golfer);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Golfer> viewGolfer(Golfer golfer) {
        try {
            return gdao.viewGolferInfo(golfer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Golfer>();
        }
    }

    public Boolean updateGolfer(Golfer oldGolfer, Golfer newGolfer) {
        try {
            gdao.updateGolferInfo(oldGolfer, newGolfer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
