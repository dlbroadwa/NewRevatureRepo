package com.ex.services;

import com.ex.DAO.*;
import com.ex.Objects.Keepers;
/*Class Description:
     *Acts as a middle "man" for the DAO and the JUnit Tests
 */
public class KeepersService {//Start of KeepersService
//Instant Variables
    private GetEnvironmentVar getVar = new GetEnvironmentVar();
    private DAO dao;

//Constructors
    public KeepersService(DAO dao) {
        this.dao = dao;
    }

//Methods
    public Boolean save(Keepers keepers) {//Start of save Method
        try{
            dao.save(keepers);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }//End of save Method
}//End of KeepersService
