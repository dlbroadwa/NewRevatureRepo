package app;

import data.EmployeeSQLRepo;
import data.GrossPaySQLRepo;
import data.Repo;
import data.TimesheetSQLRepo;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import models.Employee;
import models.GrossPay;
import models.Timesheet;
import clients.*;

import java.util.List;

public class TimeLogger {
    public static void main(String[] args) {
        ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
                "",
                "",
                "",
                "");

        Repo<Employee, Integer> employeeRepo = new EmployeeSQLRepo(connectionDBUtility);
        Repo<Timesheet, Integer> timesheetRepo = new TimesheetSQLRepo(connectionDBUtility);
        Repo<GrossPay, Integer> grossPayRepo = new GrossPaySQLRepo(connectionDBUtility);

        EmployeeService eService = new EmployeeService(employeeRepo);
        TimesheetService tService = new TimesheetService(timesheetRepo);
        GrossPayService gpService = new GrossPayService(grossPayRepo);

        List<Employee> allEmployees = eService.getAllEmployees();
        List<Timesheet> allTimesheets = tService.getAllTimesheets();
        List<GrossPay> allGrossPay = gpService.getAllGrossPay();
    }
}
