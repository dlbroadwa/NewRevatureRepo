package data;

import dbutility.ConnectionDBUtility;
import models.Timesheet;

import java.util.List;

public class TimesheetSQLRepo implements Repo<Timesheet, Integer> {
    private List<Timesheet> timesheetList;
    private ConnectionDBUtility connectionDBUtility;

    public TimesheetSQLRepo(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Timesheet findById(Integer integer) {
        return null;
    }

    @Override
    public List<Timesheet> findAll() {
        return null;
    }

    @Override
    public Integer save(Timesheet obj) {
        return null;
    }

    @Override
    public void update(Timesheet newObj, Integer integer) {

    }

    @Override
    public void delete(Timesheet obj) {

    }
}
