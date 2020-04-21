package com.ex.services;

import com.ex.DAO.*;
import com.ex.Objects.Keepers;

public class KeepersService {
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private DAO dao;

    public KeepersService(DAO dao) {
        this.dao = dao;
    }


    public Boolean save(Keepers keepers) {
        try{
            dao.save(keepers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
