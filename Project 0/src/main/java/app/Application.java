package app;

import clients.EmployeeService;
import clients.GrossPayService;
import clients.TimesheetService;
import data.EmployeeSQLRepo;
import data.GrossPaySQLRepo;
import data.Repo;
import data.TimesheetSQLRepo;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import models.Employee;
import models.GrossPay;
import models.Timesheet;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
                "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com",
                "timesheet_user",
                "ge4s1lly",
                "public");

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
