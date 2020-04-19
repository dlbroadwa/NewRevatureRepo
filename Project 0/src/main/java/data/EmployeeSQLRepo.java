package data;

import dbutility.ConnectionDBUtility;
import models.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeSQLRepo implements Repo<Employee, String> {
    private List<Employee> employeeList;
    private ConnectionDBUtility connectionDBUtility;
    private Employee employee;

    public EmployeeSQLRepo(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Employee findById(String s) {
        Connection connection = null;
        this.employee = new Employee();
        //setEmployeID();
        try {
            connection = connectionDBUtility.getConnection();
            String defaultSchema = connectionDBUtility.getDefaultSchema();
            String sql ="select employeeid, first_name, last_name, hourly_salary, userid from " + defaultSchema + " where id =" + this.employee.getEmployeeID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public String save(Employee obj) {
        return null;
    }


    @Override
    public void update(Employee newObj, String s) {

    }

    @Override
    public void delete(Employee obj) {

    }
}
