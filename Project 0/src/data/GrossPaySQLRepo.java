package data;

import dbutility.ConnectionDBUtility;
import models.GrossPay;

import java.util.List;

public class GrossPaySQLRepo implements Repo<GrossPay, Integer> {
    private List<GrossPay> timesheetList;
    private ConnectionDBUtility connectionDBUtility;

    public GrossPaySQLRepo(ConnectionDBUtility connectionDBUtility) {
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
