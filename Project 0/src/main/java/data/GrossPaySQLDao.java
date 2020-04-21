package data;

import dbutility.ConnectionDBUtility;
import models.GrossPay;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GrossPaySQLDao implements Dao<GrossPay, Integer> {
    //private List<GrossPay> timesheetList;
    private ConnectionDBUtility connectionDBUtility;

    public GrossPaySQLDao(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public GrossPay findById(Integer integer) {
        return null;
    }

    @Override
    public List<GrossPay> findAll() {
        return null;
    }

    @Override
    public Integer save(GrossPay obj) {
        return null;
    }

    @Override
    public void update(GrossPay newObj, Integer integer) {

    }

    @Override
    public void delete(GrossPay obj) {

    }
}
