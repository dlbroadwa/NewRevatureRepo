package com.ex.services;

import com.ex.Objects.Animals;
import com.ex.DAO.DAO;
import com.ex.DAO.GetEnvironmentVar;


public class AnimalService {
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private DAO dao;

    public AnimalService(DAO dao) {
        this.dao = dao;
    }


    public Boolean save(Animals animals) {
        try{
            dao.save(animals);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean delete(Animals animals) {
        try{
            dao.delete(animals);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
