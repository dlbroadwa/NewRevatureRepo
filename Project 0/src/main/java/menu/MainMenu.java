package menu;

import app.Application;
import app.TimeSheetApp;
import clients.EmployeeService;
import clients.GrossPayService;
import clients.TimesheetService;
import data.Dao;
import data.EmployeeSQLDao;
import data.GrossPaySQLDao;
import data.TimesheetSQLDao;
import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import models.Employee;
import models.GrossPay;
import models.Timesheet;

import java.util.List;
import java.util.Scanner;

public class MainMenu implements Menu{
    //contains com.main menu
    boolean exit;
    private HoursMenu hMenu;
    private PayMenu pMenu;
    private Object MainMenu;
    private Object Menu;

    ConnectionDBUtility connectionDBUtility = new PostgresConnectionUtility(
            "jdbc:postgresql://rjdatabase-1.cbfjnm41xkat.us-east-1.rds.amazonaws.com:5432/Project 0",
            "timesheet_user",
            "ge4s1lly",
            "public");

    @Override
    public Menu makeMenu(Application app) {
        Scanner scanner = ((TimeSheetApp)app).getScanner();
        printHeader();
        while(!exit){
            mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            menuAction(choice);
        }
        //return (Menu) MainMenu;
        return null;
    }

    private void printHeader(){
        System.out.println("Welocome to Timesheets!");
    }

    static void mainMenu(){
        System.out.println("Please make a selection: \n");
        System.out.println("1:\t Employee List");
        System.out.println("3:\t Update Employees");
        System.out.println("4:\t Weekly Hours");
        System.out.println("5:\t Update Hours");
        System.out.println("6:\t Weekly Pay");
        System.out.println("0:\t Exit");
        //return null;
        //return null;
    }

    Dao<Employee, Integer> employeeDao = new EmployeeSQLDao(connectionDBUtility);
/*    Dao<Timesheet, Integer> timesheetDao = new TimesheetSQLDao(connectionDBUtility);
    Dao<GrossPay, Integer> grossPayDao = new GrossPaySQLDao(connectionDBUtility);*/

    EmployeeService eService = new EmployeeService(employeeDao);
/*    TimesheetService tService = new TimesheetService(timesheetDao);
    GrossPayService gpService = new GrossPayService(grossPayDao);*/
git
    //Menu with options to access submenus
    private Menu menuAction(int choice){
        switch (choice){
            case 0:
                exit = true;
                System.out.println("Thanks for using Timesheets!");
                System.exit(0);
                break;
            case 1:
                System.out.println("Here are the employees: ");
                List<Employee> allEmployees = eService.getAllEmployees();
//                allEmployees.toString();
                for(Employee e : allEmployees) {
                    System.out.println("Employee: " + e.getEmployeeID() + e.getFirstName() + e.getLastName() + e.getHourly_Salary() + e.getUserid());
                }
                break;
            case 2:
                //PayMenu.payMenu();
                //pMenu = new PayMenu();
                //return new PayMenu();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:
                System.out.println("That isn't a valid menu option.\n");
        }
        return (Menu) MainMenu;
    }
}
