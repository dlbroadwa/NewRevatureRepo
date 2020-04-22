package com.ex.services;

import com.ex.Objects.Animals;
import com.ex.DAO.DAO;
import com.ex.DAO.GetEnvironmentVar;
/*Class Description:
    *Acts as a middle "man" for the DAO and the JUnit Tests
 */

public class AnimalService {//Start of AnimalService Class
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private DAO dao;

//Constructors
    public AnimalService(DAO dao) {
        this.dao = dao;
    }

//Methods
    public Boolean save(Animals animals) {//Start of save Method
        try{
            dao.save(animals);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//End of save Method

    public Boolean delete(Animals animals) {//Start of delete Method
        try{
            dao.delete(animals);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//End of delete Method
}//End of AnimalService Class
